package com.rabhareit.tailing.web;

import com.rabhareit.tailing.service.TailingUtil;
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

  @RequestMapping("/")
  public String accessTop() {
    return "top";
  }


  @RequestMapping("/index")
  public ModelAndView accessIndex(ModelAndView mav) {
    mav.setViewName("index");
    return mav;
  }

  @RequestMapping("/userhome")
  public ModelAndView accessHome(ModelAndView mav) {
    mav.setViewName("home");
    return mav;
  }

  @RequestMapping("/home")
  ModelAndView accessList(ModelAndView mav) {
    mav.setViewName("ToDoList");

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    try {
      List<Map<String, Object>> images = jdbc.queryForList("select imageurl,profileurl from userconnection where userid = ?",username);
      String imageurl = "null";
      imageurl = (String) images.get(0).get("imageurl");
      List<Map<String, Object>> taskList = jdbc.queryForList("select * from task_model where ownerid = ?",username);
      if (taskList.isEmpty()) {
        mav.addObject("loginId",username);
        mav.addObject("imageurl",imageurl);
        mav.addObject("taskList", taskList); // ?
        mav.addObject("noTaskFlag", true);
        mav.addObject("formModel", (new AddTaskForm()));
      } else {
        mav.addObject("loginId",username);
        mav.addObject("imageurl",imageurl);
        mav.addObject("taskList", taskList);
        mav.addObject("noTaskFlag", false);
        mav.addObject("formModel", (new AddTaskForm()) );
      }
    } catch (IndexOutOfBoundsException iob) {
      iob.printStackTrace();
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
                    @RequestParam(name="deadline",required=true)String deadline,
                    @RequestParam(name="memo",required=true)String memo,
                    @RequestParam(name="ownerId", required=true)String ownerId)
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    jdbc.update("insert into task_model(title, deadline, memo, ownerid) values (?,?,?,?)",title,tutil.string2SqlDate(deadline),memo,auth.getName());
    return "redirect:/list";
  }

  @PostMapping("/deltask")
  String deleteTask(@RequestParam(name="delId",required=true)String id, ModelAndView mav) {
    jdbc.update("delete from task_model where id = ?",Long.parseLong(id));
    return "redirect:/list";
  }

  @PostMapping("/completedtask")
  String archiveTask(@RequestParam(name="completeId",required=true)String id, ModelAndView mav) {
    //TODO 何らかの手段で存在しないidを受け取った場合のエラー処理
    List<Map<String, Object>> archiveTask = jdbc.queryForList("select * from task_model where id = " + id);
    jdbc.update("insert into completed_task_model values(?,current_date,?,?,?,?)", archiveTask.get(0).get("id"), archiveTask.get(0).get("title"), archiveTask.get(0).get("deadline"), archiveTask.get(0).get("memo"), archiveTask.get(0).get("ownerId"));
    jdbc.update("delete from task_model where id = " + Long.parseLong(id));
    return "redirect:/list";
  }
}
