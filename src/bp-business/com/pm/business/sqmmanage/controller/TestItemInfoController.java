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
import com.pm.business.sqmmanage.service.TestItemInfoService;
import com.pm.business.sqmmanage.service.TestingProcessInfoService;
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.common.print.ProjectSummaryPrint;
import com.pm.common.print.QrCodeCreate;
import com.pm.common.print.QrCodePrint;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.Sqmmanage.entity.TestItemInfo;
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
@RequestMapping("/TestItemInfoController")
public class TestItemInfoController {
	
	@Resource
	private TestItemInfoService testItemInfoService;
	@Resource
	private SysCodeInfoService sysCodeInfoService;
    @Resource
    private MachinePotPartInfoService machinePotPartInfoService;
    
	@RequestMapping("/defaultJsp")
	public String tablemanagePage()
	{
		return "web/sqmmanage/test_item_info_list";
	}
	/**
	 * 查询检测项
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel TestItemInfo testItemInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<TestItemInfo> testItemInfoList = testItemInfoService.queryTestItemInfoList(testItemInfo, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<TestItemInfo> convert = new UIDataConvert<TestItemInfo>();
			
			//列表中部门code name转化
			IConvert dept = new SqlMapConvertImpl("userYn", "userYn_nm", "sys_code_info", "code_nm","code_value", " and code_group_value='userYn' ");
			convert.addConvert(dept);
			
			convert.format(testItemInfoList);
			model.put("data", testItemInfoList);
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
	 * 检测项查询
	 * @param TestItemInfo
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
	 * @param TestItemInfo
	 * @return
	 */
	@RequestMapping("/addEntityData.json")
	public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel TestItemInfo testItemInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			testItemInfo.setCreate_id(processor.getCurrentUserId());
			testItemInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D));
			
			//判断是否添加生产线
			if(!testItemInfoService.checkLineItemInfo(testItemInfo)){
				//查询出生产线对应的设备ID
				SysCodeInfo codeinfo = new SysCodeInfo();
				codeinfo.setCode_group_value("line");
				codeinfo.setCode_value(testItemInfo.getLineId());
				SysCodeInfo sysCodeInfo = sysCodeInfoService.getSubCodeInfo(codeinfo);
				
				//查询出检测模板中的检测项
				TestItemInfo ti = new TestItemInfo();
				ti.setLineId(testItemInfo.getLineId());
				
				List<TestItemInfo> TestItemTemplatList = testItemInfoService.queryTestItemTemplatList(ti);
				
				for (int j = 0; j < TestItemTemplatList.size(); j++) {
					TestItemInfo y = TestItemTemplatList.get(j);
					testItemInfo.setLineName(sysCodeInfo.getCode_nm());
					testItemInfo.setEquipmentId(y.getEquipmentId()); 
					testItemInfo.setEquipmentName(y.getEquipmentName()); 
					testItemInfo.setTestItem(y.getTestItem());
					testItemInfo.setTestResultNoID(y.getTestResultNoID());
					testItemInfo.setTableName(y.getTableName());
					testItemInfo.setOrderNum(y.getOrderNum());
					testItemInfo.setUserYn("Y");
					testItemInfoService.saveTestItemInfo(testItemInfo);
				}
				if(TestItemTemplatList.size()>0){
					model.put(SysConstant.OP_FLAG, true);
					model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
				}else{
					model.put(SysConstant.OP_FLAG, false);
					model.put(SysConstant.OP_MESSAGE, "产品线模板数据为空！");
				}
				
			}else{
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, "该产品线已经添加检测项，请删除后再添加！");
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
	 * @param TestItemInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beforeUpdateEntity.json")
	public @ResponseBody JSONMap<String,Object> beforeUpdateEntity(@FormModel TestItemInfo testItemInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			
			//查询生产线
			List<SysCodeInfo> lineList = sysCodeInfoService.getCodeListByGroupCode(PmConstant.LINE_PRODUNCT);//生产线
			model.put("lineList", lineList);
			
			//根据计划id获得计划实体
			TestItemInfo testItemData = testItemInfoService.getTestItemInfoById(testItemInfo.getId());
			model.put("testItemData", testItemData);
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
	 * @param TestItemInfo
	 * @return
	 */
	@RequestMapping("/updateEntityData.json")
	public @ResponseBody JSONMap<String,Object> updateEntityData(@FormModel TestItemInfo testItemInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			testItemInfo.setUpdate_id(processor.getCurrentUserId());
			testItemInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D));
			testItemInfoService.updateTestItemInfo(testItemInfo);
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
	 * @param TestItemInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/deleteEntityData.json")
	public @ResponseBody JSONMap<String,Object> deleteEntityData(@FormModel TestItemInfo testItemInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			testItemInfo.setUserYn("N");
			testItemInfoService.updateTestItemInfo(testItemInfo);
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
	 * @param TestItemInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/beginEntityData.json")
	public @ResponseBody JSONMap<String,Object> beginEntityData(@FormModel TestItemInfo testItemInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			testItemInfo.setUserYn("Y");
			testItemInfoService.updateTestItemInfo(testItemInfo);
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
