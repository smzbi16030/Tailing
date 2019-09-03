package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.UserConnection;
import com.rabhareit.tailing.repository.UserConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Social ログイン時の userDetailService として利用
 *
 */

@Service
@Transactional
public class TailingSocialUserDetailsService implements SocialUserDetailsService {

  @Autowired
  UserConnectionRepository userConnection;

  @Override
  public SocialUserDetails loadUserByUserId(String username) throws UsernameNotFoundException {
    if(username == null || "".equals(username)) {
      throw new UsernameNotFoundException("Username is empty");
    }

    UserConnection account = Optional.ofNullable(userConnection.getConnectionById(username))
        .orElseThrow( () -> new UsernameNotFoundException("User: " + username + " does not exist."));

    return new UserConnectionDetails(account,AuthorityUtils.createAuthorityList("ROLE_USER"));
  }

  private Collection<GrantedAuthority> getAuthorities() {
    /**
     * if you can, implement like this...
     *
     *     if (account.isAdmin()) {
     *       return AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
     *     } else {
     *       return AuthorityUtils.createAuthorityList("ROLE_USER");
     *     }
     *
     */
    return null;
  }
}
