package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.UserConnection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUserDetails;

import java.util.Collection;

public class UserConnectionDetails implements SocialUserDetails {

  //private static final long serialVersionUID = -1L;

  private UserConnection user;

  private Collection<GrantedAuthority> authorities;


  public UserConnectionDetails() {}

  public UserConnectionDetails(UserConnection account, Collection<GrantedAuthority> authorities) {
    this.user = account;
    this.authorities = authorities;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String getPassword() { return null; }

  @Override
  public String getUserId() { return String.valueOf( user.getProvideruserid() ); }

  // return @"hogehoge"
  @Override
  public String getUsername() {
    return user.getUserid();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() { return true; }

}

