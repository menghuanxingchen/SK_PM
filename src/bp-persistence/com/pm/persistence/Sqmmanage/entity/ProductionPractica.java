package com.pm.persistence.Sqmmanage.entity;

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
 * 表production_practica 的实体类
 * @author 刘镝
 */
@Entity(name = "production_practica")
public class ProductionPractica extends BaseEntity implements Serializable, Cloneable {

  public ProductionPractica clone() {
      ProductionPractica o = null;
      try {
          o = (ProductionPractica) super.clone();
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
  @Column(name = "PRODNAME", comment = "产品名称")
  private String PRODNAME;

  /**
   * 调配量
   */
  @Column(name = "BLNDQTY", comment = "调配量")
  private String BLNDQTY;

  /**
   * 
   */
  @Column(name = "PLANQTY", comment = "")
  private String PLANQTY;

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
   * 1Ton
   */
  @Column(name = "QTY7", comment = "1Ton")
  private String QTY7;

  /**
   * 1Ton
   */
  @Column(name = "QTY8", comment = "1Ton")
  private String QTY8;

  /**
   * 1Ton
   */
  @Column(name = "QTY9", comment = "1Ton")
  private String QTY9;

  /**
   * 1Ton
   */
  @Column(name = "QTY10", comment = "1Ton")
  private String QTY10;

  /**
   * 1Ton
   */
  @Column(name = "QTY11", comment = "1Ton")
  private String QTY11;

  /**
   * 1Ton
   */
  @Column(name = "QTY12", comment = "1Ton")
  private String QTY12;

  /**
   * 1Ton
   */
  @Column(name = "QTY13", comment = "1Ton")
  private String QTY13;

  /**
   * 1Ton
   */
  @Column(name = "QTY14", comment = "1Ton")
  private String QTY14;

  /**
   * 1Ton
   */
  @Column(name = "QTY15", comment = "1Ton")
  private String QTY15;

  /**
   * 1Ton
   */
  @Column(name = "QTY16", comment = "1Ton")
  private String QTY16;

  /**
   * 1Ton
   */
  @Column(name = "CHRGQTY", comment = "1Ton")
  private String CHRGQTY;

  /**
   * 
   */
  @Column(name = "CHRGQTY2", comment = "")
  private String CHRGQTY2;

  /**
   * 
   */
  @Column(name = "BIGO", comment = "")
  private String BIGO;

  /**
   * 
   */
  @Column(name = "CANTIME", comment = "")
  private String CANTIME;

  /**
   * 
   */
  @Column(name = "DMTIME", comment = "")
  private String DMTIME;

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

  public String getPRODNAME() {
       return PRODNAME;
  }

  public void setPRODNAME(String PRODNAME) {
       this.PRODNAME = PRODNAME;
  }

  public String getBLNDQTY() {
       return BLNDQTY;
  }

  public void setBLNDQTY(String BLNDQTY) {
       this.BLNDQTY = BLNDQTY;
  }

  public String getPLANQTY() {
       return PLANQTY;
  }

  public void setPLANQTY(String PLANQTY) {
       this.PLANQTY = PLANQTY;
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

  public String getQTY7() {
       return QTY7;
  }

  public void setQTY7(String QTY7) {
       this.QTY7 = QTY7;
  }

  public String getQTY8() {
       return QTY8;
  }

  public void setQTY8(String QTY8) {
       this.QTY8 = QTY8;
  }

  public String getQTY9() {
       return QTY9;
  }

  public void setQTY9(String QTY9) {
       this.QTY9 = QTY9;
  }

  public String getQTY10() {
       return QTY10;
  }

  public void setQTY10(String QTY10) {
       this.QTY10 = QTY10;
  }

  public String getQTY11() {
       return QTY11;
  }

  public void setQTY11(String QTY11) {
       this.QTY11 = QTY11;
  }

  public String getQTY12() {
       return QTY12;
  }

  public void setQTY12(String QTY12) {
       this.QTY12 = QTY12;
  }

  public String getQTY13() {
       return QTY13;
  }

  public void setQTY13(String QTY13) {
       this.QTY13 = QTY13;
  }

  public String getQTY14() {
       return QTY14;
  }

  public void setQTY14(String QTY14) {
       this.QTY14 = QTY14;
  }

  public String getQTY15() {
       return QTY15;
  }

  public void setQTY15(String QTY15) {
       this.QTY15 = QTY15;
  }

  public String getQTY16() {
       return QTY16;
  }

  public void setQTY16(String QTY16) {
       this.QTY16 = QTY16;
  }

  public String getCHRGQTY() {
       return CHRGQTY;
  }

  public void setCHRGQTY(String CHRGQTY) {
       this.CHRGQTY = CHRGQTY;
  }

  public String getCHRGQTY2() {
       return CHRGQTY2;
  }

  public void setCHRGQTY2(String CHRGQTY2) {
       this.CHRGQTY2 = CHRGQTY2;
  }

  public String getBIGO() {
       return BIGO;
  }

  public void setBIGO(String BIGO) {
       this.BIGO = BIGO;
  }

  public String getCANTIME() {
       return CANTIME;
  }

  public void setCANTIME(String CANTIME) {
       this.CANTIME = CANTIME;
  }

  public String getDMTIME() {
       return DMTIME;
  }

  public void setDMTIME(String DMTIME) {
       this.DMTIME = DMTIME;
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

}
