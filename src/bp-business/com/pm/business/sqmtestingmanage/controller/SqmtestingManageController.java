package com.pm.business.sqmtestingmanage.controller;

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
import com.pm.business.sqmtestingmanage.service.SqmtestingManageService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.common.print.ProjectSummaryPrint;
import com.pm.common.print.QrCodeCreate;
import com.pm.common.print.QrCodePrint;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.SvaluationManage.entity.EvaluationManageInfo;
import com.pm.persistence.machinemanager.entity.MachinePotInfo;
import com.pm.persistence.machinemanager.entity.MachinePotPartInfo;
import com.pm.persistence.sqmtestingmanage.entity.TestResult18Info;
import com.pm.persistence.sqmtestingmanage.entity.TestResult200Info;
import com.pm.persistence.sqmtestingmanage.entity.TestResult4Info;
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
@RequestMapping("/SqmtestingManageController")
public class SqmtestingManageController {
	
	@Resource
	private SqmtestingManageService sqmtestingManageService;

	@RequestMapping("/defaultJsp") 
	public String tablepage(){
		return "web/sqmtestingmanage/testing4_result";
	}	
	@RequestMapping("/monitorJsp") 
	public String monitorpage(){
		return "web/sqmtestingmanage/monitor_condition";
	}	
	
	/**
	 * 4升查询
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/query4DataList.json")
	public @ResponseBody JSONMap<String,Object> query4DataList(@FormModel TestResult4Info testResult4Info,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<TestResult4Info> testResult4InfoList = sqmtestingManageService.queryTestResult4InfoList(testResult4Info, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<TestResult4Info> convert = new UIDataConvert<TestResult4Info>();
			
			//列表中code name转化
			//IConvert evaluation_state = new SqlMapConvertImpl("evaluation_state", "evaluation_state_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='EvaluationState' ");
			//convert.addConvert(evaluation_state);
			
			
			convert.format(testResult4InfoList);
			model.put("data", testResult4InfoList);
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
	 * 18升查询
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/query18DataList.json")
	public @ResponseBody JSONMap<String,Object> query18DataList(@FormModel TestResult18Info testResult18Info,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<TestResult18Info> testResult18InfoList = sqmtestingManageService.queryTestResult18InfoList(testResult18Info, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<TestResult18Info> convert = new UIDataConvert<TestResult18Info>();
			
			//列表中code name转化
			//IConvert evaluation_state = new SqlMapConvertImpl("evaluation_state", "evaluation_state_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='EvaluationState' ");
			//convert.addConvert(evaluation_state);
			
			
			convert.format(testResult18InfoList);
			model.put("data", testResult18InfoList);
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
	 * 200升查询
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/query200DataList.json")
	public @ResponseBody JSONMap<String,Object> query200DataList(@FormModel TestResult200Info testResult200Info,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<TestResult200Info> testResult200InfoList = sqmtestingManageService.queryTestResult200InfoList(testResult200Info, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<TestResult200Info> convert = new UIDataConvert<TestResult200Info>();
			
			//列表中code name转化
			//IConvert evaluation_state = new SqlMapConvertImpl("evaluation_state", "evaluation_state_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='EvaluationState' ");
			//convert.addConvert(evaluation_state);
			
			
			convert.format(testResult200InfoList);
			model.put("data", testResult200InfoList);
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
	 * 查询灌装计划
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryFillingPlanInfoDataList.json")
	public @ResponseBody JSONMap<String,Object> queryFillingPlanInfoDataList(@FormModel FillingPlanInfo fillingPlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<FillingPlanInfo> fillingPlanInfoList = sqmtestingManageService.queryLineProductInfoList(fillingPlanInfo,processor.getPageIndex(), processor.getPageSize());
			
			model.put("data", fillingPlanInfoList);
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
