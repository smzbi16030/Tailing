package com.rabhareit.tailing.configration;

import com.rabhareit.tailing.entity.TailingSocialUser;
import com.rabhareit.tailing.properties.TailingTwitterContext;
import com.rabhareit.tailing.repository.TailingSocialUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.encrypt.Encryptors;
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

  @Autowired
  DataSource dataSource;

  @Autowired
  TailingTwitterContext context;

  @Autowired
  TailingSocialUserRepository tailingSocialUserRepository;

  //@Autowired
  //ConnectionSignUpImpl connectionSignUpImpl;

  @Override
  public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
    cfConfig.addConnectionFactory(new TwitterConnectionFactory(
      context.getOauthconsumerkey(),context.getOauthconsumersecret()
    ));
  }



  @Override
  public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
    return new JdbcUsersConnectionRepository(dataSource,connectionFactoryLocator, Encryptors.noOpText());
  }

  @Override
  public UserIdSource getUserIdSource() {
    return new AuthenticationNameUserIdSource();
  }
}
