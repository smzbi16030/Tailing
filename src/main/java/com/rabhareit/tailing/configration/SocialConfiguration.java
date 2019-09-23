package com.rabhareit.tailing.configration;

import com.rabhareit.tailing.service.ConnectionSignUpImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;

import javax.sql.DataSource;

@Configuration
@EnableSocial
public class SocialConfiguration extends SocialConfigurerAdapter {

  @Value("${app.tailing-consumer-key}")
  private String tailingConsumerKey;

  @Value("${app.tailing-consumer-secret}")
  private String tailingConsumerSecret;

  @Autowired
  DataSource source;



  @Autowired
  @Qualifier("textEncryptor")
  TextEncryptor textEncryptor;

  @Autowired
  ConnectionSignUpImpl connectionSignUpImpl;

  @Override
  public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
    connectionFactoryConfigurer.addConnectionFactory(new TwitterConnectionFactory(tailingConsumerKey,tailingConsumerSecret));
  }

  @Override
  public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
    JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(source,connectionFactoryLocator,textEncryptor);
    repository.setConnectionSignUp(connectionSignUpImpl);
    return repository;
  }

  public UserIdSource getUserIdSource() {
    return new AuthenticationNameUserIdSource();
  }
}
