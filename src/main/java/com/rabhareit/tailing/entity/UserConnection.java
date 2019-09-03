package com.rabhareit.tailing.entity;

public class UserConnection {
  String userid;

  String providerid;

  long provideruserid;

  int rank;

  String displayname;

  String profileurl;

  String imageurl;

  String bannerurl;

  String accesstoken;

  String secret;

  String refreshtoken;

  long expiretime;

  public String getUserid() { return userid; }

  public void setUserid(String userid) { this.userid = userid; }

  public String getProviderid() { return providerid; }

  public void setProviderid(String providerid) { this.providerid = providerid; }

  public long getProvideruserid() { return provideruserid; }

  public void setProvideruserid(long provideruserid) { this.provideruserid = provideruserid; }

  public int getRank() { return rank; }

  public void setRank(int rank) { this.rank = rank; }

  public String getDisplayname() { return displayname; }

  public void setDisplayname(String displayname) { this.displayname = displayname; }

  public String getProfileurl() { return profileurl; }

  public void setProfileurl(String profileurl) { this.profileurl = profileurl; }

  public String getImageurl() { return imageurl; }

  public void setImageurl(String imageurl) { this.imageurl = imageurl; }

  public String getBannerurl() { return bannerurl; }

  public void setBannerurl(String bannerurl) { this.bannerurl = bannerurl; }

  public String getAccesstoken() { return accesstoken; }

  public void setAccesstoken(String accesstoken) { this.accesstoken = accesstoken; }

  public String getSecret() { return secret; }

  public void setSecret(String secret) { this.secret = secret; }

  public String getRefreshtoken() { return refreshtoken; }

  public void setRefreshtoken(String refreshtoken) { this.refreshtoken = refreshtoken; }

  public long getExpiretime() { return expiretime; }

  public void setExpiretime(long expiretime) { this.expiretime = expiretime; }

  public UserConnection() {

  }
}
