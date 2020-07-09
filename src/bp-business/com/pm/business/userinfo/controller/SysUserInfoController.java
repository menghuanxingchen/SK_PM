package com.pm.business.userinfo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
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
@RequestMapping("/SysUserInfoController")
public class SysUserInfoController {
	
	@Resource
	private SysUserInfoService sysUserInfoService;
	@Resource
	private SysCodeInfoService sysCodeInfoService;
	
	@RequestMapping("/defaultJsp")
	public String tablemanagePage()
	{
		return "web/userinfo/userinfo_list";
	}
	/**
	 * 按部门和下级部门查询用户数据
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/querySysUserInfoList.json")
	public @ResponseBody JSONMap<String,Object> querySysUserInfoList(@FormModel SysUserInfo sysUserInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			model.put("sysUserInfoList", sysUserInfoService.querySysUserInfoList(sysUserInfo));
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
	@RequestMapping("/getSubCodeList.json")
	public @ResponseBody JSONMap<String,Object> getSubCodeList(@FormModel("subGroupCode") String subGroupCode,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//下属部门下拉列表
			List<SysCodeInfo> subDepartList = sysCodeInfoService.getSubCodeList(PmConstant.DEPART_GROUP, subGroupCode);
			List<SysCodeInfo> sqmRoleList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.SQM_ROLE);
			model.put("subDepartList", subDepartList);
			model.put("sqmRoleList", sqmRoleList);
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
	 * 用户信息查询
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeDataList.json")
	public @ResponseBody JSONMap<String,Object> beforeDataList(@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//查询部门下拉列表
			List<SysCodeInfo> departList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.DEPART_GROUP);//部门group code 
			model.put("departList", departList);
			//排序下拉列表
			List<SysCodeInfo> orderList = sysCodeInfoService.getOrderList(PmConstant.ORDER_USER_GROUP);//排序group code
			model.put("orderList", orderList);
			
			model.put("loginUserDept", processor.getDept());
			model.put("loginUserSubdept", processor.getSubdept());
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
	 * 用户信息查询
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel SysUserInfo sysUserInfo,@FormModel("subGroupCode") String subGroupCode,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//查询部门下拉列表
			List<SysCodeInfo> departList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.DEPART_GROUP);//部门group code 
			model.put("departList", departList);
			//排序下拉列表
			List<SysCodeInfo> orderList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.ORDER_GROUP);//排序group code
			model.put("orderList", orderList);
			//列表查询（分页）
			Pagination<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoList(sysUserInfo, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<SysUserInfo> convert = new UIDataConvert<SysUserInfo>();
			//列表中部门code name转化
			IConvert dept = new SqlMapConvertImpl("dept_code", "dept_code_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.DEPART_GROUP+"' ");
			convert.addConvert(dept);
			//列表中职位code name转化
			IConvert position = new SqlMapConvertImpl("position_id", "position_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.POSITION_GROUP+"' ");
			convert.addConvert(position);
			//列表中sqm角色code name转化
			IConvert sqm = new SqlMapConvertImpl("sqm_role_code", "sqm_role_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.SQM_ROLE+"' ");
			convert.addConvert(sqm);
			convert.format(sysUserInfoList);
			model.put("data", sysUserInfoList);
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
	 * 添加信息前
	 * @param processor
	 * @return
	 */
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
			//查询sqm角色下拉列表
			List<SysCodeInfo> sqmRoleList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.SQM_ROLE);//sqm角色
			model.put("sqmRoleList", sqmRoleList);
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
	 * 录入信息
	 * @param sysUserInfo
	 * @return
	 */
	@RequestMapping("/addEntityData.json")
	public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel SysUserInfo sysUserInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			sysUserInfo.setCreate_id(processor.getCurrentUserId());
			sysUserInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D));
			sysUserInfo.setUser_num(sysUserInfo.getUser_num().trim());
			sysUserInfo.setPwd(sysUserInfo.getPwd().trim());
			sysUserInfo.setUser_nm(sysUserInfo.getUser_nm().trim());
			sysUserInfoService.saveSysUserInfo(sysUserInfo);
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
	public @ResponseBody JSONMap<String,Object> beforeUpdateEntity(@FormModel SysUserInfo sysUserInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//查询部门下拉列表
			List<SysCodeInfo> departList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.DEPART_GROUP);//部门group code 
			model.put("departList", departList);
			//查询职位下拉列表
			List<SysCodeInfo> positionList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.POSITION_GROUP);//职位group code 
			model.put("positionList", positionList);
			//根据计划id获得计划实体
			SysUserInfo sysUserInfoEo = sysUserInfoService.getSysUserInfoByIdOld(sysUserInfo.getUser_id());
			model.put("sysUserInfoEo", sysUserInfoEo);
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
	public @ResponseBody JSONMap<String,Object> updateEntityData(@FormModel SysUserInfo sysUserInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			sysUserInfo.setUpdate_id(processor.getCurrentUserId());
			sysUserInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D));
			sysUserInfoService.updateSysUserInfo(sysUserInfo);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	

	/**
	 * 修改密码
	 * @param sysUserInfo
	 * @return
	 */
	@RequestMapping("/updatePwdEntityData.json")
	public @ResponseBody JSONMap<String,Object> updatePwdEntityData(@FormModel SysUserInfo sysUserInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			sysUserInfo.setUpdate_id(processor.getCurrentUserId());
			sysUserInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D));
			sysUserInfoService.updateSysUserInfo(sysUserInfo);
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
	public @ResponseBody JSONMap<String,Object> deleteEntityData(@FormModel SysUserInfo sysUserInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			sysUserInfoService.deleteSysUserInfo(sysUserInfo);
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
