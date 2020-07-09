package com.pm.persistence.inspectplan.entity;

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
 * 表ins_plan_machine_info 的实体类
 * @author 刘镝
 */
@Entity(name = "ins_plan_machine_info")
public class InsPlanMachineInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 3019480109774740100L;

  public InsPlanMachineInfo clone() {
      InsPlanMachineInfo o = null;
      try {
          o = (InsPlanMachineInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 
   */
  @ID
  @Column(name = "plan_machine_id", comment = "")
  private String plan_machine_id;

  /**
   * 巡检计划id
   */
  @Column(name = "ins_plan_id", comment = "巡检计划id")
  private String ins_plan_id;

  /**
   * 设备id
   */
  @Column(name = "ins_machine_id", comment = "设备id")
  private String ins_machine_id;
  /**
   * 设备name
   */
  @Transient
  @Column(name = "machine_nm")
  private String machine_nm;
  /**
   * 设备类别
   */
  @Transient
  @Column(name = "machine_species_id")
  private String machine_species_id;
  /**
   * 设备类型
   */
  @Transient
  @Column(name = "machine_type_id")
  private String machine_type_id;
  /**
   * 设备区域
   */
  @Transient
  @Column(name = "area_id")
  private String area_id;
  
  /**
   * 检查类型分类
   */
  @Column(name = "chk_type", comment = "检查类型分类")
  private String chk_type;

  /**
   * 已检查项目数
   */
  @Column(name = "ins_check_num", comment = "已检查项目数")
  private String ins_check_num;

  /**
   * 需检查项目总数
   */
  @Column(name = "ins_check_all", comment = "需检查项目总数")
  private String ins_check_all;
  /**
   * 检查完成率
   */
  @Column(name = "ins_check_rate", comment = "检查完成率")
  private String ins_check_rate;
  
  /**
   * 实际时间
   */
  @Column(name = "operate_date", comment = "实际时间")
  private String operate_date;
  /**
   * 首次创建
   */
  @Column(name = "create_id", comment = "首次创建")
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

  public String getPlan_machine_id() {
       return plan_machine_id;
  }

  public void setPlan_machine_id(String plan_machine_id) {
       this.plan_machine_id = plan_machine_id;
  }

  public String getIns_plan_id() {
       return ins_plan_id;
  }

  public void setIns_plan_id(String ins_plan_id) {
       this.ins_plan_id = ins_plan_id;
  }

  public String getIns_machine_id() {
       return ins_machine_id;
  }

  public void setIns_machine_id(String ins_machine_id) {
       this.ins_machine_id = ins_machine_id;
  }

  public String getChk_type() {
       return chk_type;
  }

  public void setChk_type(String chk_type) {
       this.chk_type = chk_type;
  }

  public String getIns_check_num() {
       return ins_check_num;
  }

  public void setIns_check_num(String ins_check_num) {
       this.ins_check_num = ins_check_num;
  }

  public String getIns_check_all() {
       return ins_check_all;
  }

  public void setIns_check_all(String ins_check_all) {
       this.ins_check_all = ins_check_all;
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

public String getMachine_species_id() {
	return machine_species_id;
}

public void setMachine_species_id(String machine_species_id) {
	this.machine_species_id = machine_species_id;
}

public String getMachine_type_id() {
	return machine_type_id;
}

public void setMachine_type_id(String machine_type_id) {
	this.machine_type_id = machine_type_id;
}

public String getOperate_date() {
	return operate_date;
}

public void setOperate_date(String operate_date) {
	this.operate_date = operate_date;
}

public String getArea_id() {
	return area_id;
}

public void setArea_id(String area_id) {
	this.area_id = area_id;
}

public String getIns_check_rate() {
	return ins_check_rate;
}

public void setIns_check_rate(String ins_check_rate) {
	this.ins_check_rate = ins_check_rate;
}

}
