package com.rabhareit.tailing.entity.cloud;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountsPost {

  private String userName;

  private String displayName;

  private String passwd;

  private boolean admin;

  private boolean enable;

  public AccountsPost(String username, String displayName,String passwd, boolean admin, boolean enable){
    this.userName = username;
    this.displayName = displayName;
    this.passwd = passwd;
    this.admin = admin;
    this.enable = enable;
  }

}
