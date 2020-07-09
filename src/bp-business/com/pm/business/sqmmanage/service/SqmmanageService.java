package com.pm.business.sqmmanage.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.Sqmmanage.dao.FillingPlanDAO;
import com.pm.persistence.Sqmmanage.dao.SqmmanageDAO;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;
import com.repast.core.util.MD5Util;
import com.repast.persistence.dao.SysUserInfoDAO;
import com.repast.persistence.entity.SysUserInfo;
@Service
public class SqmmanageService {
	private static Logger logger = Logger.getLogger("logger");
	
	@Resource
	private SqmmanageDAO sqmmanageDAO;
	
	@Resource
	private FillingPlanDAO fillingPlanDAO;

	
	public List<LineProductInfo> queryLineProductInfoList(LineProductInfo entity) {
	    return sqmmanageDAO.queryLineProductInfoList(entity);
  }


	/**
	 * 分页查询用户信息
	 * @author fish
	 */
  public Pagination<LineProductInfo> queryLineProductInfoList(LineProductInfo entity, int pageIndex, int pageSize) {
    return sqmmanageDAO.queryLineProductInfoList(entity, pageIndex, pageSize);
  }
  
  /**
	 * 分页查询用户信息
	 * @author fish
	 */
public Pagination<FillingPlanInfo> queryFillingPlanInfoList(FillingPlanInfo entity, int pageIndex, int pageSize) {
  return fillingPlanDAO.interfaceFillingPlanDataList(entity, pageIndex, pageSize);
}


  /**
   * 新增用户信息
   * @author fish
   */
    public int saveLineProductInfo(LineProductInfo entity) {
    	try {
			logger.info("事务操作方法：SysUserInfoService--saveSysUserInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SysUserInfoService--saveSysUserInfo 异常！");
		}
    	
      return sqmmanageDAO.saveLineProductInfo(entity);
    }
    
    /**
     * 修改用户信息
     * @author fish
     */
	  public int updateLineProductInfo(LineProductInfo entity) {
		  try {
				logger.info("事务操作方法：SysUserInfoService--updateSysUserInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：SysUserInfoService--updateSysUserInfo 异常！");
			}
		  
	    return sqmmanageDAO.updateLineProductInfo(entity);
	  }
      
      /**
       * 删除用户信息
       * @author 刘镝
       */
    public int deleteLineProductInfo(LineProductInfo entity) {
    	try {
			logger.info("事务操作方法：SysUserInfoService--deleteSysUserInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SysUserInfoService--deleteSysUserInfo 异常！");
		}
      return sqmmanageDAO.deleteLineProductInfo(entity);
    }
    
    /**
     * 取消检测
     * @author 刘镝
     */
  public int quxiaoDataFun(FillingPlanInfo fillingPlanInfo) {
  	try {
			logger.info("事务操作方法：sqmmanageService--quxiaoDataFun");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：sqmmanageService--quxiaoDataFun 异常！");
		}
    return sqmmanageDAO.quxiaoDataFun(fillingPlanInfo);
  }
  
  /**
   * 删除
   * @author 刘镝
   */
public int deleteFillingPlanInfo(FillingPlanInfo fillingPlanInfo) {
	try {
			logger.info("事务操作方法：sqmmanageService--deleteFillingPlanInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：sqmmanageService--deleteFillingPlanInfo 异常！");
		}
  return sqmmanageDAO.deleteFillingPlanInfo(fillingPlanInfo);
}

/**
 * 删除
 * @author 刘镝
 */
public int deleteTestResult(FillingPlanInfo fillingPlanInfo) {
	try {
			logger.info("事务操作方法：sqmmanageService--deleteTestResult");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：sqmmanageService--deleteTestResult 异常！");
		}
return sqmmanageDAO.deleteTestResult(fillingPlanInfo);
}
    
    public LineProductInfo getLineProductInfoById(String id) {
    	return sqmmanageDAO.getLineProductInfoById(id);
    }
    
    public Boolean checkLineProductInfo(String id) {
    	return sqmmanageDAO.checkLineProductInfo(id);
    }
   
}
