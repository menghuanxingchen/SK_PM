package com.pm.persistence.workout.entity;

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
 * 表work_out_mach_cost 的实体类
 * @author 刘镝
 */
@Entity(name = "work_out_mach_cost")
public class WorkOutMachCost extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 3019480109774740100L;

  public WorkOutMachCost clone() {
      WorkOutMachCost o = null;
      try {
          o = (WorkOutMachCost) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 设备ID
   */
  @ID
  @Column(name = "mach_cost_id", comment = "设备ID")
  private String mach_cost_id;

  /**
   * 设备名称
   */
  @Column(name = "mach_cost_nm", comment = "设备名称")
  private String mach_cost_nm;

  /**
   * 0-4小时单价
   */
  @Column(name = "use_hour_cost", comment = "0-4小时单价")
  private String use_hour_cost;

  /**
   * 单价/天(超过4小时）
   */
  @Column(name = "use_day_cost", comment = "单价/天(超过4小时）")
  private String use_day_cost;

  /**
   * 首次创建userid
   */
  @Column(name = "create_id", comment = "首次创建userid")
  private String create_id;

  /**
   * 首次创建时间
   */
  @Column(name = "create_time", comment = "首次创建时间")
  private String create_time;

  /**
   * 修改userid
   */
  @Column(name = "update_id", comment = "修改userid")
  private String update_id;

  /**
   * 修改时间
   */
  @Column(name = "update_time", comment = "修改时间")
  private String update_time;

  public String getMach_cost_id() {
       return mach_cost_id;
  }

  public void setMach_cost_id(String mach_cost_id) {
       this.mach_cost_id = mach_cost_id;
  }

  public String getMach_cost_nm() {
       return mach_cost_nm;
  }

  public void setMach_cost_nm(String mach_cost_nm) {
       this.mach_cost_nm = mach_cost_nm;
  }

  public String getUse_hour_cost() {
       return use_hour_cost;
  }

  public void setUse_hour_cost(String use_hour_cost) {
       this.use_hour_cost = use_hour_cost;
  }

  public String getUse_day_cost() {
       return use_day_cost;
  }

  public void setUse_day_cost(String use_day_cost) {
       this.use_day_cost = use_day_cost;
  }

  public String getCreate_id() {
       return create_id;
  }

  public void setCreate_id(String create_id) {
       this.create_id = create_id;
  }

  public String getCreate_time() {
       return create_time;
  }

  public void setCreate_time(String create_time) {
       this.create_time = create_time;
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
