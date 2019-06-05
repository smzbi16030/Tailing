package com.rabhareit.tailing.service;

import com.rabhareit.tailing.entity.TaskModel;
import com.rabhareit.tailing.repository.TasksModelRepository;

import java.util.List;
import java.util.function.Consumer;

public class TaskExtractor {

  public String tweetDraft(TasksModelRepository repo) {
    List<TaskModel> allTask = repo.findAll();
    //サービスのUrl入れたり云々
    return speakAllTask(allTask);
  }

  // TODO ツイート本文のStringが140文字を超えた場合の例外処理
  public String speakAllTask(List<TaskModel> taskList) {
    StringBuffer status = new StringBuffer("\n");
    taskList.stream().forEach(new Consumer<TaskModel>() {
      public void accept(TaskModel task) {
        String str = task.getTitle()+":"+task.getLimit()+"まで\n";
        status.append(str);
      }
    });
    String tweetstatus = status.toString();
    return tweetstatus;
  }
}
