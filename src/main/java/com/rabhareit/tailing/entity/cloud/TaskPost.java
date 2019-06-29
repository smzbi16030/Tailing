package com.rabhareit.tailing.entity.cloud;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class TaskPost {

  private Long id;

  private String title;

  private Date deadLine;

  private String memo;

  public TaskPost(String title, Date limit, String memo) {
    setTitle(title);
    setDeadLine(limit);
    setMemo(memo);
  }

}
