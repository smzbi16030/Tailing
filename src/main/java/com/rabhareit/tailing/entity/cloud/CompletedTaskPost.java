package com.rabhareit.tailing.entity.cloud;

import com.rabhareit.tailing.entity.TaskModel;

import java.sql.Date;

public class CompletedTaskPost {
  private Long id;

  private Date completedDate;

  private String title;

  private Date deadLine;

  private String memo;

  public CompletedTaskPost(Long id, String title, Date deadLine, String memo) {
    this.id = id;
    this.title = title;
    this.deadLine = deadLine;
    this.memo = memo;
  }

  public CompletedTaskPost(TaskPost task) {
    this.id = task.getId();
    this.title = task.getTitle();
    this.deadLine = task.getDeadLine();
    this.memo = task.getMemo();
  }


}
