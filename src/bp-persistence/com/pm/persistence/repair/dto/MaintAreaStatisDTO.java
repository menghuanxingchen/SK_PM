package com.pm.persistence.repair.dto;

/**
 * 
 * Application name: Application describing:实体类 Copyright:Copyright copy;
 * Company:
 * 表repair_machine_detail 的实体类
 * @author 刘镝
 */
public class MaintAreaStatisDTO{


  /**
   * 维修单创建时间
   */
  private String create_time;
  
  private String st_date;
  private String en_date;

  /**
   * 维修区域code
   */
  private String repair_place;

  /**
   * 维修区域name
   */
  private String placename;

  /**
   * 维修人员
   */
  private String repair_user_id;
  /**
   * 维修人员部门01 电仪
   */
  private String dept01;
  /**
   * 维修人员部门01 机械
   */
  private String dept02;
  /**
   * 维修人员部门01 安全
   */
  private String dept03;
  /**
   * 维修人员部门01 其他
   */
  private String dept04;
  
  
  /**
   * 金额
   */
  private String total_all;
  
public String getCreate_time() {
	return create_time;
}
public void setCreate_time(String create_time) {
	this.create_time = create_time;
}
public String getRepair_place() {
	return repair_place;
}
public void setRepair_place(String repair_place) {
	this.repair_place = repair_place;
}
public String getPlacename() {
	return placename;
}
public void setPlacename(String placename) {
	this.placename = placename;
}
public String getRepair_user_id() {
	return repair_user_id;
}
public void setRepair_user_id(String repair_user_id) {
	this.repair_user_id = repair_user_id;
}
public String getDept01() {
	return dept01;
}
public void setDept01(String dept01) {
	this.dept01 = dept01;
}
public String getDept02() {
	return dept02;
}
public void setDept02(String dept02) {
	this.dept02 = dept02;
}
public String getDept03() {
	return dept03;
}
public void setDept03(String dept03) {
	this.dept03 = dept03;
}
public String getDept04() {
	return dept04;
}
public void setDept04(String dept04) {
	this.dept04 = dept04;
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
public String getTotal_all() {
	return total_all;
}
public void setTotal_all(String total_all) {
	this.total_all = total_all;
}
 
}
