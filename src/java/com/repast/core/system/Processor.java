package com.repast.core.system;

import com.repast.core.annotation.Column;

public class Processor {
	
	private int pageSize = 10;

	private int pageIndex = 0;
	
	// 当前登录用户的id
	private String currentUserId;
	// 当前登录用户的姓名
	private String currentUserName;
	// 设备访问类型 
	private String deviceType = "PC";
	// ip地址
	private String ipaddress;
	
	//微信openId
	private String openId;
	//部门
	private String dept;
	//职位
	private String auth;
	//下属部门
	private String subdept;
	//sqm权限
	private String sqm_role_code;
	
	public String getSqm_role_code() {
		return sqm_role_code;
	}

	public void setSqm_role_code(String sqm_role_code) {
		this.sqm_role_code = sqm_role_code;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getCurrentUserName() {
		return currentUserName;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getSubdept() {
		return subdept;
	}

	public void setSubdept(String subdept) {
		this.subdept = subdept;
	}

}
