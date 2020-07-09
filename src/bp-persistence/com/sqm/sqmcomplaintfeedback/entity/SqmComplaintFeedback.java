package com.sqm.sqmcomplaintfeedback.entity;

import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表sqm_complaint_feedback 的实体类
 * @author 刘镝
 */
@Entity(name = "sqm_complaint_feedback")
public class SqmComplaintFeedback extends BaseEntity implements Serializable, Cloneable {

  public SqmComplaintFeedback clone() {
      SqmComplaintFeedback o = null;
      try {
          o = (SqmComplaintFeedback) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 主键
   */
  @ID
  @Column(name = "id", comment = "主键")
  private String id;

  /**
   * 批号
   */
  @Column(name = "lotnumber", comment = "批号")
  private String lotnumber;

  /**
   * 日期
   */
  @Column(name = "cf_date", comment = "日期")
  private String cf_date;

  /**
   * 来源
   */
  @Column(name = "cf_source", comment = "来源")
  private String cf_source;

  /**
   * 类别:1投诉；2反馈
   */
  @Column(name = "cf_type", comment = "类别:1投诉；2反馈")
  private String cf_type;

  /**
   * 反馈地点
   */
  @Column(name = "cf_address", comment = "反馈地点")
  private String cf_address;

  /**
   * 产品类型
   */
  @Column(name = "product_type", comment = "产品类型")
  private String product_type;

  /**
   * 级别
   */
  @Column(name = "product_rank", comment = "级别")
  private String product_rank;

  /**
   * 包装
   */
  @Column(name = "product_pack", comment = "包装")
  private String product_pack;

  /**
   * 数量（桶）
   */
  @Column(name = "number_barrel", comment = "数量（桶）")
  private String number_barrel;

  /**
   * 数量（升）
   */
  @Column(name = "number_litres", comment = "数量（升）")
  private String number_litres;

  /**
   * 抱怨类别
   */
  @Column(name = "feedback_type", comment = "抱怨类别")
  private String feedback_type;

  /**
   * 内容概述（客户问题反馈）
   */
  @Column(name = "feedback_content", comment = "内容概述（客户问题反馈）")
  private String feedback_content;

  /**
   * 处理内容、结果
   */
  @Column(name = "processing_result", comment = "处理内容、结果")
  private String processing_result;

  /**
   * 是否关闭：y关闭，n未关闭
   */
  @Column(name = "close_yn", comment = "是否关闭：y关闭，n未关闭")
  private String close_yn;

  /**
   * 增加措施
   */
  @Column(name = "add_measures", comment = "增加措施")
  private String add_measures;

  /**
   * 创建人
   */
  @Column(name = "create_person", comment = "创建人")
  private String create_person;

  /**
   * 创建日期
   */
  @Column(name = "create_date", comment = "创建日期")
  private String create_date;

  /**
   * 最近更新日期
   */
  @Column(name = "upd_date", comment = "最近更新日期")
  private String upd_date;

  /**
   * 最近更新人
   */
  @Column(name = "upd_person", comment = "最近更新人")
  private String upd_person;

  public String getId() {
       return id;
  }

  public void setId(String id) {
       this.id = id;
  }

  public String getLotnumber() {
       return lotnumber;
  }

  public void setLotnumber(String lotnumber) {
       this.lotnumber = lotnumber;
  }

  public String getCf_date() {
       return cf_date;
  }

  public void setCf_date(String cf_date) {
       this.cf_date = cf_date;
  }

  public String getCf_source() {
       return cf_source;
  }

  public void setCf_source(String cf_source) {
       this.cf_source = cf_source;
  }

  public String getCf_type() {
       return cf_type;
  }

  public void setCf_type(String cf_type) {
       this.cf_type = cf_type;
  }

  public String getCf_address() {
       return cf_address;
  }

  public void setCf_address(String cf_address) {
       this.cf_address = cf_address;
  }

  public String getProduct_type() {
       return product_type;
  }

  public void setProduct_type(String product_type) {
       this.product_type = product_type;
  }

  public String getProduct_rank() {
       return product_rank;
  }

  public void setProduct_rank(String product_rank) {
       this.product_rank = product_rank;
  }

  public String getProduct_pack() {
       return product_pack;
  }

  public void setProduct_pack(String product_pack) {
       this.product_pack = product_pack;
  }

  public String getNumber_barrel() {
       return number_barrel;
  }

  public void setNumber_barrel(String number_barrel) {
       this.number_barrel = number_barrel;
  }

  public String getNumber_litres() {
       return number_litres;
  }

  public void setNumber_litres(String number_litres) {
       this.number_litres = number_litres;
  }

  public String getFeedback_type() {
       return feedback_type;
  }

  public void setFeedback_type(String feedback_type) {
       this.feedback_type = feedback_type;
  }

  public String getFeedback_content() {
       return feedback_content;
  }

  public void setFeedback_content(String feedback_content) {
       this.feedback_content = feedback_content;
  }

  public String getProcessing_result() {
       return processing_result;
  }

  public void setProcessing_result(String processing_result) {
       this.processing_result = processing_result;
  }

  public String getClose_yn() {
       return close_yn;
  }

  public void setClose_yn(String close_yn) {
       this.close_yn = close_yn;
  }

  public String getAdd_measures() {
       return add_measures;
  }

  public void setAdd_measures(String add_measures) {
       this.add_measures = add_measures;
  }

  public String getCreate_person() {
       return create_person;
  }

  public void setCreate_person(String create_person) {
       this.create_person = create_person;
  }

  public String getCreate_date() {
       return create_date;
  }

  public void setCreate_date(String create_date) {
       this.create_date = create_date;
  }

  public String getUpd_date() {
       return upd_date;
  }

  public void setUpd_date(String upd_date) {
       this.upd_date = upd_date;
  }

  public String getUpd_person() {
       return upd_person;
  }

  public void setUpd_person(String upd_person) {
       this.upd_person = upd_person;
  }

}
