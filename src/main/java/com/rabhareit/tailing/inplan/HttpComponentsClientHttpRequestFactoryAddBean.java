package com.rabhareit.tailing.inplan;

import org.apache.http.client.config.RequestConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

public class HttpComponentsClientHttpRequestFactoryAddBean extends HttpComponentsClientHttpRequestFactory {
  @Bean
  public RequestConfig mergeRequestConfiguration(RequestConfig rc) {
    return super.mergeRequestConfig(rc);
  }
}
