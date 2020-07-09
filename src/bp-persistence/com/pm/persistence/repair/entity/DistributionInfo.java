package com.pm.persistence.repair.entity;

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
 * 表distribution_info 的实体类
 * @author 刘镝
 */
@Entity(name = "distribution_info")
public class DistributionInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 8298016191824602127L;

  public DistributionInfo clone() {
      DistributionInfo o = null;
      try {
          o = (DistributionInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 分配ID
   */
  @ID
  @Column(name = "distribution_id", comment = "分配ID")
  private String distribution_id;

  /**
   * 维修编号
   */
  @Column(name = "repair_order_num", comment = "维修编号")
  private String repair_order_num;

  /**
   * 维修类型
   */
  @Column(name = "repair_type", comment = "维修类型")
  private String repair_type;

  /**
   * 要求结束时间
   */
  @Column(name = "require_date", comment = "要求结束时间")
  private String require_date;

  /**
   * 是否通知厂长
   */
  @Column(name = "notice_yn", comment = "是否通知厂长")
  private String notice_yn;

  /**
   * 分配人
   */
  @Column(name = "user_id", comment = "分配人")
  private String user_id;

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
  
  /**
   * 
   */
  @Column(name = "repair_classify", comment = "")
  private String repair_classify;
  /**
   * 是否需要修改pid
   */
  @Column(name = "distribution_pid", comment = "pid1需要 2不需要")
  private String distribution_pid;
  public String getDistribution_id() {
       return distribution_id;
  }

  public void setDistribution_id(String distribution_id) {
       this.distribution_id = distribution_id;
  }

  public String getRepair_order_num() {
       return repair_order_num;
  }

  public void setRepair_order_num(String repair_order_num) {
       this.repair_order_num = repair_order_num;
  }

  public String getRepair_type() {
       return repair_type;
  }

  public void setRepair_type(String repair_type) {
       this.repair_type = repair_type;
  }

  public String getRequire_date() {
       return require_date;
  }

  public void setRequire_date(String require_date) {
       this.require_date = require_date;
  }

  public String getNotice_yn() {
       return notice_yn;
  }

  public void setNotice_yn(String notice_yn) {
       this.notice_yn = notice_yn;
  }

  public String getUser_id() {
       return user_id;
  }

  public void setUser_id(String user_id) {
       this.user_id = user_id;
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

	public String getRepair_classify() {
		return repair_classify;
	}
	
	public void setRepair_classify(String repair_classify) {
		this.repair_classify = repair_classify;
	}

	public String getDistribution_pid() {
		return distribution_pid;
	}

	public void setDistribution_pid(String distribution_pid) {
		this.distribution_pid = distribution_pid;
	}

}
