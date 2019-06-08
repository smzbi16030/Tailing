package com.rabhareit.tailing.web;

import com.rabhareit.tailing.repository.CompletedTaskModelRepository;
import com.rabhareit.tailing.service.TailingUtil;
import com.rabhareit.tailing.service.TemporaryAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rabhareit.tailing.web.form.AddTaskForm;


import java.util.List;
import java.util.Map;

/**
 * もう少し機能別に別けれたら。
 */

@Controller
public class FrontController {

  TailingUtil tutil = new TailingUtil();

  @Autowired
  private JdbcTemplate jdbc;

  @Autowired
  CompletedTaskModelRepository completedTaskModel;

  @Autowired
  TemporaryAccountService accountService;

  @RequestMapping("/")
  public String accessTop() {
    return "top";
  }

  @RequestMapping("/home")
  public ModelAndView accessHome(ModelAndView mav) {
    mav.setViewName("home");
    return mav;
  }

  @RequestMapping("/list")
  ModelAndView accessList(ModelAndView mav) {
    mav.setViewName("ToDoList");
    //ログイン中のユーザー名を取得(ここでいいか)
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();

    try {
      List<Map<String, Object>> taskList = jdbc.queryForList("select * from task_model");
      //TODO TaskModel を使用したマッピングの実装
      if (taskList.isEmpty()) {
        mav.addObject("ownerid",username);
        mav.addObject("taskList", taskList);
        mav.addObject("noTaskFlag", true);
        mav.addObject("formModel", (new AddTaskForm()));
      } else {
        mav.addObject("ownerid",username);
        mav.addObject("taskList", taskList);
        mav.addObject("noTaskFlag", false);
        mav.addObject("formModel", (new AddTaskForm()) );
      }
    } catch (NullPointerException npe) {
      npe.printStackTrace();
    }
    return mav;
  }

  @RequestMapping("regist")
  ModelAndView accessRegist(ModelAndView mav) {
    mav.setViewName("ToDoRegist");
    mav.addObject("formModel", (new AddTaskForm()) );
    return mav;
  }

  @PostMapping("/addTask")
  String addNewTask(@RequestParam(name="title",required=true)String title,
                    @RequestParam(name="limit",required=true)String limit,
                    @RequestParam(name="memo",required=true)String memo) {

    jdbc.update("insert into task_model values (?,?,?)",title,tutil.string2SqlDate(limit),memo);

    //TaskModel newTask = new TaskModel(title,limit,memo);
    //taskModel.saveAndFlush(newTask);
    return "redirect:/list";
  }

  @PostMapping("/deltask")
  String deleteTask(@RequestParam(name="delId",required=true)String id, ModelAndView mav) {
  jdbc.update("delete from task_model where id = ?",id);
    return "redirect:/list";
  }

  @PostMapping("/completedtask")
  String archiveTask(@RequestParam(name="completeId",required=true)String id, ModelAndView mav) {
    //TODO 何らかの手段で存在しないidを受け取った場合のエラー処理
    //TODO jdbcTemplateを使った実装
    return "redirect:/list";
  }
}
