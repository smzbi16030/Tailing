package com.rabhareit.tailing.web;

import com.rabhareit.tailing.entity.TemporaryAccount;
import com.rabhareit.tailing.repository.TemporaryAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SigninController {

  @RequestMapping("signin")
  public ModelAndView accessLoginPage(ModelAndView mav) {
    mav.setViewName("loginpage");
    return mav;
  }

  @RequestMapping("sig")
  public ModelAndView accessRegistration(ModelAndView mav) {
    mav.setViewName("signup");
    return mav;
  }

  @RequestMapping("signout")
  public ModelAndView accessLogoutPage(ModelAndView mav) {
    mav.setViewName("signout");
    return mav;
  }
}