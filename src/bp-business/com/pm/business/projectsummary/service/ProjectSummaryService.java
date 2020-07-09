package com.pm.business.projectsummary.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.pm.persistence.projectsummary.dao.ProjectSummaryDetailDAO;
import com.pm.persistence.projectsummary.dao.ProjectSummaryInfoDAO;
import com.pm.persistence.projectsummary.dto.ProjectSummaryInfoDto;
import com.pm.persistence.projectsummary.entity.ProjectSummaryDetail;
import com.pm.persistence.projectsummary.entity.ProjectSummaryInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;
@Service("ProjectSummaryService")
public class ProjectSummaryService {

	@Resource(name="ProjectSummaryInfoDAO")
	private ProjectSummaryInfoDAO projectSummaryInfoDAO;

	@Resource(name="ProjectSummaryDetailDAO")
	private ProjectSummaryDetailDAO projectSummaryDetailDAO;
	
	private static Logger logger = Logger.getLogger("logger");
	
	/**
	 * 
	 * @param entity
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Pagination<ProjectSummaryInfo> queryProjectSummaryInfoList(ProjectSummaryInfo entity,int pageIndex,int pageSize){
		return projectSummaryInfoDAO.queryProjectSummaryInfoList(entity, pageIndex, pageSize);
	}

	/**
	 * 
	 * @param entity
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Pagination<ProjectSummaryInfo> queryProjectSummaryInfoListBigGroup(ProjectSummaryInfo entity,int pageIndex,int pageSize){
		return projectSummaryInfoDAO.queryProjectSummaryInfoListBigGroup(entity, pageIndex, pageSize);
	}
	
	/**
	 * 保存实体对象entity
	 * @author 
	 */
	public void saveProjectSummaryInfo(ProjectSummaryInfo projectSummaryInfo,List<ProjectSummaryDetail> projectSummaryDetailList,Processor processor) {
		try {
			projectSummaryInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			projectSummaryInfo.setCreate_id(processor.getCurrentUserId());
			try {
				logger.info("事务操作方法：ProjectSummaryService--saveProjectSummaryInfo");
				logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
			} catch (Exception e) {
				logger.error("事务操作方法：ProjectSummaryService--saveProjectSummaryInfo 异常！");
			}
			String id = projectSummaryInfoDAO.saveProjectSummaryInfo(projectSummaryInfo);
			ProjectSummaryDetail projectSummaryDetail = null;
			if(projectSummaryDetailList!=null){
				for(int i=0;i<projectSummaryDetailList.size();i++){
					projectSummaryDetail = new ProjectSummaryDetail();
					projectSummaryDetail.setProject_id(id);
					projectSummaryDetail.setWork_content(projectSummaryDetailList.get(i).getWork_content());
					projectSummaryDetail.setWork_date(projectSummaryDetailList.get(i).getWork_date());
					projectSummaryDetail.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
					projectSummaryDetail.setCreate_id(processor.getCurrentUserId());
					projectSummaryDetailDAO.saveProjectSummaryDetail(projectSummaryDetail);
				}
			}
			
		} catch (Exception e) {
			System.out.println("saveProjectSummaryInfo is error");
		}
	}
	
	
	/**
	 * 修改实体对象entity
	 * @author 
	 */
	public int updateProjectSummaryInfo(ProjectSummaryInfo entity,List<ProjectSummaryDetail> projectSummaryDetailList,Processor processor) {
		int flag = projectSummaryDetailDAO.deleteProjectSummaryDetailByProId(entity.getProject_id());
		try {
			logger.info("事务操作方法：ProjectSummaryService--updateProjectSummaryInfo");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProjectSummaryService--updateProjectSummaryInfo 异常！");
		}
		int cnt = 0;
		ProjectSummaryDetail projectSummaryDetail = new ProjectSummaryDetail();
		if(projectSummaryDetailList!=null){
				for(int i=0;i<projectSummaryDetailList.size();i++){
					try {
						projectSummaryDetail = new ProjectSummaryDetail();
						projectSummaryDetail.setProject_id(entity.getProject_id());
						projectSummaryDetail.setWork_content(projectSummaryDetailList.get(i).getWork_content());
						projectSummaryDetail.setWork_date(projectSummaryDetailList.get(i).getWork_date());
						projectSummaryDetail.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
						projectSummaryDetail.setCreate_id(processor.getCurrentUserId());
						projectSummaryDetail.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
						projectSummaryDetail.setUpdate_id(processor.getCurrentUserId());
						cnt += projectSummaryDetailDAO.saveProjectSummaryDetail(projectSummaryDetail);
					} catch (Exception e) {
						System.out.println("updateProjectSummaryInfo is error"+e.getMessage());
					}
					
				}
		}
		try {
			entity.setUpdate_id(processor.getCurrentUserId());
			entity.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			cnt += projectSummaryInfoDAO.updateProjectSummaryInfo(entity);
			if(PmConstant.PROJECT_TYPE_BIG.equals(entity.getProject_type())){
				int count = projectSummaryInfoDAO.updateBigProName(entity);
			}
		} catch (Exception e) {
			System.out.println("updateProjectSummaryInfo is error"+e.getMessage());
		}
		
		return cnt;
	}
	
	/**
	 * 删除实体对象entity
	 * @author 
	 */
	public int deleteProjectSummaryInfo(ProjectSummaryInfo entity) {
		try {
			logger.info("事务操作方法：ProjectSummaryService--deleteProjectSummaryDetailByProId");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProjectSummaryService--deleteProjectSummaryDetailByProId 异常！");
		}
		int flag = projectSummaryDetailDAO.deleteProjectSummaryDetailByProId(entity.getProject_id());
		return projectSummaryInfoDAO.deleteProjectSummaryInfo(entity);
	}
	
	/**
	 * 根据实体对象Id返回entity
	 * @author 
	 */
	public ProjectSummaryInfo getProjectSummaryInfoById(String id) {
		return projectSummaryInfoDAO.getProjectSummaryInfoById(id);
	}
	
	  /**
	   * 查询最近两个行为的时间和内容
	   * @author fish
	   */
	  public ProjectSummaryInfoDto queryTwoMonthInfo(){
		  List<ProjectSummaryInfoDto> projectSummaryInfoDtoList = projectSummaryInfoDAO.queryTwoMonthInfo();
		  ProjectSummaryInfoDto projectSummaryInfoDto = new ProjectSummaryInfoDto();
		  if(projectSummaryInfoDtoList!=null&&projectSummaryInfoDtoList.size()>0){
			  projectSummaryInfoDto.setWorkDate1(projectSummaryInfoDtoList.get(0).getWorkDate());
		  }
		  if(projectSummaryInfoDtoList!=null&&projectSummaryInfoDtoList.size()>1){
			  projectSummaryInfoDto.setWorkDate2(projectSummaryInfoDtoList.get(1).getWorkDate());
		  }
		    return projectSummaryInfoDto;
	  }
	  
		/**
		 * 根据实体对象Id返回entity
		 * @author 
		 */
		public List<ProjectSummaryDetail> queryProjectSummaryDetailList(ProjectSummaryDetail entity) {
			return projectSummaryDetailDAO.queryProjectSummaryDetailList(entity);
		}
		
	public List<ProjectSummaryInfo> excelDownload(String[] excelList){
		try {
			logger.info("事务操作方法：ProjectSummaryService--excelDownload");
			logger.info("事务操作时间："+DateUtil.getNow(DateUtil.Y_M_D_HMS));
		} catch (Exception e) {
			logger.error("事务操作方法：ProjectSummaryService--excelDownload 异常！");
		}
		List<ProjectSummaryInfo> projectSummaryInfoList = new ArrayList<ProjectSummaryInfo>();
		ProjectSummaryInfo projectSummaryInfo = null;
		List<ProjectSummaryDetail> projectSummaryDetailList = new ArrayList<ProjectSummaryDetail>();
		ProjectSummaryDetail projectSummaryDetail = null;
		for(String s:excelList){
			projectSummaryInfo = new ProjectSummaryInfo();
			 projectSummaryInfo = projectSummaryInfoDAO.getProjectSummaryInfoByIdForExcel(s);
			projectSummaryInfoList.add(projectSummaryInfo);
		}
		for(int i=0;i<excelList.length;i++){
			 projectSummaryDetail = new ProjectSummaryDetail();
			 projectSummaryDetail.setProject_id(excelList[i]);
			 projectSummaryDetailList = projectSummaryDetailDAO.queryProjectSummaryDetailList(projectSummaryDetail);
			 projectSummaryInfoList.get(i).setProjectSummaryDetailList(projectSummaryDetailList);
		 }
		 return projectSummaryInfoList;
	}
	
}
