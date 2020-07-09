package com.repast.core.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.repast.core.util.MD5Util;
import com.repast.persistence.dao.SysUserInfoDAO;
import com.repast.persistence.entity.SysUserInfo;

@Service
public class LoginManagerService {
	
	@Resource
	private SysUserInfoDAO sysUserInfoDAO;
	
	
	
	public SysUserInfo getLoginInfo(SysUserInfo sysUserInfo){
		String pwd = MD5Util.MD5(sysUserInfo.getPwd());
		return sysUserInfoDAO.getLoginInfo(sysUserInfo.getUser_num(), pwd);
	}
	
	/**
	 * 根据实体对象Id返回entity
	 * @author 刘镝
	 */
	  public SysUserInfo getSysUserInfoById(String id) {
	    return sysUserInfoDAO.getSysUserInfoById(id);
	  }
	
/*	public List<CkSysMenuInfo> queryMenuList(CKLoginUserInfo ckLoginUserInfo){
		List<CkSysMenuInfo> menuList = ckLoginUserInfoDAO.queryMenuList(ckLoginUserInfo);
		return menuList;
	}*/

}
