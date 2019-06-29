package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class TaskExtractor {

  @Autowired
  private JdbcTemplate jdbc;

  public String tweetDraft() {

    List<Map<String, Object>> allTask = jdbc.queryForList("select * from task_model");
    //サービスのUrl入れたり云々
    return speakAllTask(allTask);
  }

  // TODO ツイート本文のStringが140文字を超えた場合の例外処理
  public String speakAllTask(List<Map<String, Object>> taskList) {
    StringBuffer status = new StringBuffer("\n");
    taskList.stream().forEach(task -> {
      String str = task.get("title")+":"+task.get("deadline")+"まで\n";
      status.append(str);
    });
    return status.toString();
  }
}
