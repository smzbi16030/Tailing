package com.rabhareit.tailing.web.form;

//import java.util.Date;

import javax.validation.constraints.NotNull;
import java.sql.Date;

public class AddTaskForm {

  @NotNull
  private String title;

  @NotNull
  private Date deadline;

  private String memo;

  private String ownerId;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getDeadline() {
    return deadline;
  }

  public void setDeadline(Date deadline) {
    this.deadline = deadline;
  }

  public String getMemo() {
    return memo;
  }

  public void setMemo(String memo) {
    this.memo = memo;
  }

  public String getOwnerId() { return ownerId; }

  public void setOwnerId(String ownerId) { this.ownerId = ownerId; }

  public String memoTextnl2br() {
    if (this.memo == null || this.memo.length() == 0) {
      return "";
    } else {
      return this.memo.replaceAll("\n", "<br>");
    }
  }
  
}
