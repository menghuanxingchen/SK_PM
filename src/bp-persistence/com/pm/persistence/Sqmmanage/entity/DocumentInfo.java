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
 * 表document_info 的实体类
 * @author 刘镝
 */
@Entity(name = "document_info")
public class DocumentInfo extends BaseEntity implements Serializable, Cloneable {

  public DocumentInfo clone() {
      DocumentInfo o = null;
      try {
          o = (DocumentInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 
   */
  @ID
  @Column(name = "ID", comment = "")
  private String ID;

  /**
   * 
   */
  @Column(name = "FILETITLE", comment = "")
  private String FILETITLE;

  /**
   * 
   */
  @Column(name = "NOTENAME", comment = "")
  private String NOTENAME;

  /**
   * 
   */
  @Column(name = "NOTE", comment = "")
  private String NOTE;
  
  /**
   * 
   */
  @Column(name = "FILESIZE", comment = "")
  private String FILESIZE;

  /**
   * 
   */
  @Column(name = "FILENAME", comment = "")
  private String FILENAME;

  /**
   * 
   */
  @Column(name = "CREATEDATE", comment = "")
  private String CREATEDATE;

  /**
   * 
   */
  @Column(name = "CREATEUSER", comment = "")
  private String CREATEUSER;

  /**
   * 
   */
  @Column(name = "FILEVERSION", comment = "")
  private String FILEVERSION;

  /**
   * 
   */
  @Column(name = "FILEREMARK", comment = "")
  private String FILEREMARK;

  /**
   * 
   */
  @Column(name = "FILEURL", comment = "")
  private String FILEURL;
  
  /**
   * 
   */
  @Column(name = "COUNTNAME", comment = "")
  private String COUNTNAME;

  public String getID() {
       return ID;
  }

  public void setID(String ID) {
       this.ID = ID;
  }

  public String getFILETITLE() {
       return FILETITLE;
  }

  public void setFILETITLE(String FILETITLE) {
       this.FILETITLE = FILETITLE;
  }

  public String getNOTENAME() {
       return NOTENAME;
  }

  public void setNOTENAME(String NOTENAME) {
       this.NOTENAME = NOTENAME;
  }
  
  public String getNOTE() {
      return NOTE;
 }

 public void setNOTE(String NOTE) {
      this.NOTE = NOTE;
 }

  public String getFILESIZE() {
       return FILESIZE;
  }

  public void setFILESIZE(String FILESIZE) {
       this.FILESIZE = FILESIZE;
  }

  public String getFILENAME() {
       return FILENAME;
  }

  public void setFILENAME(String FILENAME) {
       this.FILENAME = FILENAME;
  }

  public String getCREATEDATE() {
       return CREATEDATE;
  }

  public void setCREATEDATE(String CREATEDATE) {
       this.CREATEDATE = CREATEDATE;
  }

  public String getCREATEUSER() {
       return CREATEUSER;
  }

  public void setCREATEUSER(String CREATEUSER) {
       this.CREATEUSER = CREATEUSER;
  }

  public String getFILEVERSION() {
       return FILEVERSION;
  }

  public void setFILEVERSION(String FILEVERSION) {
       this.FILEVERSION = FILEVERSION;
  }

  public String getFILEREMARK() {
       return FILEREMARK;
  }

  public void setFILEREMARK(String FILEREMARK) {
       this.FILEREMARK = FILEREMARK;
  }

  public String getFILEURL() {
       return FILEURL;
  }

  public void setFILEURL(String FILEURL) {
       this.FILEURL = FILEURL;
  }
  public String getCOUNTNAME() {
      return COUNTNAME;
 }

 public void setCOUNTNAME(String COUNTNAME) {
      this.COUNTNAME = COUNTNAME;
 }
}
