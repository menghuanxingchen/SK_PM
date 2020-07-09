package com.pm.business.sysinfo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.pm.persistence.sysinfo.dao.SysCodeGroupInfoDAO;
import com.pm.persistence.sysinfo.dao.SysCodeInfoDAO;
import com.pm.persistence.sysinfo.entity.SysCodeGroupInfo;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;
@Service
public class SysCodeInfoService {

	private static Logger logger = Logger.getLogger("logger");
	
	@Resource
	private SysCodeGroupInfoDAO sysCodeGroupInfoDAO;
	
	@Resource
	private SysCodeInfoDAO sysCodeInfoDAO;
	
	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
	  public Pagination<SysCodeInfo> querySysCodeInfoList(SysCodeInfo entity, int pageIndex, int pageSize) {
	    return sysCodeInfoDAO.querySysCodeInfoList(entity, pageIndex, pageSize);
	  }

	/**
	 * 保存实体对象entity
	 * @author 刘镝
	 */
	  public int saveSysCodeInfo(SysCodeGroupInfo sysCodeGroupInfo,SysCodeInfo entity) {
		  try {
				logger.info("事务操作方法：SysCodeInfoService--saveSysCodeInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：SysCodeInfoService--saveSysCodeInfo 异常！");
			} 
		  int cnt = 0;
		  if(!StringUtils.isNullOrEmpty(sysCodeGroupInfo.getCode_group_value()) ){
			  if(sysCodeGroupInfoDAO.getSysCodeGroupInfoByCodeGroupValue(sysCodeGroupInfo.getCode_group_value())==1){
				 return 0;
			  }
			  cnt = sysCodeGroupInfoDAO.saveSysCodeGroupInfo(sysCodeGroupInfo);
		  }else{
			  if(sysCodeInfoDAO.getSysCodeGroupInfoByCodeValue(entity)==1){
				  sysCodeInfoDAO.getSysCodeGroupInfoByCodeValue(entity);
					 return 0;
			  }
			  cnt = sysCodeInfoDAO.saveSysCodeInfo(entity);
		  }
		return cnt;
	  }

	/**
	 * 修改实体对象entity
	 * @author 刘镝
	 */
	  public int updateSysCodeInfo(SysCodeInfo entity) {
		  try {
				logger.info("事务操作方法：SysCodeInfoService--updateSysCodeInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：SysCodeInfoService--updateSysCodeInfo 异常！");
			}
		  return sysCodeInfoDAO.updateSysCodeInfo(entity);
	  }

	/**
	 * 删除实体对象entity
	 * @author 刘镝
	 */
	  public int deleteSysCodeInfo(SysCodeInfo entity) {
		  try {
				logger.info("事务操作方法：SysCodeInfoService--deleteSysCodeInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：SysCodeInfoService--deleteSysCodeInfo 异常！");
			}
		  return sysCodeInfoDAO.deleteSysCodeInfo(entity);
	  }

	/**
	 * 根据实体对象Id返回entity
	 * @author 刘镝
	 */
	  public SysCodeInfo getSysCodeInfoById(String id) {
		  return sysCodeInfoDAO.getSysCodeInfoById(id);
	  }
	  
	  public List<SysCodeGroupInfo> querySysCodeGroupInfoListNoPage(SysCodeGroupInfo entity) {
		    return sysCodeGroupInfoDAO.querySysCodeGroupInfoListNoPage(entity);
	  }
	  public List<SysCodeInfo> querySysCodeInfoListNoPage(SysCodeInfo entity) {
		    return sysCodeInfoDAO.querySysCodeInfoListNoPage(entity);
	  }
	  public List<SysCodeInfo> querySysCodeInfoListNoPageWithCode(SysCodeInfo entity) {
		    return sysCodeInfoDAO.querySysCodeInfoListNoPageWithCode(entity);
	  }
	  public List<SysCodeInfo> querySysCodeInfoListNoPageWithoutCode(SysCodeInfo entity) {
		    return sysCodeInfoDAO.querySysCodeInfoListNoPageWithoutCode(entity);
	  }
	  /**
	   * 根据group code查询code list
	   * @param entity
	   * @return
	   */
	  public List<SysCodeInfo> getCodeListByGroupCode(String groupCode) {
		  SysCodeInfo entity = new SysCodeInfo();
		  entity.setCode_group_value(groupCode);
		return sysCodeInfoDAO.querySysCodeInfoListNoPage(entity);
	  }
	  
	  /**
	   * 根据group code查询code list
	   * @param entity
	   * @return
	   */
	  public Pagination<SysCodeInfo> getCodeListByGroupCodePage(SysCodeInfo entity,String groupCode, int pageIndex, int pageSize) {
		  entity.setCode_group_value(groupCode);
		return sysCodeInfoDAO.querySysCodeInfoList(entity, pageIndex, pageSize);
	  }
	  
	  /**
	   * 二级下拉联动
	   * @author fish
	   */
	  public List<SysCodeInfo> getSubCodeList(String code_group_value,String code_value){
		  return sysCodeInfoDAO.getSubCodeList(code_group_value, code_value);
	  }
	  
	  /**
	   * 查询code value,code name
	   * @author fish
	   */
	  public SysCodeInfo getSubCodeName(SysCodeInfo entity){
		  return sysCodeInfoDAO.getSubCodeName(entity);
	  }
	  /**
	   * 查询*
	   * @author gao
	   */
	  public SysCodeInfo getSubCodeInfo(SysCodeInfo entity){
		  return sysCodeInfoDAO.getSubCodeInfo(entity);
	  }
	  /**
	   * 排序下拉列表组成
	   * @author fish
	   */
	  public List<SysCodeInfo> getOrderList(String[][] sb){
		  List<SysCodeInfo> orderList = new ArrayList<SysCodeInfo>();//排序
		  SysCodeInfo sysCodeInfo =null;
		  for(int i=0;i<sb.length;i++){
			  sysCodeInfo = new SysCodeInfo();
			  sysCodeInfo.setCode_value(sb[i][0]);
			  sysCodeInfo.setCode_nm(sb[i][1]);
			  orderList.add(sysCodeInfo);
		  }
		  return orderList;
	  }
	  /**
	   * 获取code_value最大值
	   */
	  public SysCodeInfo getMaxCodeValue(String code_group_value){
		  return sysCodeInfoDAO.getMaxCodeValue(code_group_value);
	  }
	  
}
