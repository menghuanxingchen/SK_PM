package com.pm.business.userinfo.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.persistence.sysinfo.entity.SysCodeGroupInfo;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.util.DateUtil;

@Controller
@RequestMapping("/RoleInfoController")
public class RoleInfoController {
	
	@Resource
	private SysCodeInfoService sysCodeInfoService;
	
	@RequestMapping("/defaultJsp")
	public String tablemanagePage()
	{
		return "web/basicinfo/authinfo/roleinfo_list";
	}
	
	/**
	 * 列表页-查询条件初始化
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel SysCodeInfo sysCodeInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<SysCodeInfo> positionGroupList = sysCodeInfoService.getCodeListByGroupCodePage(sysCodeInfo,PmConstant.POSITION_GROUP,processor.getPageIndex(),processor.getPageSize());
			model.put("data", positionGroupList);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	
/*	*//**
	 * 添加信息前
	 * @param processor
	 * @return
	 *//*
	@RequestMapping("/beforeAddEntity.json")
	public @ResponseBody JSONMap<String,Object> beforeAddEntity(@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//查询部门下拉列表
			List<SysCodeInfo> departList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.DEPART_GROUP);//部门group code 
			model.put("departList", departList);
			//查询职位下拉列表
			List<SysCodeInfo> positionList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.POSITION_GROUP);//职位group code 
			model.put("positionList", positionList);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}*/
	
	/**
	 * 录入信息
	 * @param sysUserInfo
	 * @return
	 */
	@RequestMapping("/addEntityData.json")
	public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel SysCodeInfo sysCodeInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			/*sysCodeInfo.setCreate_id(processor.getCurrentUserId());
			sysCodeInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D));
			sysUserInfo.setUser_num(sysUserInfo.getUser_num().trim());
			sysUserInfo.setPwd(sysUserInfo.getPwd().trim());
			sysUserInfo.setUser_nm(sysUserInfo.getUser_nm().trim());
			sysUserInfoService.saveSysUserInfo(sysUserInfo);*/
			SysCodeInfo entity = sysCodeInfoService.getMaxCodeValue(PmConstant.POSITION_GROUP);
			sysCodeInfo.setCode_value(String.valueOf(Integer.parseInt(entity.getMax_code_value())+1));
			SysCodeGroupInfo sysCodeGroupInfo = new SysCodeGroupInfo();
			sysCodeInfo.setCode_group_value(PmConstant.POSITION_GROUP);
			sysCodeInfoService.saveSysCodeInfo(sysCodeGroupInfo, sysCodeInfo);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
			System.out.println(e.getMessage());
		}
		return model;
	}
	
	/**
	 * 修改信息前获取信息
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeUpdateEntity.json")
	public @ResponseBody JSONMap<String,Object> beforeUpdateEntity(@FormModel SysCodeInfo sysCodeInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			SysCodeInfo entity = sysCodeInfoService.getSysCodeInfoById(sysCodeInfo.getCode_id());
			model.put("sysCodeInfo", entity);
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
	 * @param sysUserInfo
	 * @return
	 */
	@RequestMapping("/updateEntityData.json")
	public @ResponseBody JSONMap<String,Object> updateEntityData(@FormModel SysCodeInfo sysCodeInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			sysCodeInfo.setUpdate_id(processor.getCurrentUserId());
			sysCodeInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D));
			sysCodeInfoService.updateSysCodeInfo(sysCodeInfo);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 删除用户
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteEntityData.json")
	public @ResponseBody JSONMap<String,Object> deleteEntityData(@FormModel SysCodeInfo sysCodeInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			sysCodeInfoService.deleteSysCodeInfo(sysCodeInfo);
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
