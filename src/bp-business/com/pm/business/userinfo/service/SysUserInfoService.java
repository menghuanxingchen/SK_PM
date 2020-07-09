package com.pm.business.userinfo.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;
import com.repast.core.util.MD5Util;
import com.repast.persistence.dao.SysUserInfoDAO;
import com.repast.persistence.entity.SysUserInfo;
@Service
public class SysUserInfoService {
	private static Logger logger = Logger.getLogger("logger");
	
	@Resource
	private SysUserInfoDAO sysUserInfoDAO;
	
	public List<SysUserInfo> querySysUserInfoList(SysUserInfo entity) {
	    return sysUserInfoDAO.querySysUserInfoList(entity);
  }
	public List<SysUserInfo> querySysUserInfoListForDealUser(SysUserInfo entity) {
	    return sysUserInfoDAO.querySysUserInfoListForDealUser(entity);
  }
	
	public List<SysUserInfo> querySysUserInfoListByReport(SysUserInfo entity) {
	    return sysUserInfoDAO.querySysUserInfoListByReport(entity);
  }
	public List<SysUserInfo> querySysUserInfoListByAll(SysUserInfo entity) {
	    return sysUserInfoDAO.querySysUserInfoListByAll(entity);
  }
	public List<SysUserInfo> querySysUserInfoListFormoving() {
	    return sysUserInfoDAO.querySysUserInfoListFormoving();
  }
	/**
	 * 查询sqm 评价人
	 * @author gao
	 */
	public List<SysUserInfo> getEvaluationList() {
	    return sysUserInfoDAO.getEvaluationList();
  }
	/**
	 * 分页查询用户信息
	 * @author fish
	 */
  public Pagination<SysUserInfo> querySysUserInfoList(SysUserInfo entity, int pageIndex, int pageSize) {
    return sysUserInfoDAO.querySysUserInfoList(entity, pageIndex, pageSize);
  }
  
  /**
   * 新增用户信息
   * @author fish
   */
    public int saveSysUserInfo(SysUserInfo entity) {
    	try {
			logger.info("事务操作方法：SysUserInfoService--saveSysUserInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SysUserInfoService--saveSysUserInfo 异常！");
		}
    	entity.setPwd(MD5Util.MD5(entity.getPwd()));
      return sysUserInfoDAO.saveSysUserInfo(entity);
    }
    
    /**
     * 修改用户信息
     * @author fish
     */
	  public int updateSysUserInfo(SysUserInfo entity) {
		  try {
				logger.info("事务操作方法：SysUserInfoService--updateSysUserInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：SysUserInfoService--updateSysUserInfo 异常！");
			}
		  entity.setPwd(MD5Util.MD5(entity.getPwd()));
	    return sysUserInfoDAO.updateSysUserInfo(entity);
	  }
      
      /**
       * 删除用户信息
       * @author 刘镝
       */
    public int deleteSysUserInfo(SysUserInfo entity) {
    	try {
			logger.info("事务操作方法：SysUserInfoService--deleteSysUserInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SysUserInfoService--deleteSysUserInfo 异常！");
		}
      return sysUserInfoDAO.deleteSysUserInfo(entity);
    }
    
    public SysUserInfo getSysUserInfoByIdOld(String id) {
    	return sysUserInfoDAO.getSysUserInfoByIdOld(id);
    }
    
    /**
     * id 别用户信息查询
     * @author 刘镝
     */
    public SysUserInfo getSysUserInfoById(String id) {
    	return sysUserInfoDAO.getSysUserInfoById(id);
    }
}
