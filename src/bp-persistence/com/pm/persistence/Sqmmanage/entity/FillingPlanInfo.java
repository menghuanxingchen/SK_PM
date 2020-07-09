package com.pm.persistence.Sqmmanage.entity;

import java.io.Serializable;
import java.util.List;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表filling_plan_info 的实体类
 * @author 刘镝
 */
@Entity(name = "filling_plan_info")
public class FillingPlanInfo extends BaseEntity implements Serializable, Cloneable {

  public FillingPlanInfo clone() {
      FillingPlanInfo o = null;
      try {
          o = (FillingPlanInfo) super.clone();
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
   * 日期
   */
  @Column(name = "CNFYMD", comment = "日期")
  private String CNFYMD;
  
  /**
   * 结束日期
   */
  @Transient
  @Column(name = "ENDCNFYMD", comment = "结束日期")
  private String ENDCNFYMD;
  
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
   * LOTNO
   */
  @Column(name = "LOTNO", comment = "LOTNO")
  private String LOTNO;

  /**
   * PKG
   */
  @Column(name = "PKG", comment = "PKG")
  private String PKG;

  /**
   * 罐装Tank
   */
  @Column(name = "HTANK", comment = "罐装Tank")
  private String HTANK;

  /**
   * 移送量
   */
  @Column(name = "MOVEQTY", comment = "移送量")
  private String MOVEQTY;

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
   * 密度
   */
  @Column(name = "DENSITY", comment = "密度")
  private String DENSITY;

  /**
   * 配方ID
   */
  @Column(name = "COMPOUNDID", comment = "配方ID")
  private String COMPOUNDID;

  /**
   * 生产线id
   */
  @Column(name = "LINEID", comment = "生产线id")
  private String LINEID;

  /**
   * 生产线名
   */
  @Column(name = "LINENAME", comment = "生产线名")
  private String LINENAME;

  /**
   * 贴标签与否
   */
  @Column(name = "LABELSTATE", comment = "贴标签与否")
  private String LABELSTATE;

  /**
   * 罐装数量
   */
  @Column(name = "CHRGQTY", comment = "罐装数量")
  private String CHRGQTY;

  /**
   * 罐装容量(1L/3L/6L/18L/200L/1000L)
   */
  @Column(name = "CAPA", comment = "罐装容量(1L/3L/6L/18L/200L/1000L)")
  private String CAPA;

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
   * 灌装状态(0:未进行，1:进行中，2:故障，3:已完成)
   */
  @Column(name = "FILL_STATE", comment = "灌装状态(0:未进行，1:进行中，2:故障，3:已完成)")
  private Integer FILL_STATE;
  
  /**
   * 计划开始时间
   */
  @Column(name = "FILL_STDATE", comment = "计划开始时间")
  private String FILL_STDATE;
  
  /**
   * 计划结束时间
   */
  @Column(name = "FILL_EDDATE", comment = "计划结束时间")
  private String FILL_EDDATE;
  
  /**
   * 投诉数量
   */
  @Transient
  @Column(name = "complaint_num", comment = "投诉数量")
  private String complaint_num;
  
  /**
   * 投诉时间
   */
  @Transient
  @Column(name = "complaint_date", comment = "投诉时间")
  private String complaint_date;
  
  /**
   * 投诉内容
   */
  @Transient
  @Column(name = "complaint_content", comment = "投诉内容")
  private String complaint_content;

  /**
   * 增加措施
   */
  @Transient
  @Column(name = "add_measures", comment = "增加措施")
  private String add_measures;
  
  /**
   * 故障申报人
   */
  @Column(name = "fault_user_id", comment = "故障申报人")
  private String fault_user_id;
  
  /**
   * 故障申报时间
   */
  @Column(name = "fault_time", comment = "故障申报时间")
  private String fault_time;
  
  /**
   * 故障设备
   */
  @Column(name = "equipmentId", comment = "故障设备")
  private String equipmentId;
  
  /**
   * 灌装计划结束人
   */
  @Column(name = "over_plan_user", comment = "灌装计划结束人")
  private String over_plan_user;
  
  /**
   * 开始物流码
   */
  @Column(name = "start_pack_code", comment = "开始物流码")
  private String start_pack_code;
  
  /**
   * 是否5S检测
   */
  @Column(name = "fourSYn", comment = "是否5S检测：Y是，N否")
  private String fourSYn;
  
  /**
   * 第几次检测
   */
  @Transient
  @Column(name = "testNum", comment = "第几次检测")
  private int testNum;
  
  /**
   * 灌装状态名称(0:未进行，1:进行中，2:故障，3:已完成)
   */
  @Transient
  @Column(name = "FILL_STATE_NAME", comment = "灌装状态(0:未进行，1:进行中，2:故障，3:已完成)")
  private String FILL_STATE_NAME;
  
  /**
   * 检测流程
   */
  @Transient
  @Column(name = "testingProcessList", comment = "")
  private List<TestingProcessInfo> testingProcessList;
  
  /**
   * 该生产线最近一次生产的产品code
   */
  @Transient
  @Column(name = "latelyPROD", comment = "该生产线最近一次生产的产品code")
  private String latelyPROD;
  
  public String getFourSYn() {
	return fourSYn;
}

public void setFourSYn(String fourSYn) {
	this.fourSYn = fourSYn;
}

public String getStart_pack_code() {
	return start_pack_code;
}

public void setStart_pack_code(String start_pack_code) {
	this.start_pack_code = start_pack_code;
}

public String getLatelyPROD() {
	return latelyPROD;
}

public void setLatelyPROD(String latelyPROD) {
	this.latelyPROD = latelyPROD;
}

public String getAdd_measures() {
	return add_measures;
}

public void setAdd_measures(String add_measures) {
	this.add_measures = add_measures;
}

public String getOver_plan_user() {
	return over_plan_user;
}

public void setOver_plan_user(String over_plan_user) {
	this.over_plan_user = over_plan_user;
}

public List<TestingProcessInfo> getTestingProcessList() {
	return testingProcessList;
}

public void setTestingProcessList(List<TestingProcessInfo> testingProcessList) {
	this.testingProcessList = testingProcessList;
}

public String getFILL_STATE_NAME() {
	return FILL_STATE_NAME;
}

public void setFILL_STATE_NAME(String fILL_STATE_NAME) {
	FILL_STATE_NAME = fILL_STATE_NAME;
}

public String getEquipmentId() {
	return equipmentId;
}

public void setEquipmentId(String equipmentId) {
	this.equipmentId = equipmentId;
}

public int getTestNum() {
	return testNum;
}

public void setTestNum(int testNum) {
	this.testNum = testNum;
}
  
  public Integer getFILL_STATE() {
	return FILL_STATE;
}

public void setFILL_STATE(Integer fILL_STATE) {
	FILL_STATE = fILL_STATE;
}

public String getFILL_STDATE() {
	return FILL_STDATE;
}

public void setFILL_STDATE(String fILL_STDATE) {
	FILL_STDATE = fILL_STDATE;
}

public String getFILL_EDDATE() {
	return FILL_EDDATE;
}

public void setFILL_EDDATE(String fILL_EDDATE) {
	FILL_EDDATE = fILL_EDDATE;
}

public String getComplaint_date() {
	return complaint_date;
}

public void setComplaint_date(String complaint_date) {
	this.complaint_date = complaint_date;
}

public String getComplaint_content() {
	return complaint_content;
}

public void setComplaint_content(String complaint_content) {
	this.complaint_content = complaint_content;
}

public String getComplaint_num() {
	return complaint_num;
}

public void setComplaint_num(String complaint_num) {
	this.complaint_num = complaint_num;
}

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

  public String getENDCNFYMD() {
      return ENDCNFYMD;
 }

 public void setENDCNFYMD(String ENDCNFYMD) {
      this.ENDCNFYMD = ENDCNFYMD;
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

  public String getLOTNO() {
       return LOTNO;
  }

  public void setLOTNO(String LOTNO) {
       this.LOTNO = LOTNO;
  }

  public String getPKG() {
       return PKG;
  }

  public void setPKG(String PKG) {
       this.PKG = PKG;
  }

  public String getHTANK() {
       return HTANK;
  }

  public void setHTANK(String HTANK) {
       this.HTANK = HTANK;
  }

  public String getMOVEQTY() {
       return MOVEQTY;
  }

  public void setMOVEQTY(String MOVEQTY) {
       this.MOVEQTY = MOVEQTY;
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

  public String getDENSITY() {
       return DENSITY;
  }

  public void setDENSITY(String DENSITY) {
       this.DENSITY = DENSITY;
  }

  public String getCOMPOUNDID() {
       return COMPOUNDID;
  }

  public void setCOMPOUNDID(String COMPOUNDID) {
       this.COMPOUNDID = COMPOUNDID;
  }

  public String getLINEID() {
       return LINEID;
  }

  public void setLINEID(String LINEID) {
       this.LINEID = LINEID;
  }

  public String getLINENAME() {
       return LINENAME;
  }

  public void setLINENAME(String LINENAME) {
       this.LINENAME = LINENAME;
  }

  public String getLABELSTATE() {
       return LABELSTATE;
  }

  public void setLABELSTATE(String LABELSTATE) {
       this.LABELSTATE = LABELSTATE;
  }

  public String getCHRGQTY() {
       return CHRGQTY;
  }

  public void setCHRGQTY(String CHRGQTY) {
       this.CHRGQTY = CHRGQTY;
  }

  public String getCAPA() {
       return CAPA;
  }

  public void setCAPA(String CAPA) {
       this.CAPA = CAPA;
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

  public String getFault_user_id() {
       return fault_user_id;
  }

  public void setFault_user_id(String fault_user_id) {
       this.fault_user_id = fault_user_id;
  }

  public String getFault_time() {
      return fault_time;
 }

 public void setFault_time(String fault_time) {
      this.fault_time = fault_time;
 }

 public String getUser_yn() {
      return user_yn;
 }

 public void setUser_yn(String user_yn) {
      this.user_yn = user_yn;
 }

}
