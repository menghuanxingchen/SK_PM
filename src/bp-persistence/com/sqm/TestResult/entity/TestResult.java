package com.sqm.TestResult.entity;

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
 * 表test_result 的实体类
 * @author 刘镝
 */
@Entity(name = "test_result")
public class TestResult extends BaseEntity implements Serializable, Cloneable {

  public TestResult clone() {
      TestResult o = null;
      try {
          o = (TestResult) super.clone();
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
   * 生产线
   */
  @Column(name = "lineId", comment = "生产线")
  private String lineId;

  /**
   * 批次号
   */
  @Column(name = "lotnumver", comment = "批次号")
  private String lotnumver;

  /**
   * 产品Code
   */
  @Column(name = "produnctCode", comment = "产品Code")
  private String produnctCode;

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
   * 修改时间
   */
  @Column(name = "update_time", comment = "修改时间")
  private String update_time;

  /**
   * 
   */
  @Column(name = "user_yn", comment = "")
  private Integer user_yn;

  /**
   * 检测次数
   */
  @Column(name = "test_num", comment = "检测次数")
  private Integer test_num;

  /**
   * 修改次数
   */
  @Column(name = "update_num", comment = "修改次数")
  private Integer update_num;

  /**
   * 修改原因
   */
  @Column(name = "update_cause", comment = "修改原因")
  private String update_cause;

  /**
   * 检测结果
   */
  @Column(name = "testResult", comment = "检测结果")
  private String testResult;

  /**
   * 检测项
   */
  @Column(name = "testItem", comment = "检测项")
  private String testItem;
  
  /**
   * 检测项名称
   */
  @Column(name = "testItemName", comment = "检测项名称")
  private String testItemName;
  
  /**
   * 设备id
   */
  @Column(name = "equipmentId", comment = "设备id")
  private String equipmentId;
  
  /**
   * 灌装计划id
   */
  @Column(name = "planId", comment = "灌装计划id")
  private String planId;
  
  /**
   * 检测项状态（0:未检 1：正常2：与计划不一致3：与他人不一致4：故障）
   */
  @Column(name = "rState", comment = "检测项状态（0:未检 1：正常2：与计划不一致3：与他人不一致4：故障）")
  private String rState;
  
  /**
   * 设备名称
   */
  @Transient
  @Column(name = "equipmentName", comment = "设备名称")
  private String equipmentName;
  
  /**
   * 检测员nm
   */
  @Transient
  @Column(name = "create_name", comment = "检测员nm")
  private String create_name;
  
  /**
   * 是否修改
   */
  @Transient
  @Column(name = "update_yn", comment = "是否修改：yn")
  private String update_yn;
  
  /**
   * 与计划值是否一致
   */
  @Transient
  @Column(name = "planfit_yn", comment = "与计划值是否一致：yn")
  private String planfit_yn;
  
  /**
   * 与他人是否一致
   */
  @Transient
  @Column(name = "otherfit_yn", comment = "与他人是否一致：yn")
  private String otherfit_yn;
  
  
public String getrState() {
	return rState;
}

public void setrState(String rState) {
	this.rState = rState;
}

public String getPlanId() {
	return planId;
}

public void setPlanId(String planId) {
	this.planId = planId;
}

public String getOtherfit_yn() {
	return otherfit_yn;
}

public void setOtherfit_yn(String otherfit_yn) {
	this.otherfit_yn = otherfit_yn;
}

public String getPlanfit_yn() {
	return planfit_yn;
}

public void setPlanfit_yn(String planfit_yn) {
	this.planfit_yn = planfit_yn;
}

public String getUpdate_yn() {
	return update_yn;
}

public void setUpdate_yn(String update_yn) {
	this.update_yn = update_yn;
}

public String getCreate_name() {
	return create_name;
}

public void setCreate_name(String create_name) {
	this.create_name = create_name;
}

public String getId() {
       return id;
  }

  public void setId(String id) {
       this.id = id;
  }

  public String getLineId() {
       return lineId;
  }

  public void setLineId(String lineId) {
       this.lineId = lineId;
  }

  public String getLotnumver() {
       return lotnumver;
  }

  public void setLotnumver(String lotnumver) {
       this.lotnumver = lotnumver;
  }

  public String getProdunctCode() {
       return produnctCode;
  }

  public void setProdunctCode(String produnctCode) {
       this.produnctCode = produnctCode;
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

  public String getUpdate_time() {
       return update_time;
  }

  public void setUpdate_time(String update_time) {
       this.update_time = update_time;
  }

  public Integer getUser_yn() {
       return user_yn;
  }

  public void setUser_yn(Integer i) {
       this.user_yn = i;
  }

  public Integer getTest_num() {
       return test_num;
  }

  public void setTest_num(Integer test_num) {
       this.test_num = test_num;
  }

  public Integer getUpdate_num() {
       return update_num;
  }

  public void setUpdate_num(Integer update_num) {
       this.update_num = update_num;
  }

  public String getUpdate_cause() {
       return update_cause;
  }

  public void setUpdate_cause(String update_cause) {
       this.update_cause = update_cause;
  }

  public String getTestResult() {
       return testResult;
  }

  public void setTestResult(String testResult) {
       this.testResult = testResult;
  }

  public String getTestItem() {
       return testItem;
  }

  public void setTestItem(String testItem) {
       this.testItem = testItem;
  }

  public String getTestItemName() {
      return testItemName;
 }

 public void setTestItemName(String testItemName) {
      this.testItemName = testItemName;
 }
 
  public String getEquipmentId() {
       return equipmentId;
  }

  public void setEquipmentId(String equipmentId) {
       this.equipmentId = equipmentId;
  }
  public String getEquipmentName() {
      return equipmentName;
 }

 public void setEquipmentName(String equipmentName) {
      this.equipmentName = equipmentName;
 }
}
