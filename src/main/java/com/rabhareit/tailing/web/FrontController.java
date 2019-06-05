package com.rabhareit.tailing.web;

import com.rabhareit.tailing.entity.CompletedTaskModel;
import com.rabhareit.tailing.entity.TaskModel;
import com.rabhareit.tailing.repository.CompletedTaskModelRepository;
import com.rabhareit.tailing.service.TemporaryAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rabhareit.tailing.repository.TasksModelRepository;
import com.rabhareit.tailing.web.form.AddTaskForm;


import java.util.List;

/**
 * もう少し機能別に別けれたら。
 */

@Controller
public class FrontController {

  @Autowired
  TasksModelRepository taskModel;

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
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    try {
      List<TaskModel> taskList = taskModel.findAll();
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
    TaskModel newTask = new TaskModel(title,limit,memo);
    taskModel.saveAndFlush(newTask);
    return "redirect:/list";
  }

  @PostMapping("/deltask")
  String deleteTask(@RequestParam(name="delId",required=true)String id, ModelAndView mav) {
    taskModel.deleteById(Long.parseLong(id));
    return "redirect:/list";
  }

  @PostMapping("/completedtask")
  String archiveTask(@RequestParam(name="completeId",required=true)String id, ModelAndView mav) {
    //TODO 何らかの手段で存在しないidを受け取った場合のエラー処理
    CompletedTaskModel newArchive = new CompletedTaskModel( taskModel.findById(Long.parseLong(id)).get() );
    taskModel.deleteById(Long.parseLong(id));
    completedTaskModel.saveAndFlush(newArchive);
    return "redirect:/list";
  }
}
