package com.rabhareit.tailing.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.rabhareit.tailing.entity.TaskModel;


public class ForDebug {

  public void printList(Collection<?> list) {
    list.forEach(args -> System.err.println(args));
  }

  public void printTask(TaskModel task) {
    System.err.println(task.getTitle() +","+ task.getLimit() +","+ task.getMemo());
  }

  public LocalDate string2LocalDate(String date, String pattern) {
    return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
  }

  public Date localDate2Date(LocalDate date) {
    return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  public LocalDate date2LocalDate(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }
  
  public Collection<TaskModel> listNullCheck(List<TaskModel> list){
    if(list.isEmpty()) {
      list.add(new TaskModel("NoTask", null, null));
      return list;
    } else {
      return list;
    }
  }



}
