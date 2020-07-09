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
 * 表testing_process_info 的实体类
 * @author 刘镝
 */
@Entity(name = "testing_process_info")
public class TestingProcessInfo extends BaseEntity implements Serializable, Cloneable {

  public TestingProcessInfo clone() {
      TestingProcessInfo o = null;
      try {
          o = (TestingProcessInfo) super.clone();
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
   * 生产线id
   */
  @Column(name = "lineId", comment = "生产线id")
  private String lineId;

  /**
   * 生产线名称
   */

  @Column(name = "lineName", comment = "生产线名称")
  private String lineName;

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
   * 类型
   */

  @Column(name = "productType", comment = "选型")
  private String productType;

  /**
   * 客户
   */

  @Column(name = "customName", comment = "客户")
  private String customName;
  
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
   * 是否使用
   */
  @Column(name = "useYn", comment = "是否使用")
  private String useYn;

  /**
   * 排序
   */
  @Column(name = "orderNum", comment = "排序")
  private String orderNum;

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
   * 批次号
   */
  @Transient
  @Column(name = "lotnumver", comment = "批次号")
  private String lotnumver;
  
  /**
   * 状态
   */
  @Transient
  @Column(name = "testState", comment = "状态")
  private String testState;
  
  /**
   * 状态nm
   */
  @Transient
  @Column(name = "testStateName", comment = "状态nm")
  private String testStateName;
  
  /**
   * 检测项集合
   */
  @Transient
  private List<TestItemInfo> testItemInfoList;
  
  
  public List<TestItemInfo> getTestItemInfoList() {
	return testItemInfoList;
}

public void setTestItemInfoList(List<TestItemInfo> testItemInfoList) {
	this.testItemInfoList = testItemInfoList;
}

public String getTestStateName() {
	return testStateName;
}

public void setTestStateName(String testStateName) {
	this.testStateName = testStateName;
}

public String getTestState() {
	return testState;
}

public void setTestState(String testState) {
	this.testState = testState;
}

public String getLotnumver() {
	return lotnumver;
}

public void setLotnumver(String lotnumver) {
	this.lotnumver = lotnumver;
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
  public String getProductType() {
      return productType;
 }

 public void setProductType(String productType) {
      this.productType = productType;
 }

 public String getCustomName() {
      return customName;
 }

 public void setCustomName(String customName) {
      this.customName = customName;
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

  public String getUseYn() {
       return useYn;
  }

  public void setUseYn(String useYn) {
       this.useYn = useYn;
  }

  public String getOrderNum() {
       return orderNum;
  }

  public void setOrderNum(String orderNum) {
       this.orderNum = orderNum;
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

}
