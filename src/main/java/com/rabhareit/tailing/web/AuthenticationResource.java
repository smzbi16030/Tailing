package com.rabhareit.tailing.web;

import com.rabhareit.tailing.entity.TailingSocialUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import twitter4j.TwitterException;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/api/session")
public class AuthenticationResource {
/**
  //@Autowired
  AuthenticationManager authenticationManager;

  @RequestMapping(method=RequestMethod.GET)
  public TailingSocialUser session(Principal user) throws TwitterException {
    String name = user ==  null ? null : user.getName();
    return new TailingSocialUser(name);
  }

  @RequestMapping(method=RequestMethod.DELETE)
  public void logout(HttpSession session) {
    session.invalidate();
  }
  */
}
