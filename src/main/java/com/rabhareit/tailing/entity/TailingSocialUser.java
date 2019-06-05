package com.rabhareit.tailing.entity;

import org.apache.commons.lang3.mutable.Mutable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;

@Entity(name = "TailingSocialUser")
public class TailingSocialUser extends SocialUser {

  @Id
  @Column
  private long tailingId;         //AccountId

  @Column
  private String userId;          //"@~~~"

  @Column
  private String screenName;      //AccountName

  @Column
  private String encodedPasswd;

  @Column
  private boolean enabled;

  @Column
  private boolean accountNonExpired;

  @Column
  private boolean credentialsNonExpired;

  @Column
  private boolean accountNonLocked;

  @Column
  private GrantedAuthority authorities;

  @Column
  private boolean isAdmin;

  public long getTailingId() {
    return tailingId;
  }

  public String getUserId() {
    return userId;
  }

  public String getScreenName() {
    return screenName;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  //SMELL!!!!!!
  public TailingSocialUser() {
    super(null,null,true,true,true,true,null);
    this.isAdmin = false;
  }

  public TailingSocialUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, true, true, true, true, authorities);
    // username means "twitter id(ling)","user id(@~~~)","screen name"?
    this.isAdmin = false;
  }

  public void setTailingId(long tailingId) {
    this.tailingId = tailingId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setScreenName(String screenName) {
    this.screenName = screenName;
  }

  public void setEncodedPasswd(String encodedPasswd) {
    this.encodedPasswd = encodedPasswd;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setAdmin(boolean admin) {
    isAdmin = admin;
  }
}