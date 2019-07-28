package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.TailingAccount;
import com.rabhareit.tailing.entity.TailingSocialAccount;
import com.rabhareit.tailing.repository.TailingAccountRepository;
import com.rabhareit.tailing.repository.TailingSocialAccountRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    /*
    * Socialログイン時のユーザー追加(自システムのDBに)
    * user id(@~~~)でログインしてもらう.
    * -> username = userid(@~~~)
    * */


  @Value("${app.tailing-consumer-key}")
  private String tailingConsumerKey;

  @Value("${app.tailing-consumer-secret}")
  private String tailingConsumerSecret;

  @Value("${app.tailing-access-token}")
  private String tailingAccessToken;

  @Value("${app.tailing-access-token-secret}")
  private String tailingAccessTokenSecret;

  @Autowired
    TailingAccountRepository repository;

    @Autowired
    TailingSocialAccountRepository socialRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String execute(Connection<?> connection) {
      UserProfile profile = connection.fetchUserProfile();
      String username = profile.getUsername();
      try{
        long userid = getTwitterId(username);
        System.out.println("パスワード決めてもらう");
        String pw = RandomStringUtils.randomAlphanumeric(10);
        System.out.println(pw);
        TailingAccount account = new TailingAccount(username, passwordEncoder.encode(pw),false);
        TailingSocialAccount socialAccount = new TailingSocialAccount(userid,userid,username,profile.getName(),passwordEncoder.encode(pw),false);
        repository.insertAccount(account);
        socialRepository.insertAccount(socialAccount);
      } catch (TwitterException e) {
        e.printStackTrace();
      }
      return username;
    }

    long getTwitterId(String username) throws TwitterException {
      Configuration configuration = new ConfigurationBuilder()
          .setOAuthConsumerKey(tailingConsumerKey)
          .setOAuthConsumerSecret(tailingConsumerSecret)
          .setOAuthAccessToken(tailingAccessToken)
          .setOAuthAccessTokenSecret(tailingAccessTokenSecret)
          .build();
      Twitter twitter = new TwitterFactory(configuration).getInstance();
      User user = twitter.showUser(username);
      return user.getId();
    }
}
