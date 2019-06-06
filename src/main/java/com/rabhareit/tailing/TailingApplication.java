package com.rabhareit.tailing;

import com.rabhareit.tailing.properties.FirebaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
/*
@PropertySources({
  @PropertySource("classpath:application.properties"),
  @PropertySource("classpath:twitter.properties")
})
*/
//@EnableConfigurationProperties({
    //FirebaseProperties.class,
    //SocialConfiguration.class
//})
public class TailingApplication {

  public static void main(String[] args) {
    SpringApplication.run(TailingApplication.class, args);

  }

}
