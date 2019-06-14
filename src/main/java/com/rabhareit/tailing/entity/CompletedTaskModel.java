package com.rabhareit.tailing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.time.LocalDateTime;

public class CompletedTaskModel {
  @Id
  @Column(name="id", nullable=false)
  private Long id;

  @Column(name="completed", nullable=false)
  private Date completedDate;

  @Column(name="title", nullable=false)
  private String title;

  @Column(name="deadLine", nullable=false)
  private Date deadLine;

  @Column(name="memo")
  private String memo;

  public CompletedTaskModel() { }

  public CompletedTaskModel(Long id, String title, Date deadLine, String memo) {
    this.id = id;
    this.title = title;
    this.deadLine = deadLine;
    this.memo = memo;
  }

  public CompletedTaskModel(TaskModel task) {
    this.id = task.getId();
    this.title = task.getTitle();
    this.deadLine = task.getLimit();
    this.memo = task.getMemo();
  }

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

  public String getTitle() { return title; }

  public void setTitle(String title) { this.title = title; }

  public Date getDeadLine() { return deadLine; }

  public void setDeadLine(Date deadLine) { this.deadLine = deadLine; }

  public Date getCompletedDate() { return completedDate; }

  public void setCompletedDate(Date completedDate) { this.completedDate = completedDate; }

  public String getMemo() { return memo; }

  public void setMemo(String memo) { this.memo = memo; }
}
