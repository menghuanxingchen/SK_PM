package com.pm.persistence.inspectplan.entity;

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
 * 表ins_plan_info 的实体类
 * @author 刘镝
 */
@Entity(name = "ins_plan_info")
public class InsPlanInfo extends BaseEntity implements Serializable, Cloneable {

  private static final long serialVersionUID = 5795186162289562464L;

  public InsPlanInfo clone() {
      InsPlanInfo o = null;
      try {
          o = (InsPlanInfo) super.clone();
      } catch (CloneNotSupportedException e) {
          e.printStackTrace();
      }
      return o;
  }

  /**
   * 巡检计划id
   */
  @ID
  @Column(name = "ins_plan_id", comment = "巡检计划id")
  private String ins_plan_id;

  /**
   * 巡检计划名称
   */
  @Column(name = "ins_plan_nm", comment = "巡检计划名称")
  private String ins_plan_nm;

  /**
   * 巡检计划周期
   */
  @Column(name = "ins_plan_cycle", comment = "巡检计划周期")
  private String ins_plan_cycle;
  
  /**
   * 结束日期
   */
  @Column(name = "ins_plan_date_end", comment = "结束日期")
  private String ins_plan_date_end;
  
  /**
   * 巡检计划周期name
   */
  @Transient
  @Column(name = "ins_plan_cycle_name", comment = "巡检计划周期name")
  private String ins_plan_cycle_name;

  /**
   * 巡检计划日期
   */
  @Column(name = "ins_plan_date", comment = "巡检计划日期")
  private String ins_plan_date;
  /**
   * 巡检计划日期对应星期
   */
  @Transient
  @Column(name = "weekday")
  private String weekday;
  

  /**
   * 巡检计划组id
   */
  @Column(name = "ins_plan_group", comment = "巡检计划组id")
  private String ins_plan_group;

  /**
   * 巡检计划状态
   */
  @Column(name = "ins_plan_state", comment = "巡检计划状态")
  private String ins_plan_state;
  /**
   * 巡检计划状态name
   */
  @Transient
  @Column(name = "ins_plan_state_name", comment = "巡检计划状态名")
  private String ins_plan_state_name;

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
  @Column(name = "ins_pm_rate", comment = "PM完成率")
  private String ins_pm_rate;

  /**
   * 确认flag
   */
  @Column(name = "confirm_yn", comment = "确认flag")
  private String confirm_yn;

  /**
   * 首次创建
   */
  @Column(name = "create_id", comment = "首次创建")
  private String create_id;
  @Transient
  @Column(name = "create_name")
  private String create_name;

  /**
   * 操作人姓名
   */
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
   * 实际时间
   */
  @Column(name = "operate_time", comment = "实际时间")
  private String operate_time;
  
  /**
   * 修改区分  0：批量 1：单个
   */
  @Transient
  String update_flag;
  /**
   * 计划设备List
   */
  @Transient
  List<InsPlanMachineInfo> insMachineList;
  /**
   * 检查项List
   */
  @Transient
  List<InsPlanCheckDetail> insCheckDetailList;
  /**
   * 设备类别
   */
  @Transient
  @Column(name = "machine_species_id")
  String machine_species_id;
  /**
   * 设备类别name
   */
  @Transient
  @Column(name = "machine_species_name")
  String machine_species_name;
  /**
   * 开始
   */
  @Transient
  @Column(name = "date_start")
  String date_start;
  /**
   * 结束
   */
  @Transient
  @Column(name = "date_end")
  String date_end;
  /**
   * 排序类型
   */
  @Transient  
  private String orderType;
  
  public String getIns_plan_id() {
       return ins_plan_id;
  }

  public void setIns_plan_id(String ins_plan_id) {
       this.ins_plan_id = ins_plan_id;
  }

  public String getIns_plan_nm() {
       return ins_plan_nm;
  }

  public void setIns_plan_nm(String ins_plan_nm) {
       this.ins_plan_nm = ins_plan_nm;
  }

  public String getIns_plan_cycle() {
       return ins_plan_cycle;
  }

  public void setIns_plan_cycle(String ins_plan_cycle) {
       this.ins_plan_cycle = ins_plan_cycle;
  }

  public String getIns_plan_date() {
       return ins_plan_date;
  }

  public void setIns_plan_date(String ins_plan_date) {
       this.ins_plan_date = ins_plan_date;
  }

  public String getIns_plan_group() {
       return ins_plan_group;
  }

  public void setIns_plan_group(String ins_plan_group) {
       this.ins_plan_group = ins_plan_group;
  }

  public String getIns_plan_state() {
       return ins_plan_state;
  }

  public void setIns_plan_state(String ins_plan_state) {
       this.ins_plan_state = ins_plan_state;
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

  public String getIns_pm_rate() {
       return ins_pm_rate;
  }

  public void setIns_pm_rate(String ins_pm_rate) {
       this.ins_pm_rate = ins_pm_rate;
  }

  public String getConfirm_yn() {
       return confirm_yn;
  }

  public void setConfirm_yn(String confirm_yn) {
       this.confirm_yn = confirm_yn;
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

public List<InsPlanMachineInfo> getInsMachineList() {
	return insMachineList;
}

public void setInsMachineList(List<InsPlanMachineInfo> insMachineList) {
	this.insMachineList = insMachineList;
}

public List<InsPlanCheckDetail> getInsCheckDetailList() {
	return insCheckDetailList;
}

public void setInsCheckDetailList(List<InsPlanCheckDetail> insCheckDetailList) {
	this.insCheckDetailList = insCheckDetailList;
}

public String getUpdate_flag() {
	return update_flag;
}

public void setUpdate_flag(String update_flag) {
	this.update_flag = update_flag;
}

public String getMachine_species_id() {
	return machine_species_id;
}

public void setMachine_species_id(String machine_species_id) {
	this.machine_species_id = machine_species_id;
}

public String getDate_start() {
	return date_start;
}

public void setDate_start(String date_start) {
	this.date_start = date_start;
}

public String getDate_end() {
	return date_end;
}

public void setDate_end(String date_end) {
	this.date_end = date_end;
}

public String getOrderType() {
	return orderType;
}

public void setOrderType(String orderType) {
	this.orderType = orderType;
}

public String getIns_plan_state_name() {
	return ins_plan_state_name;
}

public void setIns_plan_state_name(String ins_plan_state_name) {
	this.ins_plan_state_name = ins_plan_state_name;
}

public String getOperate_time() {
	return operate_time;
}

public void setOperate_time(String operate_time) {
	this.operate_time = operate_time;
}

public String getIns_plan_cycle_name() {
	return ins_plan_cycle_name;
}

public void setIns_plan_cycle_name(String ins_plan_cycle_name) {
	this.ins_plan_cycle_name = ins_plan_cycle_name;
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

public String getUpdate_name() {
	return update_name;
}

public void setUpdate_name(String update_name) {
	this.update_name = update_name;
}

public String getWeekday() {
	return weekday;
}

public void setWeekday(String weekday) {
	this.weekday = weekday;
}
public String getIns_plan_date_end() {
	return ins_plan_date_end;
}

public void setIns_plan_date_end(String ins_plan_date_end) {
	this.ins_plan_date_end = ins_plan_date_end;
}


}
