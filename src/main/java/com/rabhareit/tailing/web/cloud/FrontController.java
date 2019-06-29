package com.rabhareit.tailing.web.cloud;

import com.google.firebase.database.*;
import com.rabhareit.tailing.entity.cloud.TaskPost;
import com.rabhareit.tailing.service.TailingUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Controller
public class FrontController {

  TailingUtil tutil = new TailingUtil();

  @RequestMapping("/flist")
  ModelAndView accessList(ModelAndView mav) {
    mav.setViewName("ToDoList");

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    return mav;
  }

}
