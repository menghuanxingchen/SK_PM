package com.pm.business.scrap.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.scrap.service.ScrapService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.persistence.scrap.entity.ScrapInfo;
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
@RequestMapping("/ScrapController")
public class ScrapController {
	
	@Resource
	private ScrapService scrapService;
	@Resource
	private SysCodeInfoService sysCodeInfoService;
	@Resource
	private SysUserInfoService sysUserInfoService;
	
	@RequestMapping("/defaultJsp")
	public String tablemanagePage()
	{
		return "web/scrapinfo/scrapinfo_list";
	}
	
	/**
	 * 列表页-查询条件初始化
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeDataList.json")
	public @ResponseBody JSONMap<String,Object> beforeDataList(@FormModel("sysCodeInfo") SysCodeInfo sysCodeInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//设备类别List
			SysCodeInfo temp = new SysCodeInfo();
			temp.setCode_group_value(PmConstant.MACH_TYPE_GROUP);
			List<SysCodeInfo> machinespeciesList = sysCodeInfoService.querySysCodeInfoListNoPage(temp);
			model.put("machinespeciesList", machinespeciesList);
			//设备类型List
			sysCodeInfo.setCode_group_value(PmConstant.MACH_TYPE_GROUP);//设备类别
			SysCodeInfo subTemp = sysCodeInfoService.querySysCodeInfoListNoPage(sysCodeInfo).get(0);			
			SysCodeInfo tempforsub = new SysCodeInfo();
			tempforsub.setCode_group_value(subTemp.getSub_code_group_value());
			List<SysCodeInfo> machinetypeList=  sysCodeInfoService.querySysCodeInfoListNoPage(tempforsub);
			model.put("machinetypeList", machinetypeList);
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
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel ScrapInfo scrapInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<ScrapInfo> scrapInfoInfoList = scrapService.queryScrapInfoList(scrapInfo,  processor.getPageIndex(),processor.getPageSize());
			UIDataConvert<ScrapInfo> convert = new UIDataConvert<ScrapInfo>();
			IConvert scrapMachine = new SqlMapConvertImpl("scrap_machine_id", "scrap_machine_nm", "machine_info", "machine_nm","machine_id", " and 1 = 1 ");
			convert.addConvert(scrapMachine);
			IConvert mainMachineYn = new SqlMapConvertImpl("main_machine_yn", "main_machine_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.STATE_YN+"' ");
			convert.addConvert(mainMachineYn);
			IConvert spareYn = new SqlMapConvertImpl("spare_yn", "spare_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='"+PmConstant.STATE_YN+"' ");
			convert.addConvert(spareYn);
			convert.format(scrapInfoInfoList);
			model.put("data", scrapInfoInfoList);
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
			//查询设备类别下拉列表
			List<SysCodeInfo> stateYn = sysCodeInfoService.getCodeListByGroupCode(PmConstant.STATE_YN);
			model.put("stateYn", stateYn);
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
	 * 新增页面跳转
	 * @param processor
	 * @return
	 */
	@RequestMapping("/addEntityForward")
	public String bigAddEntityForward() {
		return "web/scrapinfo/scrapinfo_add";
	}
	
	/**
	 * 录入信息
	 * @param processor
	 * @return
	 */
	@RequestMapping("/addEntityData.json")
	public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel ScrapInfo scrapInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			scrapInfo.setApply_userid(processor.getCurrentUserId());
			scrapInfo.setCreate_id(processor.getCurrentUserId());
			scrapInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			scrapInfo.setUse_yn(PmConstant.EXPENSES_Y);
			scrapService.saveScrapInfo(scrapInfo);
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
	 * 修改信息前获取信息
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeUpdateEntity.json")
	public JSONMap<String,Object> beforeUpdateEntity(@FormModel ScrapInfo scrapInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			List<SysCodeInfo> stateYn = sysCodeInfoService.getCodeListByGroupCode(PmConstant.STATE_YN);
			scrapInfo = scrapService.getScrapInfoByScrapId(scrapInfo.getScrap_id());
			scrapInfo.setStateYnList(stateYn);
			model.put("scrapInfo", scrapInfo);
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
	public JSONMap<String,Object> updateEntityData(@FormModel ScrapInfo scrapInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			scrapInfo.setUpdate_id(processor.getCurrentUserId());
			scrapInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			scrapService.updateScrapInfo(scrapInfo);
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
        }
       return "web/scrapinfo/scrapinfo_update";
	}
	/**
	 * 删除信息
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteEntityData.json")
	public @ResponseBody JSONMap<String,Object> deleteEntityData(@FormModel ScrapInfo scrapInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			scrapInfo.setUpdate_id(processor.getCurrentUserId());
			scrapInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));
			scrapInfo.setUse_yn(PmConstant.EXPENSES_N);
			scrapService.updateScrapInfo(scrapInfo);
			//scrapService.deleteScrapInfo(scrapInfo);
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
