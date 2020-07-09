package com.pm.business.systemfunction.controller;



import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.basicinfo.service.CheckItemService;
import com.pm.business.basicinfo.service.MachineService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.systemfunction.service.SupplierService;
import com.pm.persistence.basicinfo.entity.CheckitemInfo;
import com.pm.persistence.basicinfo.entity.MachineInfo;
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
@RequestMapping("/SupplierController")
public class SupplierController {
	
	@Resource
	private SupplierService supplierService;
	
	@RequestMapping("/defaultJsp")
	public String tablepage(){
		return "web/systemfunction/supplier_list";
	}	
	/**
	 * 设备列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/querySysCodeInfoList.json")
	public @ResponseBody JSONMap<String,Object> querySysCodeInfoList(@FormModel("sysCodeInfo") SysCodeInfo sysCodeInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			Pagination<SysCodeInfo> infoList = 
					supplierService.querySysCodeInfoList(sysCodeInfo,processor.getPageIndex(), processor.getPageSize());
			
			model.put("data", infoList);			
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
	@RequestMapping("/saveSysCodeInfo.json")
	public @ResponseBody JSONMap<String,Object> saveSysCodeInfo(@FormModel("sysCodeInfo") SysCodeInfo sysCodeInfo,
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int savecount = supplierService.saveSysCodeInfo(sysCodeInfo,processor);
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
	@RequestMapping("/querySysCodeInfo.json")
	public @ResponseBody JSONMap<String,Object> querySysCodeInfo(@FormModel("sysCodeInfo") SysCodeInfo sysCodeInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			SysCodeInfo info = 
					supplierService.querySysCodeInfo(sysCodeInfo);			
			model.put("data", info);			
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
	@RequestMapping("/updateSysCodeInfo.json")
	public @ResponseBody JSONMap<String,Object> updateSysCodeInfo(@FormModel("sysCodeInfo") SysCodeInfo sysCodeInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int update_flag = supplierService.updateSysCodeInfo(sysCodeInfo,processor);
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
