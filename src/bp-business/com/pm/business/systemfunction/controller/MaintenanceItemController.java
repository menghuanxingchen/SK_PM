package com.pm.business.systemfunction.controller;



import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.systemfunction.service.MaintenanceItemService;
import com.pm.persistence.basicinfo.entity.MachineInfo;
import com.pm.persistence.sysinfo.entity.SysCodeInfo;
import com.pm.persistence.systemfunction.entity.MaintenanceItemInfo;
import com.repast.core.constant.PmConstant;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.repast.core.uiview.format.IConvert;
import com.repast.core.uiview.format.UIDataConvert;
import com.repast.core.uiview.format.impl.SqlMapConvertImpl;
import com.repast.persistence.entity.SysUserInfo;




@Controller
@RequestMapping("/MaintenanceItemController")
public class MaintenanceItemController {
	
	@Resource
	private MaintenanceItemService maintenanceItemService;

	@Resource
	private SysCodeInfoService sysCodeInfoService;

	@RequestMapping("/defaultJsp")
	public String tablepage(){
		return "web/systemfunction/maintain_item_list";
	}	
	/**
	 * 设备列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryMaintainItemListpage.json")
	public @ResponseBody JSONMap<String,Object> queryMaintainItemListpage(@FormModel("maintainiteminfo") MaintenanceItemInfo maintainiteminfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			Pagination<MaintenanceItemInfo> maintainitemInfoList = 
					maintenanceItemService.queryMaintainItemListpage(maintainiteminfo,processor.getPageIndex(), processor.getPageSize());
			
			model.put("data", maintainitemInfoList);			
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
	 * 保存设备
	 * @param InsPlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/saveMaintenanceItemInfo.json")
	public @ResponseBody JSONMap<String,Object> saveMaintenanceItemInfo(@FormModel("maintainiteminfo") MaintenanceItemInfo maintainiteminfo,
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int savecount = maintenanceItemService.saveMaintenanceItemInfo(maintainiteminfo,processor);
			if(savecount>0){
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			}else{
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	/**
	 * 得到设备Entity
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryMaintenanceItemInfo.json")
	public @ResponseBody JSONMap<String,Object> queryMaintenanceItemInfo(@FormModel("maintainiteminfo") MaintenanceItemInfo maintainiteminfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			MaintenanceItemInfo maintenanceItemInfo = 
					maintenanceItemService.getMaintenanceItemInfo(maintainiteminfo);
			
			SysCodeInfo sysCodeInfo= new SysCodeInfo();
			sysCodeInfo.setCode_group_value(PmConstant.MACH_TYPE_GROUP);//设备类别
			List<SysCodeInfo> machinespeciesList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			//得到设备类型:用类别的code_id 和 code_group_value 得到 类别的sub_code_group_value
			sysCodeInfo.setCode_value(maintenanceItemInfo.getMachine_species_id());
			SysCodeInfo temp = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo).get(0);

			SysCodeInfo subTemp = new SysCodeInfo();
			subTemp.setCode_group_value(temp.getSub_code_group_value());
			List<SysCodeInfo> machinetypeList=  sysCodeInfoService.querySysCodeInfoListNoPage(subTemp);
			
			model.put("maintenanceItemInfo",maintenanceItemInfo);
			model.put("machinespeciesList", machinespeciesList);
			model.put("infoList", machinetypeList);
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
	 * 修改设备信息
	 * @param id
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateMaintenanceItemInfo.json")
	public @ResponseBody JSONMap<String,Object> updateMaintenanceItemInfo(@FormModel("maintainiteminfo") MaintenanceItemInfo maintainiteminfo,																
			@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int update_flag = maintenanceItemService.updateMaintenanceItemInfo(maintainiteminfo,processor);
			if(update_flag>0){
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			}else{
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
			}
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
}
