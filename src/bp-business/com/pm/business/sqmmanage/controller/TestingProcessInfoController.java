package com.pm.business.sqmmanage.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.pm.business.machinemanager.service.MachinePotInfoService;
import com.pm.business.machinemanager.service.MachinePotPartInfoService;
import com.pm.business.sqmmanage.service.SqmmanageService;
import com.pm.business.sqmmanage.service.TestingProcessInfoService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.common.print.ProjectSummaryPrint;
import com.pm.common.print.QrCodeCreate;
import com.pm.common.print.QrCodePrint;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.Sqmmanage.entity.TestingProcessInfo;
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
@RequestMapping("/TestingProcessInfoController")
public class TestingProcessInfoController {
	
	@Resource
	private TestingProcessInfoService testingProcessInfoService;
	@Resource
	private SysCodeInfoService sysCodeInfoService;
    @Resource
    private MachinePotPartInfoService machinePotPartInfoService;
    
	@RequestMapping("/defaultJsp")
	public String tablemanagePage()
	{
		return "web/sqmmanage/testing_process_info_list";
	}
	/**
	 * 按照生产线和产品名称
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel TestingProcessInfo testingProcessInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<TestingProcessInfo> testingProcessInfoList = testingProcessInfoService.queryTestingProcessInfoList(testingProcessInfo, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<TestingProcessInfo> convert = new UIDataConvert<TestingProcessInfo>();
			
			convert.format(testingProcessInfoList);
			model.put("data", testingProcessInfoList);
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
	 * 按照生产线和产品名称
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryProductDataList.json")
	public @ResponseBody JSONMap<String,Object> queryProductDataList(@FormModel MachinePotPartInfo machinePotPartInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			
			 //列表查询（分页）
            Pagination<MachinePotPartInfo> dataList = machinePotPartInfoService.queryMachinePotPartInfoList(machinePotPartInfo, processor.getPageIndex(), processor.getPageSize());

			model.put("data", dataList);
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
	 * 生产线信息查询
	 * @param LineProductInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeDataList.json")
	public @ResponseBody JSONMap<String,Object> beforeDataList(@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//查询生产线
			List<SysCodeInfo> lineList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.LINE_PRODUNCT);//生产线
			model.put("lineList", lineList);
			
			
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
	 * @param LineProductInfo
	 * @return
	 */
	@RequestMapping("/addEntityData.json")
	public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel TestingProcessInfo testingProcessInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			testingProcessInfo.setCreate_id(processor.getCurrentUserId());
			testingProcessInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D));
			
			//判断是否添加生产线
			if(!testingProcessInfoService.checkTestingProcessInfo(testingProcessInfo)){
				
			
				//查询出生产线对应的设备ID
				SysCodeInfo codeinfo = new SysCodeInfo();
				codeinfo.setCode_group_value("line");
				codeinfo.setCode_value(testingProcessInfo.getLineId());
				SysCodeInfo sysCodeInfo = sysCodeInfoService.getSubCodeInfo(codeinfo);
				
				//利用设备ID查询出属于sqm的子部件列表
				/*if(sysCodeInfo.getSub_code_group_value()!=null){
					MachinePotPartInfo MachinePotPart = new MachinePotPartInfo();
					MachinePotPart.setPot_id(sysCodeInfo.getSub_code_group_value());
					MachinePotPart.setSqmMark("SQM");
					List<MachinePotPartInfo> machinePotPartInfoList = machinePotPartInfoService.queryMachinePotPartInfobySQMList(MachinePotPart);
					for (int i = 0; i < machinePotPartInfoList.size(); i++) {
						MachinePotPartInfo s = machinePotPartInfoList.get(i);
						testingProcessInfo.setLineName(sysCodeInfo.getCode_nm());
						testingProcessInfo.setEquipmentId(s.getPot_part_id()); 
						testingProcessInfo.setEquipmentName(s.getPot_part_nm()); 
						testingProcessInfo.setOrderNum(s.getSqmOrderNum());
						testingProcessInfo.setUseYn("Y");
						testingProcessInfoService.saveTestingProcessInfo(testingProcessInfo);
					}
		
				}else{
					model.put(SysConstant.OP_FLAG, false);
					model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
				}*/
				
				//变更为从检测流程表中查询设备，由于多个生产线共用一个设备修改
				if(sysCodeInfo.getSub_code_group_value()!=null){
			
					TestingProcessInfo testProInfo=new TestingProcessInfo();
					testProInfo.setLineId(testingProcessInfo.getLineId());
					List<TestingProcessInfo> queryTestingProcessInfoList = testingProcessInfoService.queryTestingProcessTempInfoList(testProInfo);
					for (int i = 0; i < queryTestingProcessInfoList.size(); i++) {
						TestingProcessInfo s = queryTestingProcessInfoList.get(i);
						testingProcessInfo.setLineName(s.getLineName());
						testingProcessInfo.setEquipmentId(s.getEquipmentId()); 
						testingProcessInfo.setEquipmentName(s.getEquipmentName()); 
						testingProcessInfo.setOrderNum(s.getOrderNum());
						testingProcessInfo.setUseYn("Y");
						testingProcessInfoService.saveTestingProcessInfo(testingProcessInfo);
					}
				
				}else{
					model.put(SysConstant.OP_FLAG, false);
					model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
				}
				
				
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			}else{
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, "该生产线已添加设备，请删除后再添加!");
			}
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.FAIL);
			System.out.println(e.getMessage());
		}
		return model;
	}
	
	/**
	 * 修改信息前获取信息
	 * @param LineProductInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeUpdateEntity.json")
	public @ResponseBody JSONMap<String,Object> beforeUpdateEntity(@FormModel LineProductInfo lineProductInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			
			//查询生产线
			List<SysCodeInfo> lineList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.LINE_PRODUNCT);//生产线
			model.put("lineList", lineList);
			
			//根据计划id获得计划实体
			TestingProcessInfo testingProcessInfo = testingProcessInfoService.getTestingProcessInfoById(lineProductInfo.getId());
			model.put("testingProcessInfo", testingProcessInfo);
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
	 * @param LineProductInfo
	 * @return
	 */
	@RequestMapping("/updateEntityData.json")
	public @ResponseBody JSONMap<String,Object> updateEntityData(@FormModel TestingProcessInfo testingProcessInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			testingProcessInfo.setUpdate_id(processor.getCurrentUserId());
			testingProcessInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D));
			testingProcessInfoService.updateTestingProcessInfo(testingProcessInfo);
			model.put(SysConstant.OP_FLAG, true);
			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		}catch(Exception e){
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
	
	/**
	 * 删除
	 * @param LineProductInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteEntityData.json")
	public @ResponseBody JSONMap<String,Object> deleteEntityData(@FormModel TestingProcessInfo testingProcessInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			testingProcessInfo.setUseYn("N");
			testingProcessInfoService.updateTestingProcessInfo(testingProcessInfo);
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
	 * 启用
	 * @param LineProductInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beginEntityData.json")
	public @ResponseBody JSONMap<String,Object> beginEntityData(@FormModel TestingProcessInfo testingProcessInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			testingProcessInfo.setUseYn("Y");
			testingProcessInfoService.updateTestingProcessInfo(testingProcessInfo);
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
