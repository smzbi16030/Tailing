package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.TailingAccount;
import com.rabhareit.tailing.repository.TailingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * 与えられたユーザー名を用いてuserDetailsを取得し返却するメソッド.
 */
@Service
public class TailingUserDetailsService implements UserDetailsService {

  @Autowired
  private JdbcTemplate jdbc;

  @Autowired
  TailingAccountRepository repository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username == null || "".equals(username)) {
      throw new UsernameNotFoundException("Username is empty");
    }

    TailingAccount account = Optional.ofNullable(repository.getAccoutByUsername(username))
                                     .orElseThrow( () -> new UsernameNotFoundException("User: "+ username +" does not exist."));

    UserDetails userDetails = new TailingUserDetails(account,getAuthorities(account));

    return userDetails;
  }

  private Collection<GrantedAuthority> getAuthorities(TailingAccount account){

    if(account.isAdmin()){
      return AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER");
    }else{
      return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

  }
}