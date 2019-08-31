package com.rabhareit.tailing.repository;

import com.rabhareit.tailing.entity.TaskModel;
import com.rabhareit.tailing.mapper.TaskModelRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Repository
public class TaskModelRepository {

  @Autowired
  JdbcTemplate jdbc;

  TaskModelRowMapper mapper = new TaskModelRowMapper();

  public void addNewTask(String title, Date limit, String memo, String ownerid) {
    jdbc.update("insert into task_model(title, deadline, memo, ownerid) values (?,?,?,?)",title,limit,memo,ownerid);
  }

  public void addNewTask(TaskModel model) {
    jdbc.update("insert into task_model(title, deadline, memo, ownerid) values (?,?,?,?)",model.getTitle(),model.getLimit(),model.getMemo(),model.getOwnerid());
  }

  public List<TaskModel> getAllTask(String username) {
    return jdbc.query("select * from task_model where ownerid = ?",mapper,username);
  }

  public List<Map<String,Object>> getAllTaskMap(String username) {
    return jdbc.queryForList("select * from task_model where ownerid = ?",username);
  }

  public TaskModel getTask(long id) {
    return jdbc.queryForObject("select * from task_model where id = ?",mapper,id);
  }

  public void deleteTask(long id) {
    jdbc.update("delete * from task_model where id = ?", id);
  }

  public void archiveTask(long id) {
    TaskModel archiveTask = getTask(id);
    jdbc.update("insert into completed_task_model values(?,current_date,?,?,?,?)", archiveTask.getId(), archiveTask.getTitle(), archiveTask.getLimit(), archiveTask.getMemo(), archiveTask.getOwnerid());
    jdbc.update("delete from task_model where id = ?", id);
  }
}
