package com.pm.persistence.seccheckinfo.entity;

import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表sec_check_info 的实体类
 * @author 刘镝
 */
@Entity(name = "sec_check_info")
public class SecCheckInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public SecCheckInfo clone() {
      SecCheckInfo o = null;
      try {
          o = (SecCheckInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 安全检查id
   */
  @ID
  @Column(name = "sec_check_id", comment = "安全检查id")
  private String sec_check_id;

  /**
   * 工作分类
   */
  @Column(name = "work_type", comment = "工作分类")
  private String work_type;

  /**
   * 内容
   */
  @Column(name = "sec_detail", comment = "内容")
  private String sec_detail;

  /**
   * 数量次数
   */
  @Column(name = "sec_num", comment = "数量次数")
  private String sec_num;

  /**
   * 上次检定日期
   */
  @Column(name = "up_check_date", comment = "上次检定日期")
  private String up_check_date;

  /**
   * 频率
   */
  @Column(name = "frequency", comment = "频率")
  private String frequency;

  /**
   * 失效日期
   */
  @Column(name = "failure_date", comment = "失效日期")
  private String failure_date;

  /**
   * 当前状态
   */
  @Column(name = "cur_state", comment = "当前状态")
  private String cur_state;

  /**
   * 计划送检日期
   */
  @Column(name = "sec_plan_date", comment = "计划送检日期")
  private String sec_plan_date;

  /**
   * 实际送检日期
   */
  @Column(name = "sec_check_date", comment = "实际送检日期")
  private String sec_check_date;

  /**
   * 送检结果
   */
  @Column(name = "sec_result", comment = "送检结果")
  private String sec_result;

  /**
   * 检定单位
   */
  @Column(name = "check_dept", comment = "检定单位")
  private String check_dept;

  /**
   * 备注
   */
  @Column(name = "remark", comment = "备注")
  private String remark;

  /**
   * 首次创建
   */
  @Column(name = "create_id", comment = "首次创建")
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
   * 排序类型
   */
  @Transient  
  private String orderType;
  

  public String getSec_check_id() {
       return sec_check_id;
  }

  public void setSec_check_id(String sec_check_id) {
       this.sec_check_id = sec_check_id;
  }

  public String getWork_type() {
       return work_type;
  }

  public void setWork_type(String work_type) {
       this.work_type = work_type;
  }

  public String getSec_detail() {
       return sec_detail;
  }

  public void setSec_detail(String sec_detail) {
       this.sec_detail = sec_detail;
  }

  public String getSec_num() {
       return sec_num;
  }

  public void setSec_num(String sec_num) {
       this.sec_num = sec_num;
  }

  public String getUp_check_date() {
       return up_check_date;
  }

  public void setUp_check_date(String up_check_date) {
       this.up_check_date = up_check_date;
  }

  public String getFrequency() {
       return frequency;
  }

  public void setFrequency(String frequency) {
       this.frequency = frequency;
  }

  public String getFailure_date() {
       return failure_date;
  }

  public void setFailure_date(String failure_date) {
       this.failure_date = failure_date;
  }

  public String getCur_state() {
       return cur_state;
  }

  public void setCur_state(String cur_state) {
       this.cur_state = cur_state;
  }

  public String getSec_plan_date() {
       return sec_plan_date;
  }

  public void setSec_plan_date(String sec_plan_date) {
       this.sec_plan_date = sec_plan_date;
  }

  public String getSec_check_date() {
       return sec_check_date;
  }

  public void setSec_check_date(String sec_check_date) {
       this.sec_check_date = sec_check_date;
  }

  public String getSec_result() {
       return sec_result;
  }

  public void setSec_result(String sec_result) {
       this.sec_result = sec_result;
  }

  public String getCheck_dept() {
       return check_dept;
  }

  public void setCheck_dept(String check_dept) {
       this.check_dept = check_dept;
  }

  public String getRemark() {
       return remark;
  }

  public void setRemark(String remark) {
       this.remark = remark;
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

	public String getOrderType() {
		return orderType;
	}
	
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}
