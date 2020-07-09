package com.pm.persistence.preventplan.entity;

import java.io.Serializable;

import com.repast.core.annotation.Column;
import com.repast.core.annotation.Entity;
import com.repast.core.annotation.ID;
import com.repast.core.annotation.Transient;
import com.repast.core.system.BaseEntity;
/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表pre_plan_info 的实体类
 * @author 刘镝
 */
@Entity(name = "pre_plan_info")
public class PrePlanInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 5795186162289562464L;

  public PrePlanInfo clone() {
      PrePlanInfo o = null;
      try {
          o = (PrePlanInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 保养计划id
   */
  @ID
  @Column(name = "pre_plan_id", comment = "保养计划id")
  private String pre_plan_id;

  /**
   * 保养计划名称
   */
  @Column(name = "pre_plan_nm", comment = "保养计划名称")
  private String pre_plan_nm;

  /**
   * 计划周期
   */
  @Column(name = "pre_plan_cycle", comment = "计划周期")
  private String pre_plan_cycle;
  
  /**
   * 计划周期名称
   */
  @Transient
  @Column(name = "pre_plan_cycle_nm", comment = "计划周期名称")
  private String pre_plan_cycle_nm;

  /**
   * 计划日期
   */
  @Column(name = "pre_plan_date", comment = "计划日期")
  private String pre_plan_date;
  
  /**
   * 实绩日期
   */
  @Column(name = "operate_date", comment = "实绩日期")
  private String operate_date;

  /**
   * 保养计划组id
   */
  @Column(name = "pre_plan_group", comment = "保养计划组id")
  private String pre_plan_group;

  /**
   * 保养计划状态
   */
  @Column(name = "pre_plan_state", comment = "保养计划状态")
  private String pre_plan_state;
  
  /**
   * 保养计划状态名称
   */
  @Transient
  @Column(name = "pre_plan_state_nm", comment = "保养计划状态名称")
  private String pre_plan_state_nm;

  /**
   * 已检查设备数
   */
  @Column(name = "chk_mach_num", comment = "已检查设备数")
  private String chk_mach_num;

  /**
   * 需检查设备总数
   */
  @Column(name = "chk_mach_all", comment = "需检查设备总数")
  private String chk_mach_all;

  /**
   * PM完成率
   */
  @Column(name = "pre_pm_rate", comment = "PM完成率")
  private String pre_pm_rate;

  /**
   * 首次创建
   */
  @Column(name = "create_id", comment = "首次创建")
  private String create_id;
  
  @Transient
  @Column(name = "pre_plan_date_end")
  private String pre_plan_date_end;
  
  @Transient
  @Column(name = "create_name")
  private String create_name;
  
  @Transient
  @Column(name = "update_name")
  private String update_name;
  
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
   * 排序类型
   */
  @Transient
  @Column(name = "orderType", comment = "排序类型")
  private String orderType;
  
  /**
   * 计划时间范围开始时间
   */
  @Transient
  @Column(name = "start_date", comment = "计划时间范围开始时间")
  private String start_date;
  
  /**
   * 设备类别
   */
  @Transient
  @Column(name = "machine_species_id", comment = "设备类型")
  private String machine_species_id;
  /**
   * 设备类name --sunaibo
   */
  @Transient
  @Column(name = "machine_species_name", comment = "设备类name")
  private String machine_species_name;
  /**
   * 设备类型
   */
  @Transient
  @Column(name = "machine_type_id", comment = "设备类别")
  private String machine_type_id;
  
  /**
   * 计划时间范围结束时间
   */
  @Transient
  @Column(name = "start_date", comment = "计划时间范围结束时间")
  private String end_date;
  
  /**
   * 计划列表查询类型
   */
  @Transient
  @Column(name = "plan_type", comment = "计划列表查询类型")
  private String plan_type;
  
  /**
   * 修改类型（单个修改，批量修改） 
   */
  @Transient
  @Column(name = "update_flag", comment = "修改类型")
  private String update_flag;

  public String getPre_plan_id() {
       return pre_plan_id;
  }

  public void setPre_plan_id(String pre_plan_id) {
       this.pre_plan_id = pre_plan_id;
  }

  public String getPre_plan_nm() {
       return pre_plan_nm;
  }

  public void setPre_plan_nm(String pre_plan_nm) {
       this.pre_plan_nm = pre_plan_nm;
  }

  public String getPre_plan_cycle() {
       return pre_plan_cycle;
  }

  public void setPre_plan_cycle(String pre_plan_cycle) {
       this.pre_plan_cycle = pre_plan_cycle;
  }

  public String getPre_plan_date() {
       return pre_plan_date;
  }

  public void setPre_plan_date(String pre_plan_date) {
       this.pre_plan_date = pre_plan_date;
  }

  public String getPre_plan_group() {
       return pre_plan_group;
  }

  public void setPre_plan_group(String pre_plan_group) {
       this.pre_plan_group = pre_plan_group;
  }

  public String getPre_plan_state() {
       return pre_plan_state;
  }

  public void setPre_plan_state(String pre_plan_state) {
       this.pre_plan_state = pre_plan_state;
  }

  public String getChk_mach_num() {
       return chk_mach_num;
  }

  public void setChk_mach_num(String chk_mach_num) {
       this.chk_mach_num = chk_mach_num;
  }

  public String getChk_mach_all() {
       return chk_mach_all;
  }

  public void setChk_mach_all(String chk_mach_all) {
       this.chk_mach_all = chk_mach_all;
  }

  public String getPre_pm_rate() {
       return pre_pm_rate;
  }

  public void setPre_pm_rate(String pre_pm_rate) {
       this.pre_pm_rate = pre_pm_rate;
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

public String getOrderType() {
	return orderType;
}

public void setOrderType(String orderType) {
	this.orderType = orderType;
}

public String getStart_date() {
	return start_date;
}

public void setStart_date(String start_date) {
	this.start_date = start_date;
}

public String getEnd_date() {
	return end_date;
}

public void setEnd_date(String end_date) {
	this.end_date = end_date;
}

public String getMachine_species_id() {
	return machine_species_id;
}

public void setMachine_species_id(String machine_species_id) {
	this.machine_species_id = machine_species_id;
}

public String getOperate_date() {
	return operate_date;
}

public void setOperate_date(String operate_date) {
	this.operate_date = operate_date;
}

public String getPre_plan_cycle_nm() {
	return pre_plan_cycle_nm;
}

public void setPre_plan_cycle_nm(String pre_plan_cycle_nm) {
	this.pre_plan_cycle_nm = pre_plan_cycle_nm;
}

public String getPre_plan_state_nm() {
	return pre_plan_state_nm;
}

public void setPre_plan_state_nm(String pre_plan_state_nm) {
	this.pre_plan_state_nm = pre_plan_state_nm;
}

public String getUpdate_flag() {
	return update_flag;
}

public void setUpdate_flag(String update_flag) {
	this.update_flag = update_flag;
}

public String getMachine_type_id() {
	return machine_type_id;
}

public void setMachine_type_id(String machine_type_id) {
	this.machine_type_id = machine_type_id;
}

public String getPlan_type() {
	return plan_type;
}

public void setPlan_type(String plan_type) {
	this.plan_type = plan_type;
}

public String getMachine_species_name() {
	return machine_species_name;
}

public void setMachine_species_name(String machine_species_name) {
	this.machine_species_name = machine_species_name;
}

public String getCreate_name() {
	return create_name;
}

public void setCreate_name(String create_name) {
	this.create_name = create_name;
}

public String getPre_plan_date_end() {
	return pre_plan_date_end;
}

public void setPre_plan_date_end(String pre_plan_date_end) {
	this.pre_plan_date_end = pre_plan_date_end;
}

public String getUpdate_name() {
	return update_name;
}

public void setUpdate_name(String update_name) {
	this.update_name = update_name;
}

}
