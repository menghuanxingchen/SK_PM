package com.sqm.sqmpushinfo.service;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.JiGuangPush.JPush;
import com.mysql.jdbc.StringUtils;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;
import com.repast.persistence.dao.SysUserInfoDAO;
import com.repast.persistence.entity.SysUserInfo;
import com.sqm.sqmpushinfo.dao.SqmPushInfoDAO;
import com.sqm.sqmpushinfo.entity.SqmPushInfo;
/**
 * 表sqm_push_info的SERVICE类
 * @author 刘镝
 */
@Service
public class SqmPushInfoService {
    private static Logger logger = Logger.getLogger("logger");

    @Resource
    private SqmPushInfoDAO sqmPushInfoDAO;
    
	@Resource
	private SysUserInfoDAO sysUserInfoDAO;

    /**
     * 分页查询信息
     * @author 刘镝
     * @throws Exception 
     */
    public Pagination<SqmPushInfo> querySqmPushInfoList(SqmPushInfo sqmPushInfo, int pageIndex, int pageSize) throws Exception {
        return sqmPushInfoDAO.queryDataList(sqmPushInfo, pageIndex, pageSize);
    }

    /**
     * 查询详细信息
     * @author 刘镝
     */
    public SqmPushInfo getSqmPushInfoById(String sqmPushInfo_id) {
        return sqmPushInfoDAO.getSqmPushInfoById(sqmPushInfo_id);
    }

    /**
     * 新增信息
     * @author 刘镝
     * @throws Exception 
     */
    public String insertSqmPushInfo(SqmPushInfo sqmPushInfo,FillingPlanInfo planInfo){
    	String resDay = null;
		try {
			resDay = DateUtil.getNow(DateUtil.Y_M_D_HMS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	sqmPushInfo.setPush_time(resDay);
    	SysUserInfo userInfo = sysUserInfoDAO.getSysUserInfoById(sqmPushInfo.getPush_to());
    	String push_id = sqmPushInfoDAO.saveSqmPushInfo(sqmPushInfo);
    	if(!StringUtils.isNullOrEmpty(push_id)) {
    		if("ALL".equals(sqmPushInfo.getPush_to())||sqmPushInfo.getPush_to().length()>8) {
    			List<SysUserInfo> iDs = sysUserInfoDAO.queryRegistrationIDs();
    			if(iDs.size()>0)JPush.jpushPeople(iDs.get(0).getRegistrationID(), sqmPushInfo, "iOS", push_id, planInfo);
    			//JPush.jpushAll(sqmPushInfo,push_id,planInfo);
    		}else {
    			JPush.jpushPeople(userInfo.getRegistrationID(), sqmPushInfo,"iOS","",planInfo);
    		}
    	}
        return push_id;
    }

    /**
     * 修改信息
     * @author 刘镝
     */
    public int updSqmPushInfo(SqmPushInfo sqmPushInfo){
        return sqmPushInfoDAO.updateSqmPushInfo(sqmPushInfo);
    }

    /**
     * 作废信息
     * @author 刘镝
     */
    public int cancelSqmPushInfo(SqmPushInfo sqmPushInfo) {
        return sqmPushInfoDAO.deleteSqmPushInfo(sqmPushInfo);
    }

    /**
     * 添加前/更新前check
     * @author 刘镝
     */
    public boolean dbExistCheck(SqmPushInfo sqmPushInfo){
        return sqmPushInfoDAO.checkSqmPushInfoEO(sqmPushInfo);
    }
}

