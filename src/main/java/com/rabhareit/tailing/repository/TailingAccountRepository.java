package com.rabhareit.tailing.repository;

import com.rabhareit.tailing.entity.TailingAccount;
import com.rabhareit.tailing.mapper.TailingAccountRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TailingAccountRepository {
  @Autowired
  JdbcTemplate jdbc;

  public TailingAccount getAccoutById(long id) {
    TailingAccountRowMapper mapper = new TailingAccountRowMapper();
    return jdbc.queryForObject("select id, username, passwd, enabled, admin from temporary_account where id = ?",mapper,id);
  }

  public TailingAccount getAccoutByUsername(String username) {
    TailingAccountRowMapper mapper = new TailingAccountRowMapper();
    return jdbc.queryForObject("select id, username, passwd, enabled, admin from temporary_account where username = ?",mapper,username);
  }

  public List<TailingAccount> getAllAccount() {
    TailingAccountRowMapper mapper = new TailingAccountRowMapper();
    return jdbc.query("select * from temporary_account",mapper);
  }

  public int insertAccount(TailingAccount account) {
    TailingAccountRowMapper mapper = new TailingAccountRowMapper();
    return jdbc.update("insert into temporary_account(username, passwd, enabled, admin) "
        + "values(?,?,?,?)",account.getUsername(),account.getPasswd(),true,false);
  }
}
