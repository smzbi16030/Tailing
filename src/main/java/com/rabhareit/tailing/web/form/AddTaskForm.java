package com.rabhareit.tailing.web.form;

//import java.util.Date;

import javax.validation.constraints.NotNull;

public class AddTaskForm {

  @NotNull
  private String title;

  @NotNull
  private String limit;

  private String memo;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLimit() {
    return limit;
  }

  public void setLimit(String limit) {
    this.limit = limit;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String memoTextnl2br() {
    if (this.memo == null || this.memo.length() == 0) {
      return null;
    } else {
      return this.memo.replaceAll("\n", "<br>");
    }
  }
  
}
