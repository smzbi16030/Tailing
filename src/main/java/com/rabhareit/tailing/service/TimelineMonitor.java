package com.rabhareit.tailing.service;

import com.rabhareit.tailing.repository.TweetCountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;

@Service
public class TimelineMonitor {

  @Autowired
  TweetCountRepository count;

  Timer timer = new Timer(true);

  public void start(String target) {
    //呼ばれてから10分経過後にカウントを0にする。
    TimerTask monitoring = new TimerTask() {
      @Override
      public void run() {
        count.reset(target);
      }
    };
    timer.schedule(monitoring,0,600000);
  }

  public void restart(String target) {
    timer = new Timer();
    start(target);
  }
}
