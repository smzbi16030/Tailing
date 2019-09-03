package com.rabhareit.tailing.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/session")
public class AuthenticationResource {
/**
  //@Autowired
  AuthenticationManager authenticationManager;

  @RequestMapping(method=RequestMethod.GET)
  public UserConnectionDetails session(Principal user) throws TwitterException {
    String name = user ==  null ? null : user.getName();
    return new UserConnectionDetails(name);
  }

  @RequestMapping(method=RequestMethod.DELETE)
  public void logout(HttpSession session) {
    session.invalidate();
  }
  */
}
