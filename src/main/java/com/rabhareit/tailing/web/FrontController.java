package com.rabhareit.tailing.web;

import com.rabhareit.tailing.entity.TailingSocialAccount;
import com.rabhareit.tailing.entity.TaskModel;
import com.rabhareit.tailing.repository.TailingSocialAccountRepository;
import com.rabhareit.tailing.repository.TaskModelRepository;
import com.rabhareit.tailing.service.TailingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.rabhareit.tailing.web.form.AddTaskForm;

import java.util.List;
import java.util.Map;

/**
 * もうちょい小さく分ける
 *
 */
@Controller
public class FrontController {

  TailingUtil util = new TailingUtil();

  @Autowired
  JdbcTemplate jdbc;

  @Autowired
  private TailingSocialAccountRepository socialAccount;

  @Autowired
  private TaskModelRepository taskrepo;

  @RequestMapping("/")
  public String accessTop() {
    return "redirect:/home";
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
  ModelAndView accessList(@ModelAttribute("formModel") AddTaskForm taskForm, ModelAndView mav) {
    mav.setViewName("ToDoList");
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    TailingSocialAccount target = socialAccount.getAccoutByUsername(username);
    try {
      String imageurl = target.getImageUrl();
      String bannerurl = target.getBannerUrl();
      List<Map<String, Object>> taskList = taskrepo.getAllTaskMap(username);
      if (taskList.isEmpty()) {
        mav.addObject("ownerId",username);
        mav.addObject("imageurl",imageurl);
        mav.addObject("bannerurl",bannerurl);
        mav.addObject("taskList", taskList);
        mav.addObject("noTaskFlag", true);
      } else {
        mav.addObject("ownerId",username);
        mav.addObject("imageurl",imageurl);
        mav.addObject("bannerurl",bannerurl);
        mav.addObject("taskList", taskList);
        mav.addObject("noTaskFlag", false);
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

  //rename to update
  @PostMapping("/updTask")
  String addNewTask(@RequestParam(name="title",required=true)String title,
                    @RequestParam(name="deadline",required=true)String deadline,
                    @RequestParam(name="memo",required=true)String memo,
                    @RequestParam(name="ownerId", required=true)String ownerId)
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String username = auth.getName();
    if (ownerId.equals(null)) {
      taskrepo.addNewTask(title, util.string2SqlDate(deadline),memo,username);
    } else {
      taskrepo.updateTask(Long.parseLong(ownerId),title,util.string2SqlDate(deadline),memo);
    }
    return "redirect:/home";
  }

  @PostMapping("/deltask")
  String deleteTask(@RequestParam(name="delId",required=true)String id, ModelAndView mav) {
    taskrepo.deleteTask(Long.parseLong(id));
    return "redirect:/home";
  }

  @PostMapping("/completedtask")
  String archiveTask(@RequestParam(name="completeId",required=true)String id, ModelAndView mav) {
    taskrepo.archiveTask(Long.parseLong(id));
    return "redirect:/home";
  }

  @PostMapping("edit")
  String editTask(@RequestParam(name="id",required=true)String id, @RequestParam(name="taskinfo",required=true)TaskModel model) {
    taskrepo.updateTask(Long.parseLong(id), model);
    return "redirect:/home";
  }

  @RequestMapping("/maintenance")
  ModelAndView mente(ModelAndView mav) {
    mav.setViewName("maintenance");
    return mav;
  }

}
