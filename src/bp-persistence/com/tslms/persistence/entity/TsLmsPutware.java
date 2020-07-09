package com.tslms.persistence.entity;

import java.io.Serializable;
import java.util.Date;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表ts_lms_putware 的实体类
 * @author 刘镝
 */
@Entity(name = "ts_lms_putware")
public class TsLmsPutware extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 7055475116533424020L;

  public TsLmsPutware clone() {
      TsLmsPutware o = null;
      try {
          o = (TsLmsPutware) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 
   */
  @ID
  @Column(name = "putid", comment = "")
  private String putid;

  /**
   * 
   */
  @Column(name = "putno", comment = "")
  private String putno;

  /**
   * 
   */
  @Column(name = "warename", comment = "")
  private String warename;

  /**
   * 
   */
  @Column(name = "customer", comment = "")
  private String customer;

  /**
   * 
   */
  @Column(name = "part", comment = "")
  private String part;

  /**
   * 
   */
  @Column(name = "materialtype", comment = "")
  private String materialtype;

  /**
   * 
   */
  @Column(name = "materialname", comment = "")
  private String materialname;

  /**
   * 
   */
  @Column(name = "matercount", comment = "")
  private String matercount;

  /**
   * 
   */
  @Column(name = "puttime", comment = "")
  private Date puttime;

  /**
   * 
   */
  @Column(name = "putcount", comment = "")
  private String putcount;

  public String getPutid() {
       return putid;
  }

  public void setPutid(String putid) {
       this.putid = putid;
  }

  public String getPutno() {
       return putno;
  }

  public void setPutno(String putno) {
       this.putno = putno;
  }

  public String getWarename() {
       return warename;
  }

  public void setWarename(String warename) {
       this.warename = warename;
  }

  public String getCustomer() {
       return customer;
  }

  public void setCustomer(String customer) {
       this.customer = customer;
  }

  public String getPart() {
       return part;
  }

  public void setPart(String part) {
       this.part = part;
  }

  public String getMaterialtype() {
       return materialtype;
  }

  public void setMaterialtype(String materialtype) {
       this.materialtype = materialtype;
  }

  public String getMaterialname() {
       return materialname;
  }

  public void setMaterialname(String materialname) {
       this.materialname = materialname;
  }

  public String getMatercount() {
       return matercount;
  }

  public void setMatercount(String matercount) {
       this.matercount = matercount;
  }

  public Date getPuttime() {
       return puttime;
  }

  public void setPuttime(Date puttime) {
       this.puttime = puttime;
  }

  public String getPutcount() {
       return putcount;
  }

  public void setPutcount(String putcount) {
       this.putcount = putcount;
  }

}
