package com.pm.persistence.Sqmmanage.entity;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;

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
 * 表production_plan 的实体类
 * @author 刘镝
 */
@Entity(name = "production_plan")
public class ProductionPlan extends BaseEntity implements Serializable, Cloneable {

  public ProductionPlan clone() {
      ProductionPlan o = null;
      try {
          o = (ProductionPlan) super.clone();
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
   * 罐装日期
   */

  @Column(name = "CNFYMD", comment = "罐装日期")
  private String CNFYMD;

  
  /**
   * 开始日期
   */
  @Transient
  @Column(name = "startdate", comment = "开始日期")
  private String startdate;
  
  
  
  /**
   * 结束日期
   */
  @Transient
  @Column(name = "enddate", comment = "结束日期")
  private String enddate;
  
  
  /**
   * VSL
   */
  @Column(name = "VSL", comment = "VSL")
  private String VSL;

  /**
   * 次数
   */
  @Column(name = "TURN", comment = "次数")
  private String TURN;

  /**
   * 编号
   */
  @Column(name = "PROD", comment = "编号")
  private String PROD;

  /**
   * 产品名称
   */
  @Column(name = "PRODNM", comment = "产品名称")
  private String PRODNM;

  /**
   * 调配量
   */
  @Column(name = "BLNDQTY", comment = "调配量")
  private String BLNDQTY;

  /**
   * lotno
   */
  @Column(name = "LOTNO", comment = "lotno")
  private String LOTNO;

  /**
   * QTY1
   */
  @Column(name = "QTY1", comment = "QTY1")
  private String QTY1;

  /**
   * 18L
   */
  @Column(name = "QTY2", comment = "18L")
  private String QTY2;

  /**
   * 4L
   */
  @Column(name = "QTY3", comment = "4L")
  private String QTY3;

  /**
   * 1L
   */
  @Column(name = "QTY4", comment = "1L")
  private String QTY4;

  /**
   * 0.9L
   */
  @Column(name = "QTY5", comment = "0.9L")
  private String QTY5;

  /**
   * 1Ton
   */
  @Column(name = "QTY6", comment = "1Ton")
  private String QTY6;

  /**
   * ETC
   */
  @Column(name = "ETC", comment = "ETC")
  private String ETC;

  /**
   * QTY7
   */
  @Column(name = "QTY7", comment = "QTY7")
  private String QTY7;

  /**
   * 灌装TK
   */
  @Column(name = "htank", comment = "灌装TK")
  private String htank;

  /**
   * 移送量
   */
  @Column(name = "moveqty", comment = "移送量")
  private String moveqty;

  /**
   * 余量
   */
  @Column(name = "MARGINQTY", comment = "余量")
  private String MARGINQTY;

  /**
   * 移送区分
   */
  @Column(name = "MGUBUNNM", comment = "移送区分")
  private String MGUBUNNM;

  /**
   * 20度密度
   */
  @Column(name = "RESULT", comment = "20度密度")
  private String RESULT;

  /**
   * 备注
   */
  @Column(name = "notice", comment = "备注")
  private String notice;

  /**
   * 贴标与否
   */
  @Column(name = "labelState", comment = "贴标与否")
  private String labelState;

  /**
   * 备注
   */
  @Column(name = "BIGO", comment = "备注")
  private String BIGO;

  /**
   * 实际开始日期
   */
  @Column(name = "stdate", comment = "实际开始日期")
  private Date stdate;

  /**
   * 实际结束日期
   */
  @Column(name = "eddate", comment = "实际结束日期")
  private Date eddate;

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
   * 
   */
  @Transient
  @Column(name = "dateType", comment = "")
  private String dateType;

  public String getId() {
       return id;
  }

  public void setId(String id) {
       this.id = id;
  }

  public String getCNFYMD() {
       return CNFYMD;
  }

  public void setCNFYMD(String CNFYMD) {
       this.CNFYMD = CNFYMD;
  }

  public String getStartdate() {
      return startdate;
 }

 public void setStartdate(String startdate) {
      this.startdate = startdate;
 }
 
 public String getEnddate() {
     return enddate;
}

public void setEnddate(String enddate) {
     this.enddate = enddate;
}
  public String getVSL() {
       return VSL;
  }

  public void setVSL(String VSL) {
       this.VSL = VSL;
  }

  public String getTURN() {
       return TURN;
  }

  public void setTURN(String TURN) {
       this.TURN = TURN;
  }

  public String getPROD() {
       return PROD;
  }

  public void setPROD(String PROD) {
       this.PROD = PROD;
  }

  public String getPRODNM() {
       return PRODNM;
  }

  public void setPRODNM(String PRODNM) {
       this.PRODNM = PRODNM;
  }

  public String getBLNDQTY() {
       return BLNDQTY;
  }

  public void setBLNDQTY(String BLNDQTY) {
       this.BLNDQTY = BLNDQTY;
  }

  public String getLOTNO() {
       return LOTNO;
  }

  public void setLOTNO(String LOTNO) {
       this.LOTNO = LOTNO;
  }

  public String getQTY1() {
       return QTY1;
  }

  public void setQTY1(String QTY1) {
       this.QTY1 = QTY1;
  }

  public String getQTY2() {
       return QTY2;
  }

  public void setQTY2(String QTY2) {
       this.QTY2 = QTY2;
  }

  public String getQTY3() {
       return QTY3;
  }

  public void setQTY3(String QTY3) {
       this.QTY3 = QTY3;
  }

  public String getQTY4() {
       return QTY4;
  }

  public void setQTY4(String QTY4) {
       this.QTY4 = QTY4;
  }

  public String getQTY5() {
       return QTY5;
  }

  public void setQTY5(String QTY5) {
       this.QTY5 = QTY5;
  }

  public String getQTY6() {
       return QTY6;
  }

  public void setQTY6(String QTY6) {
       this.QTY6 = QTY6;
  }

  public String getETC() {
       return ETC;
  }

  public void setETC(String ETC) {
       this.ETC = ETC;
  }

  public String getQTY7() {
       return QTY7;
  }

  public void setQTY7(String QTY7) {
       this.QTY7 = QTY7;
  }

  public String getHtank() {
       return htank;
  }

  public void setHtank(String htank) {
       this.htank = htank;
  }

  public String getMoveqty() {
       return moveqty;
  }

  public void setMoveqty(String moveqty) {
       this.moveqty = moveqty;
  }

  public String getMARGINQTY() {
       return MARGINQTY;
  }

  public void setMARGINQTY(String MARGINQTY) {
       this.MARGINQTY = MARGINQTY;
  }

  public String getMGUBUNNM() {
       return MGUBUNNM;
  }

  public void setMGUBUNNM(String MGUBUNNM) {
       this.MGUBUNNM = MGUBUNNM;
  }

  public String getRESULT() {
       return RESULT;
  }

  public void setRESULT(String RESULT) {
       this.RESULT = RESULT;
  }

  public String getNotice() {
       return notice;
  }

  public void setNotice(String notice) {
       this.notice = notice;
  }

  public String getLabelState() {
       return labelState;
  }

  public void setLabelState(String labelState) {
       this.labelState = labelState;
  }

  public String getBIGO() {
       return BIGO;
  }

  public void setBIGO(String BIGO) {
       this.BIGO = BIGO;
  }

  public Date getStdate() {
       return stdate;
  }

  public void setStdate(Date stdate) {
       this.stdate = stdate;
  }

  public Date getEddate() {
       return eddate;
  }

  public void setEddate(Date eddate) {
       this.eddate = eddate;
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

  public String getDateType() {
      return dateType;
 }

 public void setDateType(String dateType) {
      this.dateType = dateType;
 }
}
