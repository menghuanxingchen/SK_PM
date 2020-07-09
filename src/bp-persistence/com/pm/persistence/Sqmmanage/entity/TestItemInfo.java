package com.pm.persistence.Sqmmanage.entity;

import java.io.Serializable;
import java.util.List;

import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表test_item_info 的实体类
 * @author 刘镝
 */
@Entity(name = "test_item_info")
public class TestItemInfo extends BaseEntity implements Serializable, Cloneable {

  public TestItemInfo clone() {
      TestItemInfo o = null;
      try {
          o = (TestItemInfo) super.clone();
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
   * 
   */
  @Column(name = "lineId", comment = "")
  private String lineId;

  /**
   * 
   */
  @Column(name = "lineName", comment = "")
  private String lineName;

  /**
   * 设备id
   */
  @Column(name = "equipmentId", comment = "设备id")
  private String equipmentId;

  /**
   * 设备名称
   */
  @Column(name = "equipmentName", comment = "设备名称")
  private String equipmentName;

  /**
   * 产品code
   */
  @Column(name = "productCode", comment = "产品code")
  private String productCode;

  /**
   * 产品名称
   */
  @Column(name = "productName", comment = "产品名称")
  private String productName;

  /**
   * 
   */
  @Column(name = "testItem", comment = "")
  private String testItem;

  /**
   * 
   */
  @Column(name = "create_id", comment = "")
  private String create_id;

  /**
   * 
   */
  @Column(name = "create_time", comment = "")
  private String create_time;

  /**
   * 
   */
  @Column(name = "update_id", comment = "")
  private String update_id;

  /**
   * 
   */
  @Column(name = "update_time", comment = "")
  private String update_time;

  /**
   * 正常、关闭
   */
  @Column(name = "userYn", comment = "正常、关闭")
  private String userYn;
  

  
  /**
   * 排序
   */
  @Column(name = "orderNum", comment = "排序")
  private String orderNum;
  
  /**
   * 正常、关闭name
   */
  @Transient
  @Column(name = "userYn_nm", comment = "正常、关闭")
  private String userYn_nm;
  
  /**
   * 对应test_result_4、test_result_18、test_result_200中字段
   */

  @Column(name = "testResultNoID", comment = "对应test_result_4、test_result_18、test_result_200中字段")
  private String testResultNoID;
  
  /**
   * 对应的表名
   */

  @Column(name = "tableName", comment = "对应的表名")
  private String tableName;
  
  /**
   * 检测结果
   */
  @Transient
  @Column(name = "testResult", comment = "检测结果")
  private String testResult;
  
  /**
   * 检测结果主键
   */
  @Transient
  @Column(name = "testResultId", comment = "检测结果主键")
  private String testResultId;
  
  /**
   * 枚举值
   */
  @Transient
  @Column(name = "sysCodeInfoList", comment = "枚举值")
  private List<SysCodeInfo> sysCodeInfoList;
  
  /**
   * 计划主键
   */
  @Transient
  @Column(name = "planId", comment = "计划主键")
  private String planId;
  
  /**
   * 第几次检测
   */
  @Transient
  @Column(name = "test_num", comment = "第几次检测")
  private int test_num;
  
  public int getTest_num() {
	return test_num;
}

public void setTest_num(int test_num) {
	this.test_num = test_num;
}

public String getPlanId() {
	return planId;
}

public void setPlanId(String planId) {
	this.planId = planId;
}

public List<SysCodeInfo> getSysCodeInfoList() {
	return sysCodeInfoList;
}

public void setSysCodeInfoList(List<SysCodeInfo> sysCodeInfoList) {
	this.sysCodeInfoList = sysCodeInfoList;
}

public String getTestResultId() {
	return testResultId;
}

public void setTestResultId(String testResultId) {
	this.testResultId = testResultId;
}

public String getTestResult() {
	return testResult;
}

public void setTestResult(String testResult) {
	this.testResult = testResult;
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

  public String getLineName() {
       return lineName;
  }

  public void setLineName(String lineName) {
       this.lineName = lineName;
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

  public String getProductCode() {
       return productCode;
  }

  public void setProductCode(String productCode) {
       this.productCode = productCode;
  }

  public String getProductName() {
       return productName;
  }

  public void setProductName(String productName) {
       this.productName = productName;
  }

  public String getTestItem() {
       return testItem;
  }

  public void setTestItem(String testItem) {
       this.testItem = testItem;
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

  public String getUserYn() {
       return userYn;
  }

  public void setUserYn(String userYn) {
       this.userYn = userYn;
  }
  public String getUserYn_nm() {
      return userYn_nm;
 }

 public void setUserYn_nm(String userYn_nm) {
      this.userYn_nm = userYn_nm;
 }
 
 public String getTestResultNoID() {
     return testResultNoID;
}

public void setTestResultNoID(String testResultNoID) {
     this.testResultNoID = testResultNoID;
}

public String getTableName() {
    return tableName;
}

public void setTableName(String tableName) {
    this.tableName = tableName;
}

public String getOrderNum() {
    return orderNum;
}

public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
}
}
