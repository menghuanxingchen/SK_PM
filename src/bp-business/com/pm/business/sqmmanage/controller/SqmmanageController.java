package com.pm.business.sqmmanage.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.IdentityHashMap;
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
import com.pm.business.sysinfo.service.SysCodeInfoService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.common.print.ProjectSummaryPrint;
import com.pm.common.print.QrCodeCreate;
import com.pm.common.print.QrCodePrint;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
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
import com.repast.core.util.HttpClientUtil;
import com.repast.persistence.entity.SysUserInfo;
import com.sqm.SqmInterface.service.SqmInterfaceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/SqmmanageController")
public class SqmmanageController {
	
	@Resource
	private SqmmanageService sqmmanageService;
	@Resource
	private SysCodeInfoService sysCodeInfoService;
    @Resource
    private MachinePotPartInfoService machinePotPartInfoService;
    
    @Resource
	private SqmInterfaceService sqmInterfaceService;
    
	@RequestMapping("/defaultJsp")
	public String tablemanagePage()
	{
		return "web/sqmmanage/productline_list";
	}
	/**
	 * 按照生产线和产品名称
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDataList(@FormModel LineProductInfo lineProductInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<LineProductInfo> LineProductInfoList = sqmmanageService.queryLineProductInfoList(lineProductInfo, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<LineProductInfo> convert = new UIDataConvert<LineProductInfo>();
			
			convert.format(LineProductInfoList);
			model.put("data", LineProductInfoList);
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
	@RequestMapping("/queryFillPlanDataList.json")
	public @ResponseBody JSONMap<String,Object> queryFillPlanDataList(@FormModel FillingPlanInfo fillingPlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			Pagination<FillingPlanInfo> fillingPlanInfoList = sqmmanageService.queryFillingPlanInfoList(fillingPlanInfo, processor.getPageIndex(), processor.getPageSize());
			UIDataConvert<FillingPlanInfo> convert = new UIDataConvert<FillingPlanInfo>();
			
			convert.format(fillingPlanInfoList);
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
	
	/**
	 * 获取密度
	 * @param LineProductInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/getfillplandata.json")
	public @ResponseBody JSONMap<String,Object> getfillplandata(@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			
			String url="http://114.113.147.110:8899/api/SqmService/GetChargeData";
			
			String NowDate = DateUtil.getNow(DateUtil.YMD);
			
			HttpClientUtil getDataJson = new HttpClientUtil();
			
			//参数
			 Map<String, String> map = new IdentityHashMap<String, String>();
			 map.put("PlanDate", NowDate);
			 map.put("LineID", "");
			 map.put("Code", "");
			 map.put("pname", "");
			 
			 //获取接口json
			String  DataJson= getDataJson.doGet(url,map);
			String NewDataJson="";
			
			if(DataJson.length()>0){
				NewDataJson = DataJson.replace("\"[", "[").replace("]\"", "]").replaceAll("\\\\","");
			}
			 
			//遍历赋值
			JSONArray jsonArray = JSONArray.fromObject(NewDataJson);
			List<FillingPlanInfo> list = new ArrayList<>();
			for(int i = 0;i<jsonArray.size();i++) {
				JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
				FillingPlanInfo user = (FillingPlanInfo) JSONObject.toBean(jsonObject, FillingPlanInfo.class);
				list.add(user);
			}
			
			for (FillingPlanInfo fillingPlanInfo : list) {
				fillingPlanInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));//当前时间
				sqmInterfaceService.saveDensityInfo(fillingPlanInfo);
			}
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
			
			if("".equals(machinePotPartInfo.getPot_id()) || null == machinePotPartInfo.getPot_id()){
				machinePotPartInfo.setPot_id("38");
			}else{
				SysCodeInfo s = new SysCodeInfo();
				s.setCode_group_value("line");
				s.setCode_value(machinePotPartInfo.getPot_id());
				SysCodeInfo getSubCodeInfo = sysCodeInfoService.getSubCodeInfo(s);
				
				machinePotPartInfo.setPot_id(getSubCodeInfo.getSub_code_group_value());
			}
			machinePotPartInfo.setSqmMark("SQM");
			 //列表查询（分页）
            Pagination<MachinePotPartInfo> dataList = machinePotPartInfoService.queryMachinePotPartInfoList(machinePotPartInfo, processor.getPageIndex(), processor.getPageSize());
            UIDataConvert<MachinePotPartInfo> convert = new UIDataConvert<MachinePotPartInfo>();
			
            IConvert dept = new SqlMapConvertImpl("pot_id", "pot_nm", "sys_code_info", "code_nm","sub_code_group_value", " and code_group_value='line' ");
			convert.addConvert(dept);
            
			convert.format(dataList);
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
	public @ResponseBody JSONMap<String,Object> addEntityData(@FormModel LineProductInfo lineProductInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			lineProductInfo.setCreate_id(processor.getCurrentUserId());
			lineProductInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D));
			
			//查询出生产线对应的设备ID
			SysCodeInfo codeinfo = new SysCodeInfo();
			codeinfo.setCode_group_value("line");
			codeinfo.setCode_value(lineProductInfo.getLineId());
			SysCodeInfo sysCodeInfo = sysCodeInfoService.getSubCodeInfo(codeinfo);
			
			//判断是否添加生产线
			if(!sqmmanageService.checkLineProductInfo(lineProductInfo.getLineId())){
				//code表中Sub_code_group_value存放pm系统设备id
				lineProductInfo.setPot_id(sysCodeInfo.getSub_code_group_value());
				sqmmanageService.saveLineProductInfo(lineProductInfo);
				model.put(SysConstant.OP_FLAG, true);
				model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
			}else{
				model.put(SysConstant.OP_FLAG, false);
				model.put(SysConstant.OP_MESSAGE, "该生产线已经添加！");
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
			LineProductInfo lineProductInfoEo = sqmmanageService.getLineProductInfoById(lineProductInfo.getId());
			model.put("lineProductInfoEo", lineProductInfoEo);
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
	public @ResponseBody JSONMap<String,Object> updateEntityData(@FormModel LineProductInfo lineProductInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			lineProductInfo.setUpdate_id(processor.getCurrentUserId());
			lineProductInfo.setUpdate_time(DateUtil.getNow(DateUtil.Y_M_D));
			
			//查询出生产线对应的设备ID
			SysCodeInfo codeinfo = new SysCodeInfo();
			codeinfo.setCode_group_value("line");
			codeinfo.setCode_value(lineProductInfo.getLineId());
			SysCodeInfo sysCodeInfo = sysCodeInfoService.getSubCodeName(codeinfo);
			
			//code表中Sub_code_group_value存放pm系统设备id
			lineProductInfo.setPot_id(sysCodeInfo.getSub_code_group_value());
			
			sqmmanageService.updateLineProductInfo(lineProductInfo);
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
	public @ResponseBody JSONMap<String,Object> deleteEntityData(@FormModel LineProductInfo lineProductInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			sqmmanageService.deleteLineProductInfo(lineProductInfo);
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
	 * 取消检测
	 * @param LineProductInfo
	 * @param processor
	 * @return
	 */
	@RequestMapping("/quxiaoDataFun.json")
	public @ResponseBody JSONMap<String,Object> quxiaoDataFun(@FormModel FillingPlanInfo fillingPlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			//更改为未进行状态
			sqmmanageService.quxiaoDataFun(fillingPlanInfo);
			//删除检测test_over_yn
			sqmmanageService.deleteFillingPlanInfo(fillingPlanInfo);
			//删除test_result
			sqmmanageService.deleteTestResult(fillingPlanInfo);
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
	 * 打印
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/printqrCodeBatch.json")
	public @ResponseBody JSONMap<String,Object> printqrCodeBatch(@FormModel MachinePotPartInfo machinePotPartInfo,@FormModel("lineName") String lineName,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			String content = "";//二维码内容
        
			  //列表查询
			List<MachinePotPartInfo> dataList = machinePotPartInfoService.queryMachinePotPartInfoList(machinePotPartInfo);
			
            for (int i = 0; i < dataList.size(); i++) {
            	MachinePotPartInfo s = dataList.get(i);
            	
            	content = s.getPot_part_id();
            	String timeStap = String.valueOf(System.currentTimeMillis());
            	//生成二维码
     	    	QrCodeCreate.getQRCode(content, "设备名称："+lineName+"_"+s.getPot_part_nm(),new File("F:\\Program Files (x86)\\PMpicture\\"+timeStap+".jpg"),200,200);
     	        
     	       System.out.println("输出成功.");

     	      new QrCodePrint("F:\\Program Files (x86)\\PMpicture\\"+timeStap+".jpg").ePrint();
     	       //qrCodePrint.JPGPrint(new File(),"ZDesigner ZT410-300dpi ZPL (副本 1)");
            }
            
            if(dataList.size()<=0){
            	model.put(SysConstant.OP_FLAG, false);
    			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
            }else{
            	model.put(SysConstant.OP_FLAG, true);
    			model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
            }
			
		}catch(Exception e){
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}

}
