package com.rabhareit.tailing.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TailingSocialAccount {

  @Autowired
  PasswordEncoder encoder;

  private long tailingId;         //AccountId

  private long twitterId;          //Long

  private String userName;  //"@~~~"

  private String screenName;      //AccountName

  private String passwd;

  private boolean enabled;

  private boolean isAdmin;

  private String imgUrl;

  private String bannerUrl;

  public long getTailingId() {
    return tailingId;
  }

  public long getTwitterId() {
    return twitterId;
  }

  public String getUserName() {
    return userName;
  }

  public String getScreenName() {
    return screenName;
  }

  public String getPasswd() {
    return passwd;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  public String getImgUrl() { return imgUrl; }

  public String getBannerUrl() { return bannerUrl; }

  public void setTailingId(long tailingId) {
    this.tailingId = tailingId;
  }

  public void setTwitterId(long twitterId) {
    this.twitterId = twitterId;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setAdmin(boolean admin) {
    isAdmin = admin;
  }

  public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl; }

  public void setBannerUrl(String bannerUrl) { this.bannerUrl = bannerUrl; }

  public TailingSocialAccount() {
    enabled = true;
    isAdmin = false;
  }

  public TailingSocialAccount(long tailingId, long twitterId, String userName, String screenName, String passwd, String imgUrl, String bannerUrl) {
    this.tailingId = tailingId;
    this.twitterId = twitterId;
    this.userName = userName;
    this.screenName = screenName;
    this.passwd = encoder.encode(passwd);
    this.enabled = true;
    this.isAdmin = false;
    this.imgUrl = imgUrl;
    this.bannerUrl = bannerUrl;
  }


}
