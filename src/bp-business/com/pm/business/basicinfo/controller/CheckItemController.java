package com.pm.business.basicinfo.controller;



import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.basicinfo.service.CheckItemService;
import com.pm.business.basicinfo.service.MachineService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
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
@RequestMapping("/CheckItemController")
public class CheckItemController {
	
	@Resource
	private CheckItemService checkItemService;
	@Resource
	private SysCodeInfoService sysCodeInfoService;
	
	@RequestMapping("/defaultJsp")
	public String tablepage(){
		return "web/basicinfo/baseinfo/checkitem_list";
	}	
	/**
	 * 设备列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryCheckItemList.json")
	public @ResponseBody JSONMap<String,Object> queryCheckItemList(@FormModel("checkitemInfo") CheckitemInfo checkitemInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			Pagination<CheckitemInfo> checkItemInfoList = 
					checkItemService.getCheckItemList(checkitemInfo,processor.getPageIndex(), processor.getPageSize());
			
			UIDataConvert<CheckitemInfo> convert = new UIDataConvert<CheckitemInfo>();
			IConvert machine_species_id = new SqlMapConvertImpl("machine_species_id", "machine_species_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.MACH_TYPE_GROUP+"'");
			convert.addConvert(machine_species_id);
			
			IConvert machine_type_id = new SqlMapConvertImpl("machine_type_id", "machine_type_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.MACH_SPECIES_GROUP+"'");
			convert.addConvert(machine_type_id);				
			convert.format(checkItemInfoList);
			
			model.put("data", checkItemInfoList);			
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
	 * 设备列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryCheckItemListNames.json")
	public @ResponseBody JSONMap<String,Object> queryCheckItemListNames(@FormModel("checkitemInfo") CheckitemInfo checkitemInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			Pagination<CheckitemInfo> checkItemInfoList = 
					checkItemService.getCheckItemListNames(checkitemInfo,processor.getPageIndex(), processor.getPageSize());
			
			UIDataConvert<CheckitemInfo> convert = new UIDataConvert<CheckitemInfo>();
			IConvert machine_species_id = new SqlMapConvertImpl("machine_species_id", "machine_species_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.MACH_TYPE_GROUP+"'");
			convert.addConvert(machine_species_id);
			convert.format(checkItemInfoList);
			
			model.put("data", checkItemInfoList);			
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
	 * delete检查项目
	 * @param id
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteCheckItem.json")
	public @ResponseBody JSONMap<String,Object> deleteCheckItem(@FormModel("id") String id) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int delet_flag = checkItemService.deleteCheckitem(id);
			if(delet_flag>0){
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
	 * 保存设备
	 * @param InsPlanInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/saveCheckItemInfo.json")
	public @ResponseBody JSONMap<String,Object> saveCheckItemInfo(@FormModel("checkitemInfo") CheckitemInfo checkitemInfo,
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			CheckitemInfo temp= new CheckitemInfo();
			temp.setCheck_project_nm(checkitemInfo.getCheck_project_nm());
			List<CheckitemInfo> tempList=checkItemService.getCheckitemListCheckName(temp);
			if(tempList!=null&&tempList.size()!=0){
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, "name");
			}else{
				int savecount = checkItemService.saveCheckItemInfo(checkitemInfo,processor);
				if(savecount>0){
					model.put(SysConstant.OP_FLAG, true);
					model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
				}else{
					model.put(SysConstant.OP_FLAG, false);
					model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
				}
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
	@RequestMapping("/queryCheckitemEntity.json")
	public @ResponseBody JSONMap<String,Object> queryCheckitemEntity(@FormModel("checkitemInfo") CheckitemInfo checkitemInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			List<CheckitemInfo> checkitemInfoList = 
					checkItemService.getCheckitemListNo(checkitemInfo);
			
			SysCodeInfo sysCodeInfo= new SysCodeInfo();
			sysCodeInfo.setCode_group_value(PmConstant.MACH_TYPE_GROUP);//设备类别
			List<SysCodeInfo> machinespeciesList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			//得到设备类型:用类别的code_id 和 code_group_value 得到 类别的sub_code_group_value
			sysCodeInfo.setCode_value(checkitemInfoList.get(0).getMachine_species_id());
			SysCodeInfo temp = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo).get(0);

			SysCodeInfo subTemp = new SysCodeInfo();
			subTemp.setCode_group_value(temp.getSub_code_group_value());
			List<SysCodeInfo> machinetypeList=  sysCodeInfoService.querySysCodeInfoListNoPage(subTemp);
			
			model.put("machinespeciesList", machinespeciesList);
			model.put("infoList", machinetypeList);
			
			model.put("checkitemInfo", checkitemInfoList.get(0));			
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
	@RequestMapping("/updateCheckitemInfo.json")
	public @ResponseBody JSONMap<String,Object> updateCheckitemInfo(@FormModel("checkitemInfo") CheckitemInfo checkitemInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			CheckitemInfo temp= new CheckitemInfo();
			temp.setCheck_project_nm(checkitemInfo.getCheck_project_nm());
			temp.setCheck_project_id(checkitemInfo.getCheck_project_id());
			List<CheckitemInfo> tempList=checkItemService.getCheckitemListCheckName(temp);
			if(tempList!=null&&tempList.size()!=0){
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, "name");
			}else{
				int update_flag = checkItemService.updateCheckitemInfo(checkitemInfo,processor);
				if(update_flag>0){
					model.put(SysConstant.OP_FLAG, true);
					model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
				}else{
					model.put(SysConstant.OP_FLAG, false);
					model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	/**
	 * 列表pop
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryCheckItemListPop.json")
	public @ResponseBody JSONMap<String,Object> queryCheckItemListPop(@FormModel("checkitemInfo") CheckitemInfo checkitemInfo,																
																@FormModel("ids") String ids,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			Pagination<CheckitemInfo> checkitemList = 
					checkItemService.getCheckitemListPop(checkitemInfo,ids,processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<CheckitemInfo> convert = new UIDataConvert<CheckitemInfo>();
			IConvert machine_species_id = new SqlMapConvertImpl("machine_species_id", "machine_species_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.MACH_TYPE_GROUP+"'");
			convert.addConvert(machine_species_id);
			
			/*IConvert machine_type_id = new SqlMapConvertImpl("machine_type_id", "machine_type_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.MACH_SPECIES_GROUP+"'");
			convert.addConvert(machine_type_id);*/				
			convert.format(checkitemList);
			model.put("data", checkitemList);			
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
