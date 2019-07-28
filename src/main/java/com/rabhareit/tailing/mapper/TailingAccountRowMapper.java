package com.rabhareit.tailing.mapper;

import com.rabhareit.tailing.entity.TailingAccount;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TailingAccountRowMapper implements RowMapper<TailingAccount> {

  @Override
  public TailingAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
    TailingAccount account = new TailingAccount();
    account.setId(rs.getLong("id"));
    account.setUsername(rs.getString("username"));
    account.setPasswd(rs.getString("passwd"));
    account.setAdmin(rs.getBoolean("admin"));
    account.setEnabled(rs.getBoolean("enabled"));
    return account;
  }
}
