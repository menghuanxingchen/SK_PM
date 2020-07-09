package com.pm.persistence.repair.entity;

import java.io.Serializable;
import java.util.List;

import com.pm.persistence.systemfunction.entity.MaintenanceItemInfo;
import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表repair_machine_detail 的实体类
 * @author 刘镝
 */
@Entity(name = "repair_machine_detail")
public class RepairMachineDetail extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 4687001109298165441L;

  public RepairMachineDetail clone() {
      RepairMachineDetail o = null;
      try {
          o = (RepairMachineDetail) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 维修设备结果id
   */
  @ID
  @Column(name = "repair_machine_id", comment = "维修设备结果id")
  private String repair_machine_id;

  /**
   * 维修结果ID
   */
  @Column(name = "repair_result_id", comment = "维修结果ID")
  private String repair_result_id;

  /**
   * 设备id
   */
  @Column(name = "machine_id", comment = "设备id")
  private String machine_id;

  /**
   * 停机时间
   */
  @Column(name = "down_hour", comment = "停机时间")
  private String down_hour;

  /**
   * 维修内容
   */
  @Column(name = "repair_detail", comment = "维修内容")
  private String repair_detail;

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
  @Transient
  @Column(name = "machine_nm", comment = "")
  private String machine_nm;
  /**
   * 维修内容名
   */
  @Transient
  @Column(name = "repair_detail_nm", comment = "")
  private String repair_detail_nm;
  
  /**
   * 
   */
  @Transient
  @Column(name = "machine_species__nm", comment = "")
  private String machine_species__nm;
  
  /**
   * 
   */
  @Transient
  @Column(name = "machine_type_nm", comment = "")
  private String machine_type_nm;
  
  /**
   * 
   */
  @Transient
  @Column(name = "", comment = "")
  private List<MaintenanceItemInfo> repairDetailGrouplist;

  public String getRepair_machine_id() {
       return repair_machine_id;
  }

  public void setRepair_machine_id(String repair_machine_id) {
       this.repair_machine_id = repair_machine_id;
  }

  public String getRepair_result_id() {
       return repair_result_id;
  }

  public void setRepair_result_id(String repair_result_id) {
       this.repair_result_id = repair_result_id;
  }

  public String getMachine_id() {
       return machine_id;
  }

  public void setMachine_id(String machine_id) {
       this.machine_id = machine_id;
  }

  public String getDown_hour() {
       return down_hour;
  }

  public void setDown_hour(String down_hour) {
       this.down_hour = down_hour;
  }

  public String getRepair_detail() {
       return repair_detail;
  }

  public void setRepair_detail(String repair_detail) {
       this.repair_detail = repair_detail;
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

public String getMachine_nm() {
	return machine_nm;
}

public void setMachine_nm(String machine_nm) {
	this.machine_nm = machine_nm;
}

public List<MaintenanceItemInfo> getRepairDetailGrouplist() {
	return repairDetailGrouplist;
}

public void setRepairDetailGrouplist(
		List<MaintenanceItemInfo> repairDetailGrouplist) {
	this.repairDetailGrouplist = repairDetailGrouplist;
}

public String getRepair_detail_nm() {
	return repair_detail_nm;
}

public void setRepair_detail_nm(String repair_detail_nm) {
	this.repair_detail_nm = repair_detail_nm;
}

public String getMachine_species__nm() {
	return machine_species__nm;
}

public void setMachine_species__nm(String machine_species__nm) {
	this.machine_species__nm = machine_species__nm;
}

public String getMachine_type_nm() {
	return machine_type_nm;
}

public void setMachine_type_nm(String machine_type_nm) {
	this.machine_type_nm = machine_type_nm;
}



}
