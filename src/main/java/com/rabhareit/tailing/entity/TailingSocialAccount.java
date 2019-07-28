package com.rabhareit.tailing.entity;

public class TailingSocialAccount {
  private long tailingId;         //AccountId

  private long twitterId;          //Long

  private String userName;  //"@~~~"

  private String screenName;      //AccountName

  private String passwd;

  private boolean enabled;

  private boolean isAdmin;

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

  public TailingSocialAccount() {}

  public TailingSocialAccount(long tailingId, long twitterId, String userName, String screenName, String passwd, boolean isAdmin) {
    this.tailingId = tailingId;
    this.twitterId = twitterId;
    this.userName = userName;
    this.screenName = screenName;
    this.passwd = passwd;
    this.enabled = true;
    this.isAdmin = isAdmin;
  }
}