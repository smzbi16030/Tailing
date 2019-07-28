package com.rabhareit.tailing.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class SigninController {

  @GetMapping(path = "signin")
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