package com.pm.business.scrap.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.scrap.dao.ScrapInfoDAO;
import com.pm.persistence.scrap.entity.ScrapInfo;
import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;

@Service
public class ScrapService {
	
	private static Logger logger = Logger.getLogger("logger");
	
	@Resource
	private ScrapInfoDAO scrapInfoDAO;
	
	/**
	 * 分页查询entity List
	 */
	  public Pagination<ScrapInfo> queryScrapInfoList(ScrapInfo entity, int pageIndex, int pageSize) {
	    return scrapInfoDAO.queryScrapInfoList(entity, pageIndex, pageSize);
	  }

	/**
	 * 保存实体对象entity
	 */
	  public int saveScrapInfo(ScrapInfo entity) {
	  try {
			logger.info("事务操作方法：ScrapService--saveScrapInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ScrapService--saveScrapInfo 异常！");
		}
	    return scrapInfoDAO.saveScrapInfo(entity);
	  }

	/**
	 * 修改实体对象entity
	 */
	  public int updateScrapInfo(ScrapInfo entity) {
	  try {
			logger.info("事务操作方法：ScrapService--updateScrapInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ScrapService--updateScrapInfo 异常！");
		}
	    return scrapInfoDAO.updateScrapInfo(entity);
	  }

	/**
	 * 删除实体对象entity
	 */
	  public int deleteScrapInfo(ScrapInfo entity) {
	  try {
			logger.info("事务操作方法：ScrapService--deleteScrapInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ScrapService--deleteScrapInfo 异常！");
		}  
	    return scrapInfoDAO.deleteScrapInfo(entity);
	  }

	/**
	 * 根据实体对象Id返回entity
	 */
	  public ScrapInfo getScrapInfoByScrapId(String id) {
	    return scrapInfoDAO.getScrapInfoByScrapId(id);
	  }

}
