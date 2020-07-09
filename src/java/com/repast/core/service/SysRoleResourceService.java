package com.repast.core.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.repast.persistence.dao.SysRoleDAO;

@Service("sysRoleResourceService")
public class SysRoleResourceService {/*

	
	@Resource
	private SysRoleDAO sysRoleDAO;
	
	@Resource
	private SysRoleInfoDAO sysRoleInfoDAO;
	
	@Resource
	private SysRoleMenuDAO sysRoleMenuDAO;
	
	@Resource
	private SysRoleUserDAO sysRoleUserDAO;
	
	*//**
	 * 
	 * @author lingy
	 * @date 2015年9月9日 下午2:07:48
	 * @param userid
	 * @param menutype
	 * @return
	 *//*
	public List<SysMenuInfo> queryMenuListByRoleIdAntType(String userid,String menutype,String parentMenucode){
		List<SysMenuInfo> menuList = sysRoleMenuDAO.queryMenuListByRoleIdAntType(userid,menutype,parentMenucode);
		for (SysMenuInfo sysMenuInfo : menuList) {
			String menuCode = sysMenuInfo.getMenucode();
			List<SysMenuInfo> childrenMenu = sysRoleMenuDAO.queryMenuListByRoleIdAntType(userid,menutype,menuCode);
			sysMenuInfo.setChildrenMenu(childrenMenu);
		}
		return menuList;
	}
	
	
*/}
