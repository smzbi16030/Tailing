package com.rabhareit.tailing.mapper;

import com.rabhareit.tailing.entity.TaskModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskModelRowMapper implements RowMapper<TaskModel> {
  @Override
  public TaskModel mapRow(ResultSet rs, int rowNum) throws SQLException {
    TaskModel model = new TaskModel();
    model.setId(rs.getLong("id"));
    model.setTitle(rs.getString("title"));
    model.setLimit(rs.getDate("deadline"));
    model.setMemo(rs.getString("memo"));
    model.setOwnerid(rs.getString("ownerid"));
    return model;
  }
}
