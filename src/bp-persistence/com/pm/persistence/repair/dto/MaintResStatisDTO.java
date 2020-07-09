package com.pm.persistence.repair.dto;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表repair_machine_detail 的实体类
 * @author 刘镝
 */
public class MaintResStatisDTO{


  /**
   * 维修设备结果id
   */
  private String repair_machine_id;

  /**
   * 维修结果ID
   */
  private String repair_result_id;

  /**
   * 设备id
   */
  private String machine_id;

  
  private String machine_nm;
  /**
   * 故障类型id
   */
  private String maintenance_id;
  /**
   * 故障类型name
   */
  private String maintenance_nm;
  /**
   * order中的repair_date
   */
  private String repair_date;
  
  private String st_date;
  private String en_date;
  private String selPotId;
  private String pot_id;
  private String pot_nm;
  private String start_time;
  private String emergent_id;
  private String hourNum;
  /**
   * 统计故障类型对应设备个数
   */
  private String amt;
  
  /**
   * 统计设备对应维修单个数
   */
  private String re_amt;
  /**
   * repair_order_info 的id
   */
  private String id;
  /**
   * 申请编号
   */
  private String repair_order_num;
  /**
   * 申请日期
   */
  private String create_time;
  
  /**
   * 维修内容
   */
  private String repair_detail;

  /**
   * 申请人
   */
  private String create_id;
  private String create_name;
  /**
   * 申请人部门
   */
  private String dept_code;
  private String dept_code_name;
  
  /**
   * 维修担当
   */
  private String repair_user_id;
  private String repair_user_name;

  /**
   * 维修金额	
   */
  private String totalam;
  /**
   * 维修工时
   */
  private String task_time;
  
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


public String getMachine_nm() {
	return machine_nm;
}

public void setMachine_nm(String machine_nm) {
	this.machine_nm = machine_nm;
}

public String getMaintenance_id() {
	return maintenance_id;
}

public void setMaintenance_id(String maintenance_id) {
	this.maintenance_id = maintenance_id;
}

public String getMaintenance_nm() {
	return maintenance_nm;
}

public void setMaintenance_nm(String maintenance_nm) {
	this.maintenance_nm = maintenance_nm;
}

public String getRepair_date() {
	return repair_date;
}

public void setRepair_date(String repair_date) {
	this.repair_date = repair_date;
}

public String getAmt() {
	return amt;
}

public void setAmt(String amt) {
	this.amt = amt;
}

public String getRe_amt() {
	return re_amt;
}

public void setRe_amt(String re_amt) {
	this.re_amt = re_amt;
}

public String getSt_date() {
	return st_date;
}

public void setSt_date(String st_date) {
	this.st_date = st_date;
}

public String getEn_date() {
	return en_date;
}

public void setEn_date(String en_date) {
	this.en_date = en_date;
}

public String getSelPotId() {
	return selPotId;
}

public void setSelPotId(String selPotId) {
	this.selPotId = selPotId;
}


public String getRepair_order_num() {
	return repair_order_num;
}

public void setRepair_order_num(String repair_order_num) {
	this.repair_order_num = repair_order_num;
}

public String getCreate_time() {
	return create_time;
}

public void setCreate_time(String create_time) {
	this.create_time = create_time;
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

public String getRepair_user_id() {
	return repair_user_id;
}

public void setRepair_user_id(String repair_user_id) {
	this.repair_user_id = repair_user_id;
}

public String getCreate_name() {
	return create_name;
}

public void setCreate_name(String create_name) {
	this.create_name = create_name;
}

public String getDept_code() {
	return dept_code;
}

public void setDept_code(String dept_code) {
	this.dept_code = dept_code;
}

public String getDept_code_name() {
	return dept_code_name;
}

public void setDept_code_name(String dept_code_name) {
	this.dept_code_name = dept_code_name;
}

public String getRepair_user_name() {
	return repair_user_name;
}

public void setRepair_user_name(String repair_user_name) {
	this.repair_user_name = repair_user_name;
}

public String getTotalam() {
	return totalam;
}

public void setTotalam(String totalam) {
	this.totalam = totalam;
}

public String getTask_time() {
	return task_time;
}

public void setTask_time(String task_time) {
	this.task_time = task_time;
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getPot_nm() {
	return pot_nm;
}

public void setPot_nm(String pot_nm) {
	this.pot_nm = pot_nm;
}

public String getPot_id() {
	return pot_id;
}

public void setPot_id(String pot_id) {
	this.pot_id = pot_id;
}

public String getStart_time() {
	return start_time;
}

public void setStart_time(String start_time) {
	this.start_time = start_time;
}

public String getEmergent_id() {
	return emergent_id;
}

public void setEmergent_id(String emergent_id) {
	this.emergent_id = emergent_id;
}

public String getHourNum() {
	return hourNum;
}

public void setHourNum(String hourNum) {
	this.hourNum = hourNum;
}
}
