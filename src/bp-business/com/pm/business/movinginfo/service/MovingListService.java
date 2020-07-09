package com.pm.business.movinginfo.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.Sqmmanage.dao.SqmmanageDAO;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.SvaluationManage.dao.SvaluationManageDAO;
import com.pm.persistence.SvaluationManage.entity.EvaluationManageInfo;
import com.pm.persistence.movinginfo.dao.MovingInInfoDAO;
import com.pm.persistence.movinginfo.dao.MovingOutInfoDAO;
import com.pm.persistence.movinginfo.dto.MovingInfo;
import com.pm.persistence.movinginfo.entity.MovingGoodsInfo;
import com.pm.persistence.movinginfo.entity.MovingInInfo;
import com.pm.persistence.movinginfo.entity.MovingOutInfo;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;
import com.repast.core.util.MD5Util;
import com.repast.persistence.dao.SysUserInfoDAO;
import com.repast.persistence.entity.SysUserInfo;
@Service
public class MovingListService {
	private static Logger logger = Logger.getLogger("logger");
	
	@Resource
	private MovingInInfoDAO movingInInfoDAO;
	
	@Resource
	private MovingOutInfoDAO movingOutInfoDAO;

	
	/**
	 * 分页查询用户信息
	 * @author fish
	 */
  public Pagination<MovingInfo> queryDataList(MovingInfo entity, int pageIndex, int pageSize) {
    return movingInInfoDAO.queryDataList(entity, pageIndex, pageSize);
  }
  /**
	 * 搬入详细
	 * @author fish
	 */
public MovingInInfo queryMovingInDataList(MovingInfo entity) {
  return movingInInfoDAO.queryMovingInDataList(entity);
}
/**
	 * 搬出详细
	 * @author fish
	 */
public MovingOutInfo queryMovingOutDataList(MovingInfo entity) {
return movingOutInfoDAO.queryMovingOutDataList(entity);
}
/**
 * 物品清单
 * @author fish
 */
public List<MovingGoodsInfo> queryMovingGoodsInfoList(MovingInfo entity) {
return movingInInfoDAO.queryMovingGoodsInfoList(entity);
}
/**
 * 删除
 * @author fish
 */
public int deleteMovingInfoData(MovingInfo entity) {
return movingInInfoDAO.deleteMovingInfoData(entity);
}
}
