package com.rabhareit.tailing.web;

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