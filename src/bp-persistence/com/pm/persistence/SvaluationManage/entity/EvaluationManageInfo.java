package com.pm.persistence.SvaluationManage.entity;

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
 * 表evaluation_manage_info 的实体类
 * @author 刘镝
 */
@Entity(name = "evaluation_manage_info")
public class EvaluationManageInfo extends BaseEntity implements Serializable, Cloneable {

  public EvaluationManageInfo clone() {
      EvaluationManageInfo o = null;
      try {
          o = (EvaluationManageInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 
   */
  @ID
  @Column(name = "id", comment = "")
  private String id;

  /**
   * 用户ID
   */
  @Column(name = "user_num", comment = "用户ID")
  private String user_num;

  /**
   * 工号
   */
  @Column(name = "user_nm", comment = "姓名")
  private String user_nm;

  /**
   * 
   */
  @Column(name = "year_date", comment = "")
  private String year_date;

  /**
   * 
   */
  @Column(name = "date_annual", comment = "")
  private String date_annual;

  /**
   * 
   */
  @Column(name = "test_goal_num", comment = "")
  private String test_goal_num;

  /**
   * 
   */
  @Column(name = "modify_num", comment = "")
  private String modify_num;

  /**
   * 
   */
  @Column(name = "error_rate", comment = "")
  private String error_rate;

  /**
   * 
   */
  @Column(name = "average_score", comment = "")
  private String average_score;

  /**
   * 
   */
  @Column(name = "evaluation_one", comment = "")
  private String evaluation_one;

  /**
   * 
   */
  @Column(name = "evaluation_one_name", comment = "")
  private String evaluation_one_name;

  /**
   * 
   */
  @Column(name = "evaluation_one_num", comment = "")
  private String evaluation_one_num;

  /**
   * 
   */
  @Column(name = "evaluation_one_remark", comment = "")
  private String evaluation_one_remark;

  /**
   * 
   */
  @Column(name = "evaluation_two", comment = "")
  private String evaluation_two;

  /**
   * 
   */
  @Column(name = "evaluation_two_name", comment = "")
  private String evaluation_two_name;

  /**
   * 
   */
  @Column(name = "evaluation_two_num", comment = "")
  private String evaluation_two_num;

  /**
   * 
   */
  @Column(name = "evaluation_two_remark", comment = "")
  private String evaluation_two_remark;

  /**
   * 
   */
  @Column(name = "evaluation_state", comment = "")
  private String evaluation_state;

  /**
   * 
   */
  @Column(name = "evaluation_state_nm", comment = "")
  private String evaluation_state_nm;
  
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
  @Column(name = "user_yn", comment = "")
  private String user_yn;

  
  /**
   * 保存对象json
   */
  @Transient
  @Column(name = "json_info", comment = "保存对象json")
  private String json_info;
  
  
  public String getId() {
       return id;
  }

  public void setId(String id) {
       this.id = id;
  }

  public String getUser_num() {
       return user_num;
  }

  public void setUser_num(String user_num) {
       this.user_num = user_num;
  }

  public String getUser_nm() {
       return user_nm;
  }

  public void setUser_nm(String user_nm) {
       this.user_nm = user_nm;
  }

  public String getYear_date() {
       return year_date;
  }

  public void setYear_date(String year_date) {
       this.year_date = year_date;
  }

  public String getDate_annual() {
       return date_annual;
  }

  public void setDate_annual(String date_annual) {
       this.date_annual = date_annual;
  }

  public String getTest_goal_num() {
       return test_goal_num;
  }

  public void setTest_goal_num(String test_goal_num) {
       this.test_goal_num = test_goal_num;
  }

  public String getModify_num() {
       return modify_num;
  }

  public void setModify_num(String modify_num) {
       this.modify_num = modify_num;
  }

  public String getError_rate() {
       return error_rate;
  }

  public void setError_rate(String error_rate) {
       this.error_rate = error_rate;
  }

  public String getAverage_score() {
       return average_score;
  }

  public void setAverage_score(String average_score) {
       this.average_score = average_score;
  }

  public String getEvaluation_one() {
       return evaluation_one;
  }

  public void setEvaluation_one(String evaluation_one) {
       this.evaluation_one = evaluation_one;
  }

  public String getEvaluation_one_name() {
       return evaluation_one_name;
  }

  public void setEvaluation_one_name(String evaluation_one_name) {
       this.evaluation_one_name = evaluation_one_name;
  }

  public String getEvaluation_one_num() {
       return evaluation_one_num;
  }

  public void setEvaluation_one_num(String evaluation_one_num) {
       this.evaluation_one_num = evaluation_one_num;
  }

  public String getEvaluation_one_remark() {
       return evaluation_one_remark;
  }

  public void setEvaluation_one_remark(String evaluation_one_remark) {
       this.evaluation_one_remark = evaluation_one_remark;
  }

  public String getEvaluation_two() {
       return evaluation_two;
  }

  public void setEvaluation_two(String evaluation_two) {
       this.evaluation_two = evaluation_two;
  }

  public String getEvaluation_two_name() {
       return evaluation_two_name;
  }

  public void setEvaluation_two_name(String evaluation_two_name) {
       this.evaluation_two_name = evaluation_two_name;
  }

  public String getEvaluation_two_num() {
       return evaluation_two_num;
  }

  public void setEvaluation_two_num(String evaluation_two_num) {
       this.evaluation_two_num = evaluation_two_num;
  }

  public String getEvaluation_two_remark() {
       return evaluation_two_remark;
  }

  public void setEvaluation_two_remark(String evaluation_two_remark) {
       this.evaluation_two_remark = evaluation_two_remark;
  }

  public String getEvaluation_state() {
       return evaluation_state;
  }

  public void setEvaluation_state(String evaluation_state) {
       this.evaluation_state = evaluation_state;
  }
  
  public String getEvaluation_state_nm() {
      return evaluation_state_nm;
  }

 public void setEvaluation_state_nm(String evaluation_state_nm) {
      this.evaluation_state_nm = evaluation_state_nm;
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

  public String getUser_yn() {
       return user_yn;
  }

  public void setUser_yn(String user_yn) {
       this.user_yn = user_yn;
  }

  public String getJson_info() {
       return json_info;
  }

  public void setJson_info(String json_info) {
       this.json_info = json_info;
  }
}
