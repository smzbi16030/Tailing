package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.UserConnection;
import com.rabhareit.tailing.repository.TweetCountRepository;
import com.rabhareit.tailing.repository.UserConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

@Component
@Transactional
public class ConnectionSignUpImpl implements ConnectionSignUp {

  @Value("${app.tailing-consumer-key}")
  private String tailingConsumerKey;

  @Value("${app.tailing-consumer-secret}")
  private String tailingConsumerSecret;

  @Value("${app.tailing-access-token}")
  private String tailingAccessToken;

  @Value("${app.tailing-access-token-secret}")
  private String tailingAccessTokenSecret;

  @Autowired
  UserConnectionRepository userConnectionRepository;

  @Autowired
  TweetCountRepository tweetcount;


  @Override
  public String execute(Connection<?> connection) {
    UserProfile profile = connection.fetchUserProfile();
    String username = profile.getUsername();
    try{
      userConnectionRepository.insertBannerUrl(getBannerUrl(username));
    } catch (TwitterException e) {
      e.printStackTrace();
    }
    //tweetcountテーブルにセット
    setCountor(userConnectionRepository.getConnectionById(username));

    return username;
  }

  String getBannerUrl(String username) throws TwitterException {
    Configuration configuration = new ConfigurationBuilder()
      .setOAuthConsumerKey(tailingConsumerKey)
      .setOAuthConsumerSecret(tailingConsumerSecret)
      .setOAuthAccessToken(tailingAccessToken)
      .setOAuthAccessTokenSecret(tailingAccessTokenSecret)
      .build();
    Twitter twitter = new TwitterFactory(configuration).getInstance();
    User user = twitter.showUser(username);
    return user.getProfileBannerURL();
  }

  void setCountor(UserConnection account) {
    tweetcount.setCountor(account.getProvideruserid(), account.getUserid(),0);
  }

}
