package com.pm.business.seccheckinfo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.seccheckinfo.service.SecCheckInfoService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.persistence.repair.dto.RepairOrderInfoDTO;
import com.pm.persistence.repair.entity.ApproveInfo;
import com.pm.persistence.repair.entity.RepairOrderInfo;
import com.pm.persistence.seccheckinfo.dto.SecCheckInfoDTO;
import com.pm.persistence.seccheckinfo.entity.SecCheckInfo;
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
import com.repast.core.util.DateUtil;
import com.repast.persistence.entity.SysUserInfo;

@Controller
@RequestMapping("/SecCheckInfoController")
public class SecCheckInfoController {
	@Resource
	private SecCheckInfoService secCheckInfoService;
	
	@Resource
	private SysCodeInfoService sysCodeInfoService;

	@Resource
	private SysUserInfoService sysUserInfoService;
	
	@RequestMapping("/defaultJsp")
	public String tablemanagePage() {
		return "web/seccheckinfo/seccheck_list";
	}
	/**
	 * 维修单新增页面下拉列表
	 * 
	 * @param sysCodeInfo
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDownList.json")
	public @ResponseBody JSONMap<String, Object> queryDownList(
			@FormModel SysCodeInfo sysCodeInfo,@FormModel SysUserInfo sysUserInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			sysCodeInfo.setCode_group_value(PmConstant.job_classification);
			List<SysCodeInfo> repairPlaceList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);			
			model.put("repairPlaceList", repairPlaceList);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	/**
	 * 安全检查单新增
	 * @param secCheckInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/addSecCheckInfoData.json")
	public @ResponseBody JSONMap<String, Object> addSecCheckInfoData(
			@FormModel SecCheckInfo secCheckInfo,
            @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			secCheckInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			secCheckInfo.setCreate_id(processor.getCurrentUserId());
			int one=secCheckInfoService.saveSecCheckInfo(secCheckInfo);
			if(one==1){
				secCheckInfoService.saveSecCheckInfo1(secCheckInfo);
			}
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}

		return model;
	}
	/**
	 * 安全检查列表查询
	 * @param secCheckInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/querySecCheckInfoList.json")
	public @ResponseBody JSONMap<String, Object> querySecCheckInfoList(
			@FormModel ("secCheckInfo")SecCheckInfoDTO secCheckInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			Pagination<SecCheckInfoDTO> secCheckInfoList = secCheckInfoService.querySecCheckInfoList2(secCheckInfo,processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<SecCheckInfoDTO> convert = new UIDataConvert<SecCheckInfoDTO>();
			IConvert workType = new SqlMapConvertImpl("work_type","work_type_nm", "sys_code_info","code_nm", "code_value","and code_group_value='job_classification'");
			convert.addConvert(workType);
			convert.format(secCheckInfoList);
			model.put("data", secCheckInfoList);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	/**
	 * 删除安全检查单信息
	 * 
	 * @param secCheckInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteSecCheckInfoData.json")
	public @ResponseBody JSONMap<String, Object> deleteSecCheckInfoData(
			@FormModel SecCheckInfo secCheckInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		int cnt =secCheckInfoService.deleteSecCheckInfo(secCheckInfo);
		if (cnt > 0) {
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		} else {
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
		}
		return model;
	}	
	/**
	 * 安全检查修改前查询
	 * 
	 * @param secCheckInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/querySecCheckBeforeUpdate.json")
	public @ResponseBody JSONMap<String, Object> querySecCheckBeforeUpdate(
			@FormModel("sec_check_id") String sec_check_id,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			SecCheckInfo secCheckInfoList = secCheckInfoService.getSecCheckInfoById(sec_check_id);
			model.put("secCheckInfo", secCheckInfoList);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	/**
	 * 修改安全检查单信息
	 * 
	 * @param secCheckInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateSecCheckInfoData.json")
	public @ResponseBody JSONMap<String, Object> updateSecCheckInfoData(
			@FormModel SecCheckInfo secCheckInfo,
            @FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			    secCheckInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			    secCheckInfo.setUpdate_id(processor.getCurrentUserId());
				secCheckInfoService.updateSecCheckInfo(secCheckInfo);
			    secCheckInfoService.deleteSecCheckInfoUpdate(secCheckInfo);
			    int cnt =secCheckInfoService.saveSecCheckInfo1(secCheckInfo);
				if(cnt>0){
					model.put(SysConstant.OP_FLAG, true);
					model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
				}
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}

}
