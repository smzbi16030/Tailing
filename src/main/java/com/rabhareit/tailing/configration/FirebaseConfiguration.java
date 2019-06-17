package com.rabhareit.tailing.configration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.rabhareit.tailing.service.CompletionListenerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class FirebaseConfiguration {

  @Value("${app.tailing-firebase-json}")
  String tailingFirebaseJson;

  @Value("${app.tailing-firebase-url}")
  String tailingFirebaseUrl;

  @Autowired
  ResourceLoader loader;

  CompletionListenerImpl listener;

  public void configuration() throws Exception {
    Resource resource = loader.getResource("classpath:"+tailingFirebaseJson);
    FirebaseOptions options;
    try (InputStream serviceAccount = resource.getInputStream()) {
      options = new FirebaseOptions.Builder()
          .setCredentials(GoogleCredentials.fromStream(serviceAccount))
          .setDatabaseUrl(tailingFirebaseUrl)
          .build();
    }

    FirebaseApp.initializeApp(options);

/**
 *  Sample code for realtimeDatabase...
 *
 *     DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
 *     reference.child("key1").setValue("アンパンマンと",listener);
 *     reference.child("key2").setValue("イキリみょうがマン",listener);
 *
 *     TODO CompletionListenerの使い方
 *
 */

  }


}
