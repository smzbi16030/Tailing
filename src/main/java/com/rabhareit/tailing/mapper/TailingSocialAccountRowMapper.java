package com.rabhareit.tailing.mapper;

import com.rabhareit.tailing.entity.TailingSocialAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TailingSocialAccountRowMapper implements RowMapper<TailingSocialAccount> {

  @Override
  public TailingSocialAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
    TailingSocialAccount account = new TailingSocialAccount();
    account.setTailingId(rs.getLong("tailing_id"));
    account.setPasswd(rs.getString("encoded_passwd"));
    account.setScreenName(rs.getString("screen_name"));
    account.setUserName(rs.getString("user_name"));
    account.setTwitterId(rs.getLong("twitter_id"));
    account.setImgUrl(rs.getString("img_url"));
    account.setBannerUrl(rs.getString("banner_url"));
    return account;
  }
}
