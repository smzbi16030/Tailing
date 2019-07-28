package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.TailingSocialAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import java.util.Collection;

public class TailingSocialUserDetails implements SocialUserDetails {

  //private static final long serialVersionUID = -1L;

  private TailingSocialAccount user;

  private Collection<GrantedAuthority> authorities;


  public TailingSocialUserDetails() {}

  public TailingSocialUserDetails(TailingSocialAccount account, Collection<GrantedAuthority> authorities) {
    // username = "@~~~",screen name = "user name",twitter id = "(Long)"
    this.user = account;
    this.authorities = authorities;
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  //DANGER!!
  @Override
  public String getUserId() { return String.valueOf( user.getTailingId() ); }

  @Override
  public String getPassword() {
    return user.getPasswd();
  }

  @Override
  public String getUsername() {
    return user.getUserName();
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
  public boolean isEnabled() {
    return user.isEnabled();
  }

}

