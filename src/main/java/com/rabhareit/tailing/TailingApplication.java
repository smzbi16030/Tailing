package com.rabhareit.tailing;

import com.rabhareit.tailing.properties.FirebaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
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
public class TailingApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(TailingApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(TailingApplication.class);
  }

}
