package com.rabhareit.tailing.web;

import javax.servlet.http.Cookie;
import java.util.Date;

public class TailingCookie extends Cookie {

  public TailingCookie(String name, String value) {
    super(name, value);
  }

  private Date expires;

  public Date getExpires() {
    return expires;
  }

  public void setExpires(Date expires) {
    this.expires = expires;
  }

  public void printAll() {
    System.out.println("Name: "+ super.getName()+", Value: "+super.getValue()+", Comment: "+super.getComment()
                      +", Dmain: "+super.getDomain()+", Path: "+super.getPath()+", Max-Age: "+super.getMaxAge()
                      +", Secure: "+super.getSecure() + ", Version: " + super.getVersion());
  }
}
