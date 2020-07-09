package com.pm.business.projectsummary.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pm.business.projectsummary.service.ProjectSummaryService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.common.print.ProjectSummaryPrint;
import com.pm.persistence.projectsummary.dto.ProjectSummaryInfoDto;
import com.pm.persistence.projectsummary.entity.ProjectSummaryDetail;
import com.pm.persistence.projectsummary.entity.ProjectSummaryInfo;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.IConvert;
import com.repast.core.uiview.format.UIDataConvert;
import com.repast.core.uiview.format.impl.SqlMapConvertImpl;

@Controller
@RequestMapping("/ProjectSummaryController")
public class ProjectSummaryController {
	
	@Resource(name="ProjectSummaryService")
	private ProjectSummaryService projectSummaryService;
	@Resource
	private SysCodeInfoService sysCodeInfoService;
	@Resource
	private SysUserInfoService sysUserInfoService;
	
	@RequestMapping("/defaultJsp")
	public String tablemanagePage(RedirectAttributes redirectAttributes)
	{
		redirectAttributes.addAttribute("message", "This is a test."); 
		return "web/projectsummary/projectsummary_list";
	}
	
	/**
	 * 列表页-查询条件初始化
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeDataList.json")
	public @ResponseBody JSONMap<String,Object> beforeDataList(@FormModel ProjectSummaryInfo projectSummaryInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			List<SysCodeInfo> projectPlanType = sysCodeInfoService.getCodeListByGroupCode(PmConstant.PROJECT_PLAN_TYPE);//项目计划管理-项目计划类型
			model.put("projectPlanType", projectPlanType);
			List<SysCodeInfo> projectPlanState = sysCodeInfoService.getCodeListByGroupCode(PmConstant.PROJECT_PLAN_STATE);//项目计划管理-项目计划状态
			model.put("projectPlanState", projectPlanState);
			List<SysCodeInfo> projectStep = sysCodeInfoService.getCodeListByGroupCode(PmConstant.PROJECT_STEP);//项目计划管理-项目计划状态
			model.put("projectStep", projectStep);
			ProjectSummaryInfoDto projectSummaryInfoDtoEo  = projectSummaryService.queryTwoMonthInfo();
			model.put("projectSummaryInfoDtoEo", projectSummaryInfoDtoEo);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 列表页-查询条件初始化
	 * @param processor
	 * @return
	 */
	@RequestMapping("/getWorkDataList.json")
	public @ResponseBody JSONMap<String,Object> getWorkDataList(@FormModel ProjectSummaryInfo projectSummaryInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			ProjectSummaryInfoDto projectSummaryInfoDtoEo  = projectSummaryService.queryTwoMonthInfo();
			model.put("projectSummaryInfoDtoEo", projectSummaryInfoDtoEo);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 查询数据
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel ProjectSummaryInfo projectSummaryInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<ProjectSummaryInfo> projectSummaryInfoList = projectSummaryService.queryProjectSummaryInfoList(projectSummaryInfo,  processor.getPageIndex(),processor.getPageSize());
			UIDataConvert<ProjectSummaryInfo> convert = new UIDataConvert<ProjectSummaryInfo>();
			IConvert projectType = new SqlMapConvertImpl("project_type", "project_type_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.PROJECT_PLAN_TYPE+"' ");
			convert.addConvert(projectType);
			IConvert bigProjectStep = new SqlMapConvertImpl("big_project_step", "big_project_step_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.PROJECT_STEP+"' ");
			convert.addConvert(bigProjectStep);
			IConvert projectState = new SqlMapConvertImpl("state", "state_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.PROJECT_PLAN_STATE+"' ");
			convert.addConvert(projectState);
			convert.format(projectSummaryInfoList);
			model.put("data", projectSummaryInfoList);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 查询数据
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataListBigGroup.json")
	public @ResponseBody JSONMap<String,Object> queryDataListBigGroup(@FormModel ProjectSummaryInfo projectSummaryInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<ProjectSummaryInfo> projectSummaryInfoList = projectSummaryService.queryProjectSummaryInfoListBigGroup(projectSummaryInfo,  processor.getPageIndex(),processor.getPageSize());
			model.put("data", projectSummaryInfoList);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 添加入库信息前
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeAddEntity.json")
	public @ResponseBody JSONMap<String,Object> beforeAddEntity(@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 大项目新增页面跳转
	 * @param processor
	 * @return
	 */
	@RequestMapping("/bigAddEntityForward")
	public String bigAddEntityForward() {
		return "web/projectsummary/projectsummary_addbig";
	}
	
	/**
	 * 小项目新增页面跳转
	 * @param processor
	 * @return
	 */
	@RequestMapping("/smallAddEntityDataFun")
	public String smallAddEntityDataFun() {
		return "web/projectsummary/projectsummary_addsmall";
	}
	/**
	 * 录入入库信息
	 * @param tsLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/addEntityData.json")
	public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel ProjectSummaryInfo projectSummaryInfo,@FormModel("projectSummaryDetailList") List<ProjectSummaryDetail> projectSummaryDetailList,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			projectSummaryService.saveProjectSummaryInfo(projectSummaryInfo,projectSummaryDetailList,processor);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 修改入库信息前获取入库信息
	 * @param tsLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeUpdateEntity.json")
	public JSONMap<String,Object> beforeUpdateEntity(@FormModel ProjectSummaryInfo projectSummaryInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			projectSummaryInfo = projectSummaryService.getProjectSummaryInfoById(projectSummaryInfo.getProject_id());
			List<SysCodeInfo> projectPlanState = sysCodeInfoService.getCodeListByGroupCode(PmConstant.PROJECT_PLAN_STATE);//项目计划管理-项目计划状态
			projectSummaryInfo.setProjectPlanState(projectPlanState);
			List<SysCodeInfo> projectStep = sysCodeInfoService.getCodeListByGroupCode(PmConstant.PROJECT_STEP);//项目计划管理-项目计划状态
			projectSummaryInfo.setProjectStep(projectStep);
			model.put("projectSummaryInfo", projectSummaryInfo);
			ProjectSummaryDetail projectSummaryDetail = new ProjectSummaryDetail();
			projectSummaryDetail.setProject_id(projectSummaryInfo.getProject_id());
			List<ProjectSummaryDetail> projectSummaryDetailList= projectSummaryService.queryProjectSummaryDetailList(projectSummaryDetail);
			model.put("projectSummaryDetailList", projectSummaryDetailList);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 修改入库信息前获取入库信息
	 * @param tsLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateEntityData.json")
	public JSONMap<String,Object> updateEntityData(@FormModel ProjectSummaryInfo projectSummaryInfo,@FormModel("projectSummaryDetailList") List<ProjectSummaryDetail> projectSummaryDetailList,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			projectSummaryService.updateProjectSummaryInfo(projectSummaryInfo,projectSummaryDetailList,processor);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	/**
	 * 修改信息
	 * @param processor
	 * @return
	 */
	@RequestMapping("/goToUpdateEntityData")
	public String updateEntityData(@ModelAttribute("proObject") Object proObject) {
		JSONArray json = JSONArray .fromObject(proObject);
		Iterator<Object> it = json.iterator();
       Map<String, String> map = new HashMap<String, String>();
        while (it.hasNext()) {
            JSONObject ob = (JSONObject) it.next();
            if(ob.getString("id")!=null){
            	map.put("id", ob.getString("id"));
            }
            if(ob.getString("type")!=null){
            	map.put("type", ob.getString("type"));
            }
        }
        
		if(PmConstant.PROJECT_TYPE_BIG.equals(map.get("type"))){
			return "web/projectsummary/projectsummary_updatebig";
		}else{
			return "web/projectsummary/projectsummary_updatesmall";
		}
	}
	/**
	 * 删除信息
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteEntityData.json")
	public @ResponseBody JSONMap<String,Object> deleteEntityData(@FormModel ProjectSummaryInfo projectSummaryInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			projectSummaryService.deleteProjectSummaryInfo(projectSummaryInfo);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 打印信息
	 * @param processor
	 * @return
	 */
	@RequestMapping("/excelDownload.json")
	public @ResponseBody JSONMap<String,Object> excelDownload(@FormModel("excelList") String[] excelList,@FormModel Processor processor,HttpServletRequest request) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			String[] sourceStrArray = excelList[0].split(",");
			List<ProjectSummaryInfo> projectSummaryInfoList = projectSummaryService.excelDownload(sourceStrArray);
			List<ProjectSummaryInfo> dataList = new ArrayList<ProjectSummaryInfo>();
			ProjectSummaryInfo dataEntity = null;
			for(int i=0;i<projectSummaryInfoList.size();i++){
				projectSummaryInfoList.get(i).setNumber(""+(i+1));
				List<ProjectSummaryDetail> projectSummaryDetailList = projectSummaryInfoList.get(i).getProjectSummaryDetailList();
				if(projectSummaryDetailList.size()<1){
					dataList.add(projectSummaryInfoList.get(i));
				}else{
					for(int j=0;j<projectSummaryDetailList.size();j++){
						dataEntity = new ProjectSummaryInfo();
						dataEntity.setNumber(projectSummaryInfoList.get(i).getNumber());
						dataEntity.setProject_nm(projectSummaryInfoList.get(i).getProject_nm());
						dataEntity.setContent(projectSummaryInfoList.get(i).getContent());
						dataEntity.setDuty_user(projectSummaryInfoList.get(i).getDuty_user());
						dataEntity.setProject_type_nm(projectSummaryInfoList.get(i).getProject_type_nm());
						dataEntity.setPlan_start_date(projectSummaryInfoList.get(i).getPlan_start_date());
						dataEntity.setPlan_end_date(projectSummaryInfoList.get(i).getPlan_end_date());
						dataEntity.setWork_date(projectSummaryDetailList.get(j).getWork_date());
						dataEntity.setWork_content(projectSummaryDetailList.get(j).getWork_content());
						dataEntity.setReal_end_date(projectSummaryInfoList.get(i).getReal_end_date());
						dataEntity.setState_nm(projectSummaryInfoList.get(i).getState_nm());
						dataList.add(dataEntity);
					}
				}
			}
			ProjectSummaryPrint projectSummaryPrint = new ProjectSummaryPrint(request);
			String outPutUrl = projectSummaryPrint.mdPrint(dataList);
			
			model.put("filePath", outPutUrl);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
}
