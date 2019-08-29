package com.rabhareit.tailing.mapper;

import com.rabhareit.tailing.entity.TailingSocialAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TailingSocialAccountRowMapper implements RowMapper<TailingSocialAccount> {

  @Override
  public TailingSocialAccount mapRow(ResultSet resultSet, int i) throws SQLException {
    TailingSocialAccount account = new TailingSocialAccount();
    account.setTailingId(resultSet.getLong("tailing_id"));
    account.setTwitterId(resultSet.getLong("twitter_id"));
    account.setUserName(resultSet.getString("user_name"));
    account.setScreenName(resultSet.getString("screen_name"));
    account.setPasswd(resultSet.getString("encoded_passwd"));
    account.setEnabled(resultSet.getBoolean("enabled"));
    account.setAdmin(resultSet.getBoolean("is_admin"));
    account.setImgUrl(resultSet.getString("img_url"));
    account.setBannerUrl(resultSet.getString("banner_url"));
    return account;
  }
}
