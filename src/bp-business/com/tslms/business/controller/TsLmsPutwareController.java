package com.tslms.business.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.bind.annotation.FormModel;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.system.Pagination;
import com.repast.core.system.Processor;
import com.tslms.business.service.TsLmsPutwareService;
import com.tslms.persistence.entity.TsLmsPutware;

@Controller
@RequestMapping("/TsLmsPutwareController")
public class TsLmsPutwareController {
	
	@Resource(name="TsLmsPutwareService")
	private TsLmsPutwareService tsLmsPutwareService;
		
	@RequestMapping("/defaultJsp")
	public String tablemanagePage()
	{
		return "tslms/putware/putware_list";
	}
	/**
	 * 查询数据
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel TsLmsPutware tsLmsPutware,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<TsLmsPutware> areaList = tsLmsPutwareService.queryTsLmsPutwareList(tsLmsPutware, processor.getPageIndex(),processor.getPageSize());
			
			model.put("data", areaList);
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
	 * 录入入库信息
	 * @param tsLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/addEntityData.json")
	public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel TsLmsPutware tsLmsPutware,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			tsLmsPutwareService.saveTsLmsPutware(tsLmsPutware);
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
	public @ResponseBody JSONMap<String,Object> beforeUpdateEntity(@FormModel TsLmsPutware tsLmsPutware,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			tsLmsPutware = tsLmsPutwareService.getTsLmsPutwareById(tsLmsPutware.getPutid());
			tsLmsPutware.setPutcount(String.valueOf((Double.parseDouble(tsLmsPutware.getPutcount())*Double.parseDouble(tsLmsPutware.getMatercount()))));
			model.put("tsLmsPutware", tsLmsPutware);
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
	 * 修改入库信息
	 * @param tsLmsPutware
	 * @param oldid
	 * @param processor
	 * @return
	 */
	@RequestMapping("/updateEntityData.json")
	public @ResponseBody JSONMap<String,Object> updateEntityData(@FormModel TsLmsPutware tsLmsPutware ,@FormModel("oldid") String oldid,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			tsLmsPutwareService.updateTsLmsPutware(tsLmsPutware);
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
	 * 删除入库信息
	 * @param tsLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteEntityData.json")
	public @ResponseBody JSONMap<String,Object> deleteEntityData(@FormModel TsLmsPutware tsLmsPutware,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			tsLmsPutwareService.deleteTsLmsPutware(tsLmsPutware);
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
