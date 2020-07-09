package com.pm.business.basicinfo.controller;



import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.basicinfo.service.MachineService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
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
import com.repast.persistence.entity.SysUserInfo;




@Controller
@RequestMapping("/MachineController")
public class MachineController {
	
	@Resource
	private MachineService machineService;

	@Resource
	private SysCodeInfoService sysCodeInfoService;

	@RequestMapping("/defaultJsp")
	public String tablepage(){
		return "web/basicinfo/baseinfo/machine_list";
	}	
	/**
	 * 设备列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryMachineList.json")
	public @ResponseBody JSONMap<String,Object> queryMachineList(@FormModel("machineInfo") MachineInfo machineInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			Pagination<MachineInfo> machineInfoList = 
					machineService.getMachineList(machineInfo,processor.getPageIndex(), processor.getPageSize());
			
			UIDataConvert<MachineInfo> convert = new UIDataConvert<MachineInfo>();
			IConvert machine_type_id = new SqlMapConvertImpl("machine_type_id", "machine_type_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.MACH_SPECIES_GROUP+"'");
			convert.addConvert(machine_type_id);
			IConvert area_id = new SqlMapConvertImpl("area_id", "area_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.AREACODE+"'");
			convert.addConvert(area_id);
			convert.format(machineInfoList);	
			model.put("data", machineInfoList);			
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
	 * delete设备
	 * @param id
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteMachine.json")
	public @ResponseBody JSONMap<String,Object> deleteMachine(@FormModel("id") String id) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			int delet_flag = machineService.deleteMachine(id);
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
	@RequestMapping("/saveMachineInfo.json")
	public @ResponseBody JSONMap<String,Object> saveMachineInfo(@FormModel("machineInfo") MachineInfo machineInfo,
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			MachineInfo temp= new MachineInfo();
			temp.setMachine_nm(machineInfo.getMachine_nm());
			List<MachineInfo> tempList=machineService.getMachineListCheckName(temp);
			if(tempList!=null&&tempList.size()!=0){
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, "name");
			}else{
				int savecount = machineService.saveMachineInfo(machineInfo,processor);
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
	@RequestMapping("/queryMachineEntity.json")
	public @ResponseBody JSONMap<String,Object> queryMachineEntity(@FormModel("machineInfo") MachineInfo machineInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			List<MachineInfo> machineInfoList = 
					machineService.getMachineListNo(machineInfo);
			
			SysCodeInfo sysCodeInfo= new SysCodeInfo();
			sysCodeInfo.setCode_group_value(PmConstant.MACH_TYPE_GROUP);//设备类别
			List<SysCodeInfo> machinespeciesList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			//得到设备类型:用类别的code_id 和 code_group_value 得到 类别的sub_code_group_value
			sysCodeInfo.setCode_value(machineInfoList.get(0).getMachine_species_id());
			SysCodeInfo temp = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo).get(0);

			SysCodeInfo subTemp = new SysCodeInfo();
			subTemp.setCode_group_value(temp.getSub_code_group_value());
			List<SysCodeInfo> machinetypeList=  sysCodeInfoService.querySysCodeInfoListNoPage(subTemp);
			
			SysCodeInfo tempcode= new SysCodeInfo();
			tempcode.setCode_group_value(PmConstant.AREACODE);//区域
			List<SysCodeInfo> areaList = sysCodeInfoService.querySysCodeInfoListNoPageWithCode(tempcode);		
						
			model.put("machinespeciesList", machinespeciesList);
			model.put("infoList", machinetypeList);
			model.put("areaList", areaList);
			model.put("machineInfo", machineInfoList.get(0));			
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
	@RequestMapping("/updateMachineInfo.json")
	public @ResponseBody JSONMap<String,Object> updateMachineInfo(@FormModel("machineInfo") MachineInfo machineInfo,																
			@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			MachineInfo temp= new MachineInfo();
			temp.setMachine_nm(machineInfo.getMachine_nm());
			temp.setMachine_id(machineInfo.getMachine_id());
			List<MachineInfo> tempList=machineService.getMachineListCheckName(temp);
			if(tempList!=null&&tempList.size()!=0){
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, "name");
			}else{
				int update_flag = machineService.updateMachineInfo(machineInfo,processor);
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
	 * 设备列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryMachineListPop.json")
	public @ResponseBody JSONMap<String,Object> queryMachineListPop(@FormModel("machineInfo") MachineInfo machineInfo,																
																@FormModel("ids") String ids,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			Pagination<MachineInfo> machineInfoList = 
					machineService.getMachineListPop(machineInfo,ids,processor.getPageIndex(), processor.getPageSize());
			
			UIDataConvert<MachineInfo> convert = new UIDataConvert<MachineInfo>();
			IConvert machine_species_id = new SqlMapConvertImpl("machine_species_id", "machine_species_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.MACH_TYPE_GROUP+"'");
			convert.addConvert(machine_species_id);
			
			/*IConvert machine_type_id = new SqlMapConvertImpl("machine_type_id", "machine_type_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.MACH_SPECIES_GROUP+"'");
			convert.addConvert(machine_type_id);*/
			
			IConvert area_id = new SqlMapConvertImpl("area_id", "area_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.AREACODE+"'");
			convert.addConvert(area_id);
			
			convert.format(machineInfoList);
			
			model.put("data", machineInfoList);			
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
	 * 维修单新增页面下拉列表
	 * 
	 * @param sysCodeInfo
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryPageList.json")
	public @ResponseBody JSONMap<String, Object> queryPageList(@FormModel SysCodeInfo sysCodeInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			sysCodeInfo.setCode_group_value(PmConstant.MACH_TYPE_GROUP);//设备类别
			List<SysCodeInfo> machinespeciesList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			//sysCodeInfo.setCode_group_value(PmConstant.MACH_SPECIES_GROUP);//设备类型
			//List<SysCodeInfo> machinetypeList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			/*sysCodeInfo.setCode_group_value(PmConstant.AREACODE);//区域
			List<SysCodeInfo> areaList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
						*/
			sysCodeInfo.setCode_group_value(PmConstant.AREACODE);
			List<SysCodeInfo> areaList = sysCodeInfoService.querySysCodeInfoListNoPageWithCode(sysCodeInfo);;
						
			model.put("machinespeciesList", machinespeciesList);
			//model.put("machinetypeList", machinetypeList);
			model.put("areaList", areaList);
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
	 * 
	 * 
	 * @param sysCodeInfo
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/getSubCodeList.json")
	public @ResponseBody JSONMap<String, Object> getSubCodeList(@FormModel SysCodeInfo sysCodeInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			SysCodeInfo temp = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo).get(0);
			SysCodeInfo subTemp = new SysCodeInfo();
			subTemp.setCode_group_value(temp.getSub_code_group_value());
			List<SysCodeInfo> infoList=  sysCodeInfoService.querySysCodeInfoListNoPage(subTemp);
			
			model.put("infoList",infoList);
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
	 * 
	 * 
	 * @param sysCodeInfo
	 * @return
	 */
	@RequestMapping("/getSubCodeList2.json")
	public @ResponseBody JSONMap<String, Object> getSubCodeList2(@FormModel SysCodeInfo sysCodeInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			List<SysCodeInfo> infoList=  null;
			List<SysCodeInfo> temp = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			SysCodeInfo subTemp = new SysCodeInfo();
			if(!"".equals(sysCodeInfo.getCode_value())){
				subTemp.setCode_group_value(temp.get(0).getSub_code_group_value());
				infoList=  sysCodeInfoService.querySysCodeInfoListNoPage(subTemp);
			}
			model.put("infoList",infoList);
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
	 * 设备列表
	 * @param lmsInsOrderMst
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryMachineListNames.json")
	public @ResponseBody JSONMap<String,Object> queryMachineListNames(@FormModel("machineInfo") MachineInfo machineInfo,																
																@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();		
		try{
			Pagination<MachineInfo> machineInfoList = 
					machineService.getMachineListNames(machineInfo,processor.getPageIndex(), processor.getPageSize());
			
			UIDataConvert<MachineInfo> convert = new UIDataConvert<MachineInfo>();			
			IConvert area_id = new SqlMapConvertImpl("area_id", "area_name", "sys_code_info", "code_nm","code_value", "AND code_group_value='"+PmConstant.AREACODE+"'");
			convert.addConvert(area_id);
			convert.format(machineInfoList);	
			model.put("data", machineInfoList);			
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
	 * 维修单新增页面下拉列表
	 * 
	 * @param sysCodeInfo
	 * @param sysUserInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryPageListForPop.json")
	public @ResponseBody JSONMap<String, Object> queryPageListForPop(@FormModel("sysCodeInfo") SysCodeInfo sysCodeInfo,
			@FormModel Processor processor) {
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try {
			//设备类别List
			SysCodeInfo temp = new SysCodeInfo();
			temp.setCode_group_value(PmConstant.MACH_TYPE_GROUP);
			List<SysCodeInfo> machinespeciesList = sysCodeInfoService.querySysCodeInfoListNoPage(temp);
			
			//设备类型List
			sysCodeInfo.setCode_group_value(PmConstant.MACH_TYPE_GROUP);//设备类别
			SysCodeInfo subTemp = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo).get(0);			
			SysCodeInfo tempforsub = new SysCodeInfo();
			tempforsub.setCode_group_value(subTemp.getSub_code_group_value());
			List<SysCodeInfo> machinetypeList=  sysCodeInfoService.querySysCodeInfoListNoPage(tempforsub);
			
			//sysCodeInfo.setCode_group_value(PmConstant.MACH_SPECIES_GROUP);//设备类型
			//List<SysCodeInfo> machinetypeList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);
			/*sysCodeInfo.setCode_group_value(PmConstant.AREACODE);//区域
			List<SysCodeInfo> areaList = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo);*/
			sysCodeInfo.setCode_group_value(PmConstant.AREACODE);
			List<SysCodeInfo> areaList = sysCodeInfoService.querySysCodeInfoListNoPageWithCode(sysCodeInfo);;
						
			model.put("machinespeciesList", machinespeciesList);
			model.put("infoList", machinetypeList);
			model.put("areaList", areaList);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
}
