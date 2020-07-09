package com.pm.business.evaluationManage.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.pm.business.evaluationManage.service.SvaluationManageService;
import com.pm.business.machinemanager.service.MachinePotInfoService;
import com.pm.business.machinemanager.service.MachinePotPartInfoService;
import com.pm.business.sqmmanage.service.SqmmanageService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.common.print.ProjectSummaryPrint;
import com.pm.common.print.QrCodeCreate;
import com.pm.common.print.QrCodePrint;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.SvaluationManage.entity.EvaluationManageInfo;
import com.pm.persistence.machinemanager.entity.MachinePotInfo;
import com.pm.persistence.machinemanager.entity.MachinePotPartInfo;
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
@RequestMapping("/SvaluationManageController")
public class SvaluationManageController {
	
	@Resource
	private SvaluationManageService svaluationManageService;
	@Resource
	private SysCodeInfoService sysCodeInfoService;
	@Resource
	private SysUserInfoService sysUserInfoService;
    
    
	@RequestMapping("/defaultJsp")
	public String tablemanagePage()
	{
		return "web/evaluationManage/evaluation_manage_list";
	}
	

	
	/**
	 * 查询评价信息
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel EvaluationManageInfo evaluationManageInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<EvaluationManageInfo> evaluationManageInfoList = svaluationManageService.queryEvaluationManageInfoList(evaluationManageInfo, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<EvaluationManageInfo> convert = new UIDataConvert<EvaluationManageInfo>();
			
			//列表中code name转化
			IConvert evaluation_state = new SqlMapConvertImpl("evaluation_state", "evaluation_state_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='EvaluationState' ");
			convert.addConvert(evaluation_state);
			
			
			convert.format(evaluationManageInfoList);
			model.put("data", evaluationManageInfoList);
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
	 * 查询评价开始信息
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryEvaluationSaveDataList.json")
	public @ResponseBody JSONMap<String,Object> queryEvaluationSaveDataList(@FormModel EvaluationManageInfo evaluationManageInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<EvaluationManageInfo> evaluationSaveInfoList = svaluationManageService.queryEvaluationSaveInfoList(evaluationManageInfo, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<EvaluationManageInfo> convert = new UIDataConvert<EvaluationManageInfo>();
			
			//列表中code name转化
			IConvert evaluation_state = new SqlMapConvertImpl("evaluation_state", "evaluation_state_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='EvaluationState' ");
			convert.addConvert(evaluation_state);
			
			convert.format(evaluationSaveInfoList);
			model.put("data", evaluationSaveInfoList);
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
	 * 查询评价结果信息
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryEvaluationResultDataList.json")
	public @ResponseBody JSONMap<String,Object> queryEvaluationResultDataList(@FormModel EvaluationManageInfo evaluationManageInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			if(!"admin".equals(processor.getCurrentUserId()) && !"lg00064".equals(processor.getCurrentUserId())){
				evaluationManageInfo.setUser_num(processor.getCurrentUserId());
			}
			Pagination<EvaluationManageInfo> evaluationSaveInfoList = svaluationManageService.queryEvaluationSaveInfoList(evaluationManageInfo, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<EvaluationManageInfo> convert = new UIDataConvert<EvaluationManageInfo>();
			
			//列表中code name转化
			IConvert evaluation_state = new SqlMapConvertImpl("evaluation_state", "evaluation_state_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='EvaluationState' ");
			convert.addConvert(evaluation_state);
			
			convert.format(evaluationSaveInfoList);
			model.put("data", evaluationSaveInfoList);
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
	 * 查询
	 * @param LineProductInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeDataList.json")
	public @ResponseBody JSONMap<String,Object> beforeDataList(@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//查询生产线
			List<SysUserInfo> EvaluationList = sysUserInfoService.getEvaluationList();//评价人
			model.put("EvaluationList", EvaluationList);

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
	 * 生成信息
	 * @param evaluationManageInfo
	 * @return
	 */
	@RequestMapping("/updateEntityData.json")
	public @ResponseBody JSONMap<String,Object> updateEntityData(@FormModel EvaluationManageInfo evaluationManageInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			evaluationManageInfo.setCreate_id(processor.getCurrentUserId());
			evaluationManageInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D));
			
			svaluationManageService.saveEvaluationManageInfo(evaluationManageInfo);
			
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 结束信息
	 * @param evaluationManageInfo
	 * @return
	 */
	@RequestMapping("/updateEndEntityData.json")
	public @ResponseBody JSONMap<String,Object> updateEndEntityData(@FormModel EvaluationManageInfo evaluationManageInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			evaluationManageInfo.setUpdate_id(processor.getCurrentUserId());
			evaluationManageInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D));
			
			svaluationManageService.saveEndEvaluationManageInfo(evaluationManageInfo);
			
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * checkdata
	 * @param evaluationManageInfo
	 * @return
	 */
	@RequestMapping("/checkListValueData.json")
	public @ResponseBody JSONMap<String,Object> checkListValueData(@FormModel EvaluationManageInfo evaluationManageInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			String OP_FLAG = "";
			
			List<EvaluationManageInfo> EvaluationList = svaluationManageService.checkListValueData(evaluationManageInfo);//评价人
			if(EvaluationList.size()>0){
				
				OP_FLAG = "have";
				
				if(EvaluationList.get(0).getEvaluation_state().equals("2")){
					OP_FLAG = "end";
				}
			}
			
			model.put(SysConstant.OP_FLAG, OP_FLAG);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	final static ObjectMapper mapper = new ObjectMapper(); 
	/**
	 * 保存信息
	 * @param evaluationManageInfo
	 * @return
	 */
	@RequestMapping("/saveTemporaryEntityData.json")
	public @ResponseBody JSONMap<String,Object> saveTemporaryEntityData(@FormModel EvaluationManageInfo evaluationManageInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			 
		      //实例化Gson
		      Gson gson = new Gson();
		      //把json序列化为List对象
		      List<EvaluationManageInfo> evaList = gson.fromJson(evaluationManageInfo.getJson_info(), new TypeToken<List<EvaluationManageInfo>>() {}.getType());
		      for (int i = 0; i < evaList.size(); i++) {
		    	  EvaluationManageInfo s = (EvaluationManageInfo)evaList.get(i);
		    	  
		    	  svaluationManageService.saveTemporaryEntityData(s,processor);
		      }

			 model.put(SysConstant.OP_FLAG, true);
			 model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}

}
