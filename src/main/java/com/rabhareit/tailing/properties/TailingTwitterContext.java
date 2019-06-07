package com.rabhareit.tailing.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TailingTwitterContext {
    @Value("${tailing_consumerKey}")
    private String oauthconsumerkey;

    @Value("${tailing_consumerSecret}")
    private String oauthconsumersecret;

    @Value("${tailing_accessToken}")
    private String oauthaccesstoken;

    @Value("${tailing_accessTokenSecret}")
    private String oauthaccesstokensecret;
}
