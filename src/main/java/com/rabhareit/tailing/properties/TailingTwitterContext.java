package com.rabhareit.tailing.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "tailing")
public class TailingTwitterContext {
    //大文字はダメみたいです(小文字+"-"に変換される)
    //"_"もだめっぽい
    private String oauthconsumerkey;

    private String oauthconsumersecret;

    private String oauthaccesstoken;

    private String oauthaccesstokensecret;
}
