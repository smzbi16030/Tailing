package com.rabhareit.tailing.configration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.rabhareit.tailing.properties.FirebaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@EnableConfigurationProperties(FirebaseProperties.class)
public class FirebaseConfiguration {
  private final FirebaseProperties properties;

  private GoogleCredentials credentials;

  public FirebaseConfiguration(FirebaseProperties properties) {
    this.properties = properties;
  }

  @PostConstruct
  public synchronized void initialize() {
    if (credentials != null) {
      return;
    }

    try (InputStream serviceAccount = new FileInputStream(properties.getCredential().getPath())) {
      //credentials = GoogleCredentials.fromStream(serviceAccount);
      FirebaseOptions options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .setDatabaseUrl("https://tailing-ysk43z.firebaseio.com/")
          .build();
      FirebaseApp.initializeApp(options);
    } catch (IOException e) {
      //Googleなんとかエラー
      e.printStackTrace();
    }
  }

  @Bean
  public GoogleCredentials getCredentials() {
    return credentials;
  }
}
