package com.rabhareit.tailing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class CompletedTaskModel {
  @Id
  @Column(name="id", nullable=false)
  private Long id;

  @Column(name="title", nullable=false)
  private String title;

  @Column(name="deadLine", nullable=false)
  private String deadLine;

  @Column(name="completed", nullable=false)
  private String completedDate;

  @Column(name="memo")
  private String memo;

  public CompletedTaskModel() { }

  public CompletedTaskModel(Long id,String title,String deadLine,String completedDate,String memo) {
    this.id = id;
    this.title = title;
    this.deadLine = deadLine;
    this.completedDate = completedDate;
    this.memo = memo;
  }

  public CompletedTaskModel(TaskModel task) {
    this.id = task.getId();
    this.title = task.getTitle();
    this.deadLine = task.getLimit();
    this.memo = task.getMemo();
    this.completedDate = String.valueOf(LocalDateTime.now());
  }

  public Long getId() { return id; }

  public void setId(Long id) { this.id = id; }

  public String getTitle() { return title; }

  public void setTitle(String title) { this.title = title; }

  public String getDeadLine() { return deadLine; }

  public void setDeadLine(String deadLine) { this.deadLine = deadLine; }

  public String getCompletedDate() { return completedDate; }

  public void setCompletedDate(String completedDate) { this.completedDate = completedDate; }

  public String getMemo() { return memo; }

  public void setMemo(String memo) { this.memo = memo; }
}
