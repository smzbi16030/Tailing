package com.rabhareit.tailing.web;

import com.rabhareit.tailing.entity.TemporaryAccount;
import com.rabhareit.tailing.repository.TemporaryAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SigninController {

  @Autowired
  TemporaryAccountRepository account;

  @RequestMapping("signin")
  public String accessLoginPage() {
    return "loginpage";
  }

  @RequestMapping("sig")
  public String accessRegistration() {
    return "signup";
  }

  @PostMapping("signup")
  public String insertNewUser(@RequestParam(name = "username", required = true) String username, @RequestParam(name = "passwd", required = true) String passwd) {
    PasswordEncoder pass = new BCryptPasswordEncoder();
    TemporaryAccount newAccount = new TemporaryAccount(username, pass.encode(passwd), false);
    account.saveAndFlush(newAccount);
    return "redirect:/home";
  }

  @RequestMapping("signout")
  public String accessLogoutPage() {
    return "signout";
  }

  @RequestMapping("delete/accountdlt/{id}")
  public String deleteAccount(@PathVariable Long id) {
    account.deleteById(id);
    return "redirect:/home";
  }
}