package com.sqm.sqmequipmenteverystate.entity;

import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表sqm_equipment_every_state 的实体类
 * @author 刘镝
 */
@Entity(name = "sqm_equipment_every_state")
public class SqmEquipmentEveryState extends BaseEntity implements Serializable, Cloneable {

  public SqmEquipmentEveryState clone() {
      SqmEquipmentEveryState o = null;
      try {
          o = (SqmEquipmentEveryState) super.clone();
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
   * 设备状态（初始值为:1；1：未检测2：已检测正常3：已检测检测信息与计划不一致4：设备故障5：已检测检测信息与他人不一致6：故障已解除待检）
   */
  @Column(name = "eState", comment = "设备状态（初始值为:1；1：未检测2：已检测正常3：已检测检测信息与计划不一致4：设备故障5：已检测检测信息与他人不一致6：故障已解除待检）")
  private String eState;

  /**
   * 设备状态说明
   */
  @Column(name = "eStateName", comment = "设备状态说明")
  private String eStateName;

  /**
   * 检测员
   */
  @Column(name = "inspector", comment = "检测员")
  private String inspector;
  
  /**
   * 设备原状态（初始值为:1；1：未检测2：已检测正常3：已检测检测信息与计划不一致4：设备故障5：已检测检测信息与他人不一致6：故障已解除待检）
   */
  @Column(name = "oldState", comment = "设备状态（初始值为:1；1：未检测2：已检测正常3：已检测检测信息与计划不一致4：设备故障5：已检测检测信息与他人不一致6：故障已解除待检）")
  private String oldState;

public String getOldState() {
	return oldState;
}

public void setOldState(String oldState) {
	this.oldState = oldState;
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

  public String getEState() {
       return eState;
  }

  public void setEState(String eState) {
       this.eState = eState;
  }

  public String getEStateName() {
       return eStateName;
  }

  public void setEStateName(String eStateName) {
       this.eStateName = eStateName;
  }

  public String getInspector() {
       return inspector;
  }

  public void setInspector(String inspector) {
       this.inspector = inspector;
  }

}
