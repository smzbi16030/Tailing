package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.TailingAccount;
import com.rabhareit.tailing.entity.TailingSocialAccount;
import com.rabhareit.tailing.repository.TailingSocialAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Social ログイン時の userDetailService として利用
 * TODO JdbcTemplateを使った実装
 */

@Service
@Transactional
public class TailingSocialUserDetailsService implements SocialUserDetailsService {

  @Autowired
  TailingSocialAccountRepository repository;

  @Override
  public SocialUserDetails loadUserByUserId(String username) throws UsernameNotFoundException {
    if(username == null || "".equals(username)) {
      throw new UsernameNotFoundException("Username is empty");
    }

    System.out.println("["+username+"]:TailingSocialUserDetailsService.java:46");
    TailingSocialAccount account = Optional.ofNullable(repository.getAccoutByUsername(username))
        .orElseThrow( () -> new UsernameNotFoundException("User: " + username + " does not exist."));

    return new TailingSocialUserDetails(account,getAuthorities(account));
  }

  private Collection<GrantedAuthority> getAuthorities(TailingSocialAccount account) {
    if (account.isAdmin()) {
      return AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
    } else {
      return AuthorityUtils.createAuthorityList("ROLE_USER");
    }
  }
}
