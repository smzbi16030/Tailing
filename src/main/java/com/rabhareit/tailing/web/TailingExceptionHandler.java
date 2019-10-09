package com.rabhareit.tailing.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

public class TailingExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(Exception.class)
  public ModelAndView downloadFileDoesNotReadyException() {
    ModelAndView mav = new ModelAndView();
    mav.setViewName("download/404");
    return mav;
  }

}
