package com.rabhareit.tailing.repository;

import com.rabhareit.tailing.entity.TailingSocialAccount;
import com.rabhareit.tailing.mapper.TailingSocialAccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TailingSocialAccountRepository {
  @Autowired
  JdbcTemplate jdbc;

  public TailingSocialAccount getAccoutByTailingId(long tailingId) {
    TailingSocialAccountRowMapper mapper = new TailingSocialAccountRowMapper();
    return jdbc.queryForObject("select * from tailing_social_user where tailing_id = ?",mapper,tailingId);
  }

  public TailingSocialAccount getAccoutByUsername(String username) {
    TailingSocialAccountRowMapper mapper = new TailingSocialAccountRowMapper();
    return jdbc.queryForObject("select * from tailing_social_user where user_name = ?",mapper,username);
  }

  public List<TailingSocialAccount> getAllAccount() {
    TailingSocialAccountRowMapper mapper = new TailingSocialAccountRowMapper();
    return jdbc.query("select * from tailing_social_user",mapper);
  }

  public int insertAccount(TailingSocialAccount account) {
    TailingSocialAccountRowMapper mapper = new TailingSocialAccountRowMapper();
    return jdbc.update("insert into tailing_social_user(tailing_id, account_non_expired, account_non_locked, credentials_non_expired, enabled, encoded_passwd, is_admin, screen_name, user_name, twitter_id, img_url, banner_url)"
            +"values(?,?,?,?,?,?,?,?,?,?,?,?)" ,
        account.getTailingId(),true,true,true,true,account.getPasswd(),false,account.getScreenName(),account.getUserName(),account.getTwitterId(),account.getImgUrl(), account.getBannerUrl());
  }

  public Long[] getUserIdArray() {
    TailingSocialAccountRowMapper mapper = new TailingSocialAccountRowMapper();
    List<TailingSocialAccount> userList = jdbc.query("select * from tailing_social_user",mapper);
    return userList.stream()
                   .map( user -> user.getTwitterId())
                   .collect(Collectors.toList())
                   .toArray(new Long[0]);
  }
}
