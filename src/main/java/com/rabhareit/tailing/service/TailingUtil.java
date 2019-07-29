package com.rabhareit.tailing.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

public class TailingUtil {

  public void printList(Collection<?> list) {
    list.forEach(args -> System.err.println(args));
  }

  public void printTask() {
  }

  public LocalDate string2LocalDate(String date, String pattern) {
    return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
  }

  public Date localDate2Date(LocalDate date) {
    return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
  }

  public LocalDate date2LocalDate(Date date) {
    return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public java.sql.Date string2SqlDate(String date) {
    return java.sql.Date.valueOf(date);
  }
}