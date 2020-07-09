package com.pm.business.seccheckinfo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.seccheckinfo.dao.SecCheckInfoDAO;
import com.pm.persistence.seccheckinfo.dto.SecCheckInfoDTO;
import com.pm.persistence.seccheckinfo.entity.SecCheckInfo;
import com.repast.core.system.Pagination;
import com.repast.core.util.DateUtil;

@Service
public class SecCheckInfoService {
	
	private static Logger logger = Logger.getLogger("logger");
	
	@Resource
	private SecCheckInfoDAO secCheckInfoDAO;// 安全检查
	/* * 保存安全检查单实体对象entity
	 * 
	 * xuning
	 */
	public int saveSecCheckInfo(SecCheckInfo entity) {
	 try {
			logger.info("事务操作方法：SecCheckInfoService--saveSecCheckInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SecCheckInfoService--saveSecCheckInfo 异常！");
		} 
		entity.setSec_check_id(secCheckInfoDAO.getUUId());
		return secCheckInfoDAO.saveSecCheckInfo(entity);
	}
	/* * 保存安全检查单实体对象entity+1年
	 * 
	 * xuning
	 */
	public int saveSecCheckInfo1(SecCheckInfo entity) {
		try {
			logger.info("事务操作方法：SecCheckInfoService--saveSecCheckInfo1");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SecCheckInfoService--saveSecCheckInfo1 异常！");
		} 
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		String dstr=entity.getUp_check_date();  
		String dstr1=entity.getFailure_date();
		String dstr2=entity.getSec_check_date();
		String dstr3=entity.getSec_plan_date();
	    java.util.Date up_check_date = null;
	    java.util.Date failure_date = null;
	    java.util.Date sec_plan_date = null;
	    java.util.Date sec_check_date = null;
		try {
			if(!"".equals(dstr)&&dstr!=null){
				up_check_date = sdf.parse(dstr);
			}
			if(!"".equals(dstr1)&&dstr1!=null){
				failure_date = sdf.parse(dstr1);
			}
			if(!"".equals(dstr2)&&dstr2!=null){
				sec_plan_date = sdf.parse(dstr2);
			}
			if(!"".equals(dstr3)&&dstr3!=null){
				sec_check_date = sdf.parse(dstr3);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		Calendar calendar = Calendar.getInstance();
		int y=0;
		for(int i = 1; i< 10; i++){
			entity.setSec_check_id(secCheckInfoDAO.getUUId());
			if(up_check_date!=null){
				calendar.setTime(up_check_date);
			    calendar.add(Calendar.YEAR, +i);
		        String up_check_date2=sdf.format(calendar.getTime()); 
		        entity.setUp_check_date(up_check_date2);
			}
			if(failure_date!=null){
				calendar.setTime(failure_date);
			    calendar.add(Calendar.YEAR, +i);
		        String failure_date2=sdf.format(calendar.getTime());  
		        entity.setFailure_date(failure_date2);
			}
			if(sec_plan_date!=null){
				calendar.setTime(sec_plan_date);
			    calendar.add(Calendar.YEAR, +i);
		        String sec_plan_date2=sdf.format(calendar.getTime()); 
		        entity.setSec_plan_date(sec_plan_date2);
			}
			if(sec_check_date!=null){
				calendar.setTime(sec_check_date);
			    calendar.add(Calendar.YEAR, +i);
		        String sec_check_date2=sdf.format(calendar.getTime()); 
		        entity.setSec_check_date(sec_check_date2);
			}
	        y= secCheckInfoDAO.saveSecCheckInfo(entity);
		}
		return y;
	}
	/**
	 * 分页查询安全检查entity List
	 * 
	 * @author xuning
	 */
	public Pagination<SecCheckInfoDTO> querySecCheckInfoList2(
			SecCheckInfoDTO entity, int pageIndex, int pageSize){
			 if(!"".equals(entity.getStart_dt()) && entity.getStart_dt() != null){
				 entity.setStart_dt(entity.getStart_dt()+" 00:00:00 ");
             }
			 if(!"".equals(entity.getEnd_dt()) && entity.getEnd_dt() != null){
				 entity.setEnd_dt(entity.getEnd_dt()+" 23:59:59 ");
             }
		return secCheckInfoDAO.querySecCheckInfoList2(entity, pageIndex,
				pageSize);
	}
	/**
	 * 删除安全检查单实体对象entity
	 * 
	 * @author xuning
	 */
	public int deleteSecCheckInfo(SecCheckInfo entity) {
		try {
			logger.info("事务操作方法：SecCheckInfoService--deleteSecCheckInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SecCheckInfoService--deleteSecCheckInfo 异常！");
		} 
		return secCheckInfoDAO.deleteSecCheckInfo(entity);
	}
	  /**
	   * 删除计划送检日期在今年之后，并且类别与传的参数类别一样
	   * @author xuning
	   */
	public int deleteSecCheckInfoUpdate(SecCheckInfo entity) {
		try {
			logger.info("事务操作方法：SecCheckInfoService--deleteSecCheckInfoUpdate");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SecCheckInfoService--deleteSecCheckInfoUpdate 异常！");
		} 
		return secCheckInfoDAO.deleteSecCheckInfoUpdate(entity);
	}
	
	/**
	 * 修改安全检查单实体对象entity
	 * 
	 * @author xuning
	 */
	public int updateSecCheckInfo(SecCheckInfo entity) {
		try {
			logger.info("事务操作方法：SecCheckInfoService--updateSecCheckInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：SecCheckInfoService--updateSecCheckInfo 异常！");
		} 
		return secCheckInfoDAO.updateSecCheckInfo(entity);
	}
	/**
	 * 根据实体对象Id返回entity
	 * @author 刘镝
	 */
	  public SecCheckInfo getSecCheckInfoById(String id) {
	    return secCheckInfoDAO.getSecCheckInfoById(id);
	  }

}
