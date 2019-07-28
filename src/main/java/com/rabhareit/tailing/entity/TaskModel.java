package com.rabhareit.tailing.entity;

import java.sql.Date;

public class TaskModel {

  private Long id;

  private String title;

  private Date deadLine;

  private String memo;

  private String ownerid;

  public TaskModel() { }

  public TaskModel(String title, Date limit, String memo) {
    setTitle(title);
    setLimit(limit);
    setMemo(memo);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getLimit() {
    return deadLine;
  }

  public void setLimit(Date deadLine) {
    this.deadLine = deadLine;
  }


  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getOwnerid() {
    return ownerid;
  }

  public void setOwnerid(String ownerid) {
    this.ownerid = ownerid;
  }


}
