package com.rabhareit.tailing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TaskModel {

  @Id
  @Column(name="id", nullable=false)
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @Column(name="title", nullable=false)
  private String title;

  @Column(name="deadLine", nullable=false)
  private String deadLine;

  /*	@Temporal(TemporalType.DATE)
  @Column(name="deadLine", nullable=false)
  private Date deadLine;
*/
  @Column(name="memo")
  private String memo;

  public TaskModel() {

  }

  public TaskModel(String title, String limit, String memo) {
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

  public String getLimit() {
    return deadLine;
  }

  public void setLimit(String deadLine) {
    this.deadLine = deadLine;
  }


  /*
  public Date getLimit() {
    return deadLine;
  }

  public void setLimit(Date deadLine) {
    this.deadLine = deadLine;
  }
*/
  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

}
