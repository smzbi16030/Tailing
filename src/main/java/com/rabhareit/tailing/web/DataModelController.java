package com.rabhareit.tailing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DataModelController {

  @Autowired
  private JdbcTemplate jdbc;

  @PostMapping("signup")
  public String insertNewUser(@RequestParam(name = "username", required = true) String username, @RequestParam(name = "passwd", required = true) String passwd) {
    PasswordEncoder pass = new BCryptPasswordEncoder();
    int result = jdbc.update("insert into temporary_account(username, passwd, enabled, admin) values (?,?,?,?)",username,pass.encode(passwd),true,true);
    //TailingAccount newAccount = new TailingAccount(username, pass.encode(passwd), false);
    //account.saveAndFlush(newAccount);
    return "redirect:/home";
  }

  @RequestMapping("delete/accountdlt/{id}")
  public String deleteAccount(@PathVariable Long id) {
    jdbc.update("delete from temporary_account where id = ?",id);
    return "redirect:/home";
  }

}
