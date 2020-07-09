package com.pm.business.basicinfo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.basicinfo.service.MachineTypeService;
import com.pm.persistence.sysinfo.entity.SysCodeGroupInfo;
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

@Controller
@RequestMapping("/MachineTypeController")
public class MachineTypeController {
	
	@Resource
	private MachineTypeService machineTypeService;
	
	@RequestMapping("/defaultJsp")
	public String tablemanagePage()
	{
		return "web/basicinfo/baseinfo/machinetype_list";
	}
	/**
	 * 查询数据
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel SysCodeInfo sysCodeInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<SysCodeInfo> sysCodeInfoList = machineTypeService.querySysCodeInfoPageList(sysCodeInfo, processor.getPageIndex(),processor.getPageSize());
			/*UIDataConvert<SysCodeInfo> convert = new UIDataConvert<SysCodeInfo>();
			IConvert approvalStatus = new SqlMapConvertImpl("code_group_value", "code_group_name", "sys_code_group_info", "code_group_name","code_group_value", "");
			convert.addConvert(approvalStatus);
			convert.format(sysCodeInfoList);*/
			model.put("data", sysCodeInfoList);
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
	 * @param tsLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/addEntityData.json")
	public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel SysCodeInfo sysCodeInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			
			sysCodeInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			sysCodeInfo.setCreate_id(processor.getCurrentUserId());
			int cnt = machineTypeService.saveSysCodeInfo(sysCodeInfo);
			if(cnt>0){
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			}
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, "存在一样的code值");
		}
		return model;
	}
	
	/**
	 * 修改信息前获取信息
	 * @param tsLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeUpdateEntity.json")
	public @ResponseBody JSONMap<String,Object> beforeUpdateEntity(@FormModel SysCodeInfo sysCodeInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			sysCodeInfo = machineTypeService.getSysCodeInfoById(sysCodeInfo.getCode_id());
			SysCodeInfo temp = new SysCodeInfo();
			temp.setCode_group_value(PmConstant.MACH_TYPE_GROUP);//设备类别
			List<SysCodeInfo> machinespeciesList = machineTypeService.querySysCodeInfoListNoPage(temp);
			model.put("sysCodeInfo", sysCodeInfo);
			model.put("machinespeciesList", machinespeciesList);
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
	 * @param tsLmsPutware
	 * @param oldid
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateEntityData.json")
	public @ResponseBody JSONMap<String,Object> updateEntityData(@FormModel SysCodeGroupInfo sysCodeGroupInfo,@FormModel SysCodeInfo sysCodeInfo ,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			sysCodeInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			sysCodeInfo.setUpdate_id(processor.getCurrentUserId());
			machineTypeService.updateSysCodeInfo(sysCodeInfo);
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
	 * 删除信息
	 * @param tsLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteEntityData.json")
	public @ResponseBody JSONMap<String,Object> deleteEntityData(@FormModel SysCodeInfo sysCodeInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			machineTypeService.deleteSysCodeInfo(sysCodeInfo);
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
