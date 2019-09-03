package com.rabhareit.tailing.mapper;

import com.rabhareit.tailing.entity.UserConnection;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserConnectionRowMapper implements RowMapper<UserConnection> {

  @Override
  public UserConnection mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    UserConnection account = new UserConnection();
    account.setUserid(resultSet.getString("userid"));
    account.setProviderid(resultSet.getString("providerid"));
    account.setProvideruserid(resultSet.getLong("provideruserid"));
    account.setRank(resultSet.getInt("rank"));
    account.setDisplayname(resultSet.getString("displayname"));
    account.setProfileurl(resultSet.getString("profileurl"));
    account.setImageurl(resultSet.getString("imageurl"));
    account.setBannerurl(resultSet.getString("bannerurl"));
    account.setAccesstoken(resultSet.getString("accesstoken"));
    account.setSecret(resultSet.getString("secret"));
    account.setRefreshtoken(resultSet.getString("refreshtoken"));
    account.setExpiretime(resultSet.getLong("expiretime"));
    return account;
  }
}
