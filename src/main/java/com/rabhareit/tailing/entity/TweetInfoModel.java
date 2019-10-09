package com.rabhareit.tailing.entity;

import java.io.IOException;
import java.util.Date;

public class TweetInfoModel {

  String userPlofileImageUrl;

  String id;

  String text;

  Date createAt;

  public String getUserPlofileImageUrl() { return userPlofileImageUrl; }

  public void setUserPlofileImageUrl(String userPlofileImageUrl) { this.userPlofileImageUrl = userPlofileImageUrl; }

  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getText() { return text; }

  public void setText(String text) { this.text = text; }

  public Date getCreateAt() { return createAt; }

  public void setCreateAt(Date createAt) { this.createAt = createAt; }

  public String exportCSV() throws IOException {
    return (id + "," + text.replaceAll("\\n", " ") + "," + createAt);
  }
}
