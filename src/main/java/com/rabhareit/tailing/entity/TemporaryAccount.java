package com.rabhareit.tailing.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="Temporary_Account")
public class TemporaryAccount {

  public TemporaryAccount() {}

  public TemporaryAccount(String username, String passwd, boolean isAdomin) {
    setUsername(username);
    setPasswd(passwd);
    setEnabled(true);
    setAdmin(isAdomin);
  }

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(nullable=false, unique=true)
  private Long id;

  //Use username(x ScreenName) on Twitter (@~~~)
  @Column(nullable=false, unique=true)
  private String username;

  @Column(nullable=false)
  private String passwd;

  @Column(nullable=false)
  private boolean enabled;

  @Column(nullable=false)
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
