package com.pm.persistence.workout.entity;

import java.io.Serializable;
import java.util.Date;

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
 * 表work_out_detail 的实体类
 * @author 刘镝
 */
@Entity(name = "work_out_detail")
public class WorkOutDetail extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public WorkOutDetail clone() {
      WorkOutDetail o = null;
      try {
          o = (WorkOutDetail) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 考勤统计详情id
   */
  @ID
  @Column(name = "work_out_detail_id", comment = "考勤统计详情id")
  private String work_out_detail_id;

  /**
   * 考勤ID
   */
  @Column(name = "work_out_id", comment = "考勤ID")
  private String work_out_id;

  /**
   * 员工名称
   */
  @Column(name = "worker_nm", comment = "员工名称")
  private String worker_nm;

  /**
   * 工作时间
   */
  @Column(name = "work_hour", comment = "工作时间")
  private String work_hour;

  /**
   * 使用时间
   */
  @Column(name = "work_hour2", comment = "使用时间")
  private String work_hour2;

  /**
   * 工作费用类型（0,人工费，1，机械费）
   */
  @Column(name = "type", comment = "工作费用类型（0,人工费，1，机械费）")
  private String type;

  /**
   * 单位1
   */
  @Column(name = "unit", comment = "单位")
  private String unit;

  /**
   * 单位2
   */
  @Column(name = "unit2", comment = "单位2")
  private String unit2;
 
 

/**
   * 机械费用小时价格
   */
   @Transient
  @Column(name = "use_hour_cost", comment = "机械每小时价格")
  private String use_hour_cost;

  /**
   * 机械费用小时价格
   */
  @Transient
  @Column(name = "use_day_cost", comment = "机械每天价格")
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
  private Date create_time;

  /**
   * 修改userid
   */
  @Column(name = "update_id", comment = "修改userid")
  private String update_id;

  /**
   * 修改时间
   */
  @Column(name = "update_time", comment = "修改时间")
  private Date update_time;
  
  public String getWork_out_detail_id() {
       return work_out_detail_id;
  }

  public void setWork_out_detail_id(String work_out_detail_id) {
       this.work_out_detail_id = work_out_detail_id;
  }

  public String getWork_out_id() {
       return work_out_id;
  }

  public void setWork_out_id(String work_out_id) {
       this.work_out_id = work_out_id;
  }

  public String getWorker_nm() {
       return worker_nm;
  }

  public void setWorker_nm(String worker_nm) {
       this.worker_nm = worker_nm;
  }

  public String getWork_hour() {
       return work_hour;
  }

  public void setWork_hour(String work_hour) {
       this.work_hour = work_hour;
  }

  public String getType() {
       return type;
  }

  public void setType(String type) {
       this.type = type;
  }

  public String getUnit() {
       return unit;
  }

  public void setUnit(String unit) {
       this.unit = unit;
  }
   
public String getCreate_id() {
       return create_id;
  }

  public void setCreate_id(String create_id) {
       this.create_id = create_id;
  }

  public Date getCreate_time() {
       return create_time;
  }

  public void setCreate_time(Date create_time) {
       this.create_time = create_time;
  }

  public String getUpdate_id() {
       return update_id;
  }

  public void setUpdate_id(String update_id) {
       this.update_id = update_id;
  }

  public Date getUpdate_time() {
       return update_time;
  }

  public void setUpdate_time(Date update_time) {
       this.update_time = update_time;
  }

  public String getWork_hour2() {
	return work_hour2;
}

public void setWork_hour2(String work_hour2) {
	this.work_hour2 = work_hour2;
}

public String getUnit2() {
	return unit2;
}

public void setUnit2(String unit2) {
	this.unit2 = unit2;
}


public String getUse_day_cost() {
	return use_day_cost;
}

public void setUse_day_cost(String use_day_cost) {
	this.use_day_cost = use_day_cost;
}

public String getUse_hour_cost() {
	return use_hour_cost;
}

public void setUse_hour_cost(String use_hour_cost) {
	this.use_hour_cost = use_hour_cost;
}
}
