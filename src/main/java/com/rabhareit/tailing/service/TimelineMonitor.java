package com.rabhareit.tailing.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class TimelineMonitor {

  private JdbcTemplate jdbc;

  Timer timer;

  public TimelineMonitor(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
    timer = new Timer(true);
  }

  public void start(String target) {
    //呼ばれてから10分経過後にカウントを0にする。
    TimerTask monitoring = new TimerTask() {
      @Override
      public void run() {
        System.out.println(jdbc.equals(null));
        int result = jdbc.update("update tweetcount set count = 0 where username = ?", target);
        System.out.println(target + ":count++");
      }
    };
    timer.schedule(monitoring,0,600000);
  }

  public void restart(String target) {
    timer = new Timer();
    start(target);
  }
}
