package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.TemporaryAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TemporaryAccountService {

  @Autowired
  private JdbcTemplate jdbc;

  String[] arg = new String[1];

  public TemporaryAccount getAccountById(Long id) {
    return jdbc.queryForObject("select * from temporary_account where id = ?",arg,TemporaryAccount.class);
  }

  public void saveNewAccount(TemporaryAccount account) {
    //TODO jdbcTemplate を使った実装
  }

  public void deleteById(Long id) {
    //TODO jdbcTemplate を使った実装
  }
}
