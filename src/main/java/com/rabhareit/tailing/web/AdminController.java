package com.rabhareit.tailing.web;

import com.rabhareit.tailing.repository.TweetCountRepository;
import com.rabhareit.tailing.repository.UserConnectionRepository;
import com.rabhareit.tailing.service.TailingStausListenerImpl;
import com.rabhareit.tailing.service.TimelineMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/config")
public class AdminController {

    @Value("${app.tailing-consumer-key}")
    private String tailingConsumerKey;

    @Value("${app.tailing-consumer-secret}")
    private String tailingConsumerSecret;

    @Value("${app.tailing-access-token}")
    private String tailingAccessToken;

    @Value("${app.tailing-access-token-secret}")
    private String tailingAccessTokenSecret;

    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    UserConnectionRepository userConnection;

    @Autowired
    TweetCountRepository counter;

    TailingStausListenerImpl stausListener;

    @RequestMapping("/stream/stop")
    public String streamStop() {
        return "";
    }


    // 中身だけ別モジュールに？
    @RequestMapping("/stream")
    public String administratorConfiguration() throws TwitterException {
        Configuration configuration = new ConfigurationBuilder()
            .setOAuthConsumerKey(tailingConsumerKey)
            .setOAuthConsumerSecret(tailingConsumerSecret)
            .setOAuthAccessToken(tailingAccessToken)
            .setOAuthAccessTokenSecret(tailingAccessTokenSecret)
            .build();
        Twitter twitter = new TwitterFactory(configuration).getInstance();
        TwitterStream twStream = new TwitterStreamFactory(configuration).getInstance();

        // MEMO ストリームの開始
        twStream.addListener(stausListener.getStattusLisener(twitter));
        FilterQuery filter = new FilterQuery();
        filter.follow(userConnection.getUserIdArray());
        twStream.filter(filter);

        return "redirect:/home";
    }

}
