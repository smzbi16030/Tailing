package com.rabhareit.tailing.entity;

public class TailingAccount {

  public TailingAccount() {}

  public TailingAccount(String username, String passwd, boolean isAdomin) {
    setUsername(username);
    setPasswd(passwd);
    setEnabled(true);
    setAdmin(isAdomin);
  }

  private Long id;

  //Use username(x ScreenName) on Twitter (@~~~)
  private String username;

  private String passwd;

  private boolean enabled;

  private boolean admin;

  public Long getId() { return id; }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPasswd() {
    return passwd;
  }

  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }
}
