package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.TemporaryAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class TemporaryAccountDetailServiceImple implements UserDetailsService {

  @Autowired
  private JdbcTemplate jdbc;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username == null || "".equals(username)) {
      throw new UsernameNotFoundException("Username is empty");
    }
    String[] arg = new String[1];
    arg[0] = username;
    TemporaryAccount account = jdbc.queryForObject("select * from temporary_account where username = ?",arg,TemporaryAccount.class);
    if (account == null) {
      throw new UsernameNotFoundException("User not found: " + username);
    }

    if (!account.isEnabled()) {
      throw new UsernameNotFoundException("User not found: " + username);
    }

    UserDetails userDetails = new TemporaryAccountDetails(account,getAuthorities(account));

    return userDetails;
  }

  private Collection<GrantedAuthority> getAuthorities(TemporaryAccount account){

    if(account.isAdmin()){
      return AuthorityUtils.createAuthorityList("ROLE_ADMIN","ROLE_USER");
    }else{
      return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

  }

  @Transactional
  public void registerAdmin(String username, String password) {
    jdbc.update("insert into temporary_account values (?,?,?,?)",username,passwordEncoder.encode(password),true,true);
  }

  @Transactional
  public void registerUser(String username, String password) {
    jdbc.update("insert into temporary_account values (?,?,?,?)",username,passwordEncoder.encode(password),true,false);
  }
}