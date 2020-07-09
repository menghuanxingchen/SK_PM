package com.pm.business.basicinfo.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.sysinfo.dao.SysCodeGroupInfoDAO;
import com.pm.persistence.sysinfo.dao.SysCodeInfoDAO;
import com.pm.persistence.sysinfo.entity.SysCodeGroupInfo;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;
@Service("MachineTypeService")
public class MachineTypeService {

	@Resource
	private SysCodeGroupInfoDAO sysCodeGroupInfoDAO;
	
	@Resource
	private SysCodeInfoDAO sysCodeInfoDAO;
	private static Logger logger = Logger.getLogger("logger");
	/**
	 * 分页查询entity List
	 * @author 刘镝
	 */
	  public Pagination<SysCodeInfo> querySysCodeInfoPageList(SysCodeInfo entity, int pageIndex, int pageSize) {
	    return sysCodeInfoDAO.querySysCodeInfoPageList(entity, pageIndex, pageSize);
	  }

	/**
	 * 保存实体对象entity
	 * @author 刘镝
	 */
	  public int saveSysCodeInfo(SysCodeInfo entity) {		 
		  //--code_value 递增
		  SysCodeInfo temp = new SysCodeInfo();
		  temp.setCode_group_value(entity.getCode_group_value());
		  int num=sysCodeInfoDAO.querySysCodeInfoListNoPage(temp).size()+1;
		  entity.setCode_value(num+"");
		  entity.setCode_id(sysCodeInfoDAO.getUUId());
		  try {
				logger.info("事务操作方法：MachineTypeService--saveSysCodeInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：MachineTypeService--saveSysCodeInfo 异常！");
			}
		  int cnt = sysCodeInfoDAO.saveSysCodeInfo(entity);		 
		  return cnt;
	  }

	/**
	 * 修改实体对象entity
	 * @author 刘镝
	 */
	  public int updateSysCodeInfo(SysCodeInfo entity) {
		  try {
				logger.info("事务操作方法：MachineTypeService--updateSysCodeInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：MachineTypeService--updateSysCodeInfo 异常！");
			}
		  return sysCodeInfoDAO.updateSysCodeInfo(entity);
	  }

	/**
	 * 删除实体对象entity
	 * @author 刘镝
	 */
	  public int deleteSysCodeInfo(SysCodeInfo entity) {
		  try {
				logger.info("事务操作方法：MachineTypeService--deleteSysCodeInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：MachineTypeService--deleteSysCodeInfo 异常！");
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
	   * 二级下拉联动
	   * @author fish
	   */
	  public List<SysCodeInfo> getSubCodeList(String code_group_value,String code_value){
		  return sysCodeInfoDAO.getSubCodeList(code_group_value, code_value);
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
	  
}
