package com.rabhareit.tailing.web;

import com.rabhareit.tailing.entity.TweetInfoModel;
import com.rabhareit.tailing.web.form.SearchQuery;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TwitterInfoGetterController {

  @Value("${app.tailing-consumer-key}")
  private String tailingConsumerKey;

  @Value("${app.tailing-consumer-secret}")
  private String tailingConsumerSecret;

  @Value("${app.tailing-access-token}")
  private String tailingAccessToken;

  @Value("${app.tailing-access-token-secret}")
  private String tailingAccessTokenSecret;

  TailingExceptionHandler exception = new TailingExceptionHandler();

  @RequestMapping(value="/get/search")
  ModelAndView accessHome(ModelAndView mav) {
    mav.setViewName("getTweetsInfo");
    mav.addObject("downloadLink",false);
    return mav;
  }

  @RequestMapping(value="/get/search", method=RequestMethod.POST)
  ModelAndView searchTweets(@ModelAttribute("query") SearchQuery input, ModelAndView mav) throws TwitterException, InterruptedException, IOException {
    //input.setSince(input.getSince());
    //input.setUntil(input.getUntil());

    if(input.getQuery().equals("") || input.getQuery().equals(null)) {
      mav.setViewName("getTweetsInfo");
      mav.addObject("downloadLink",false);
      return mav;
    }
    String filename = (new Date()).toString().replaceAll("[^a-zA-z0-9]", "_");
    BufferedWriter writer = new BufferedWriter(new FileWriter("/tmp/" + filename + ".csv"));
    Configuration configuration = new ConfigurationBuilder()
        .setOAuthConsumerKey(tailingConsumerKey)
        .setOAuthConsumerSecret(tailingConsumerSecret)
        .setOAuthAccessToken(tailingAccessToken)
        .setOAuthAccessTokenSecret(tailingAccessTokenSecret)
        .build();

    Twitter twitter = new TwitterFactory(configuration).getInstance();

    List<TweetInfoModel> resultsList = new ArrayList<>();
    Query searchQuery = new Query(input.getQuery());
    if (input.getSince() != null) {
      searchQuery.setSince(input.getSince());
    }
    if (input.getUntil() != null) {
      searchQuery.setUntil(input.getUntil());
    }
    //searchQuery.setResultType(Query.POPULAR);
    searchQuery.setCount(100);

    System.out.println(searchQuery);
    // format : YYYY-MM-DD_hh:mm:ss_ZZZ
    searchQuery.setSince(input.getSince());
    searchQuery.setUntil(input.getUntil());




    for (int i = 0; i < 15; i++) {
      QueryResult result = twitter.search(searchQuery);
      for (Status status : result.getTweets()) {
        if (!status.getText().startsWith("RT")) {
          TweetInfoModel info = new TweetInfoModel();
          info.setUserPlofileImageUrl(status.getUser().getBiggerProfileImageURL());
          info.setId(status.getUser().getScreenName());
          info.setText(status.getText());
          info.setCreateAt(status.getCreatedAt());
          resultsList.add(info);

          writer.write(info.exportCSV());
          writer.newLine();
        }
      }

      if (result.getCount() < 99) break;

      /*
      try{
        Thread.sleep(3000);
      } catch (InterruptedException ie) {
        ie.printStackTrace();
      }
      */
      if (result.hasNext()) {
        searchQuery = result.nextQuery();
      } else {
        break;
      }
    }
    writer.close();
    mav.setViewName("getTweetsInfo");
    mav.addObject("resultList",resultsList);
    mav.addObject("filename",filename);
    mav.addObject("downloadLink",true);
    return mav;
  }

  @RequestMapping("/download/{filename}")
  ResponseEntity<byte[]> download(@PathVariable String filename) throws IOException {
    Path tempfile = Paths.get("/tmp/" + filename);
    if(!Files.exists(tempfile)) {
      exception.downloadFileDoesNotReadyException();
    }
    HttpHeaders header = new HttpHeaders();
    header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    header.add("Content-Disposition", "attachement;filename=\"" + filename + "\"");
    return new ResponseEntity<>(Files.readAllBytes(tempfile),header, HttpStatus.OK);
  }

}
