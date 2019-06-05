package com.rabhareit.tailing.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.firebase")
public class FirebaseProperties {
  @Getter
  private final Credential credential = new Credential();

  public static class Credential {
    @Getter
    @Setter
    private String path;
  }
}
