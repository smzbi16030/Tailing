package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.TailingSocialAccount;
import com.rabhareit.tailing.repository.TailingSocialAccountRepository;
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
  TailingSocialAccountRepository socialRepository;

  @Autowired
  UserConnectionRepository connectionRepository;

  @Autowired
  TweetCountRepository tweetcount;

  //ユーザー情報がDB上に存在しない場合実行. 暗黙的サインアップ
  @Override
  public String execute(Connection<?> connection) {
    UserProfile profile = connection.fetchUserProfile();
    String username = profile.getUsername();
    try{
      //tweetcountテーブルにセット
      TailingSocialAccount newAccount = getAccountInfo(username);
      socialRepository.insertAccount(newAccount);
      setCountor(newAccount);
    } catch (TwitterException e) {
      e.printStackTrace();
      // when null is returned, nothing is inserted to "userConnection".
      return null;
    }
    return username;
  }

  TailingSocialAccount getAccountInfo(String username) throws TwitterException {
    Configuration configuration = new ConfigurationBuilder()
      .setOAuthConsumerKey(tailingConsumerKey)
      .setOAuthConsumerSecret(tailingConsumerSecret)
      .setOAuthAccessToken(tailingAccessToken)
      .setOAuthAccessTokenSecret(tailingAccessTokenSecret)
      .build();
    Twitter twitter = new TwitterFactory(configuration).getInstance();
    User user = twitter.showUser(username);

    TailingSocialAccount account = new TailingSocialAccount();
    account.setTailingId(user.getId());
    account.setPasswd(user.getName());
    account.setScreenName(user.getName());
    account.setUserName(user.getScreenName());
    account.setTwitterId(user.getId());
    account.setImageUrl(user.getProfileImageURL());
    account.setBannerUrl(user.getProfileBannerURL());
    return account;
  }

  void setCountor(TailingSocialAccount account) {tweetcount.setCountor(account.getTwitterId(), account.getUserName(), 0);}

}
