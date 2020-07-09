package com.pm.persistence.worktimeinfo.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表worktime_info 的实体类
 * @author 刘镝
 */
@Entity(name = "worktime_info")
public class WorktimeInfo extends BaseEntity implements Serializable, Cloneable {

  public WorktimeInfo clone() {
      WorktimeInfo o = null;
      try {
          o = (WorktimeInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 
   */
  @ID
  @Column(name = "workhour_id", comment = "")
  private String workhour_id;

  /**
   * 年/月
   */
  @Column(name = "date_group", comment = "年/月")
  private String date_group;

  /**
   * 日期
   */
  @Column(name = "days", comment = "日期")
  private String days;

  /**
   * 星期
   */
  @Column(name = "week", comment = "星期")
  private String week;

  /**
   * 工作时长
   */
  @Column(name = "work_hour", comment = "工作时长")
  private String work_hour;

  /**
   * 修改人
   */
  @Column(name = "update_id", comment = "修改人")
  private String update_id;

  /**
   * 修改时间
   */
  @Column(name = "update_time", comment = "修改时间")
  private String update_time;

  public String getWorkhour_id() {
       return workhour_id;
  }

  public void setWorkhour_id(String workhour_id) {
       this.workhour_id = workhour_id;
  }

  public String getDate_group() {
       return date_group;
  }

  public void setDate_group(String date_group) {
       this.date_group = date_group;
  }

  public String getDays() {
       return days;
  }

  public void setDays(String days) {
       this.days = days;
  }

  public String getWeek() {
       return week;
  }

  public void setWeek(String week) {
       this.week = week;
  }

  public String getWork_hour() {
       return work_hour;
  }

  public void setWork_hour(String work_hour) {
       this.work_hour = work_hour;
  }

  public String getUpdate_id() {
       return update_id;
  }

  public void setUpdate_id(String update_id) {
       this.update_id = update_id;
  }

  public String getUpdate_time() {
       return update_time;
  }

  public void setUpdate_time(String update_time) {
       this.update_time = update_time;
  }

}
