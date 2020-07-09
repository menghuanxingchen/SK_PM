package com.pm.business.component.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.component.dao.ComponentsSysCodeInfoDAO;
import com.pm.persistence.component.entity.ComponentsSysCodeInfo;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;
/**
 * 表sys_code_info的SERVICE类
 * @author 刘镝
 */
@Service
public class ComponentsSysCodeInfoService {
    private static Logger logger = Logger.getLogger("logger");

    @Resource
    private ComponentsSysCodeInfoDAO componentsSysCodeInfoDAO;

    /**
     * 分页查询信息
     * @author 刘镝
     */
    public Pagination<ComponentsSysCodeInfo> queryComponentsSysCodeInfoList(ComponentsSysCodeInfo ComponentsSysCodeInfo, int pageIndex, int pageSize) {
        return componentsSysCodeInfoDAO.queryComponentsSysCodeInfoListLike(ComponentsSysCodeInfo, pageIndex, pageSize);
    }

    /**
     * 查询详细信息
     * @author 刘镝
     */
    public ComponentsSysCodeInfo getComponentsSysCodeInfoById(String ComponentsSysCodeInfo_id) {
        return componentsSysCodeInfoDAO.getComponentsSysCodeInfoById(ComponentsSysCodeInfo_id);
    }

    /**
     * 新增信息
     * @author 刘镝
     * @throws Exception 
     */
    public int insertComponentsSysCodeInfo(ComponentsSysCodeInfo ComponentsSysCodeInfo) throws Exception{
    	
    	ComponentsSysCodeInfo obj = componentsSysCodeInfoDAO.queryComponentsSysCodeInfoEO(ComponentsSysCodeInfo);
    	//System.out.println(obj.getCode_value());
    	if(null!=obj){
    		Integer temp=Integer.parseInt(obj.getCode_value())+1;
    		System.err.println(temp);
    		String s = String.valueOf(temp);
    		ComponentsSysCodeInfo.setCode_value(s);
    		
    		}
    	
    	return componentsSysCodeInfoDAO.saveComponentsSysCodeInfo(ComponentsSysCodeInfo);
   
    }

    /**
     * 修改信息
     * @author 刘镝
     * @throws Exception 
     */
    public int updComponentsSysCodeInfo(ComponentsSysCodeInfo ComponentsSysCodeInfo) throws Exception{
        return componentsSysCodeInfoDAO.updateComponentsSysCodeInfo(ComponentsSysCodeInfo);
    }

    /**
     * 作废信息
     * @author 刘镝
     */
    public int cancelComponentsSysCodeInfo(ComponentsSysCodeInfo ComponentsSysCodeInfo) {
        return componentsSysCodeInfoDAO.deleteComponentsSysCodeInfo(ComponentsSysCodeInfo);
    }

    /**
     * 添加前/更新前check
     * @author 刘镝
     */
    public boolean dbExistCheck(ComponentsSysCodeInfo ComponentsSysCodeInfo){
    	
        return componentsSysCodeInfoDAO.checkComponentsSysCodeInfoEO(ComponentsSysCodeInfo);
    }
    /**
     * 分页查询3级部件
     * @param ComponentsSysCodeInfo
     * @param pageIndex
     * @param pageSize
     * @return
     */
    
    public Pagination<ComponentsSysCodeInfo> selComponentsSysCodeInfoList(ComponentsSysCodeInfo ComponentsSysCodeInfo, int pageIndex, int pageSize) {
        return componentsSysCodeInfoDAO.selComponentsSysCodeInfo(ComponentsSysCodeInfo, pageIndex, pageSize);
    }
    /**
     * 查询一条信息根据ID
     */
    
    public ComponentsSysCodeInfo selComponentsInfo(String id) {
    	return componentsSysCodeInfoDAO.getComponentsSysCodeInfoById(id);
    	
    }
    /**
     * 查询一条信息根据Code_Value
     * @param ComponentsSysCodeInfo
     * @return
     */
    public  List<ComponentsSysCodeInfo> selComponentsInfoValue(ComponentsSysCodeInfo ComponentsSysCodeInfo) {
    	
    	return componentsSysCodeInfoDAO.queryComponentsSysCodeInfoList1(ComponentsSysCodeInfo);
    }
    
}

