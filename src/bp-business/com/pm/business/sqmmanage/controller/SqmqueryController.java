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
import com.pm.persistence.Sqmmanage.entity.DocumentInfo;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.pm.persistence.Sqmmanage.entity.LineProductInfo;
import com.pm.persistence.Sqmmanage.entity.ProductionPlan;
import com.pm.persistence.Sqmmanage.entity.ProductionPractica;
import com.pm.persistence.Sqmmanage.entity.SampleInfo;
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
import com.repast.core.util.HttpClientUtil;
import com.repast.persistence.entity.SysUserInfo;
import com.sqm.SqmInterface.service.SqmInterfaceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/SqmqueryController")
public class SqmqueryController {
	
	@Resource
	private SqmInterfaceService sqmInterfaceService;
	

	@RequestMapping("/defaultJsp") 
	public String tablepage(){
		return "web/sqmquery/production_plan";
	}	
	
	@RequestMapping("/PracticalJsp") 
	public String Practicalpage(){
		return "web/sqmquery/production_practical";
	}
	
	@RequestMapping("/MproductionplanJsp") 
	public String Mproductionplanpage(){
		return "web/sqmquery/m_production_plan";
	}	
	
	@RequestMapping("/MPracticalJsp") 
	public String MPracticalJsp(){
		return "web/sqmquery/m_production_practical";
	}
	
	@RequestMapping("/MSampleManagerJsp") 
	public String MSampleManagerJsp(){
		return "web/sqmquery/m_sample_manager";
	}
	
	@RequestMapping("/SampleManagerJsp") 
	public String SampleManagerpage(){
		return "web/sqmquery/sample_manager";
	}
	
	
	@RequestMapping("/DocumentManagerJsp") 
	public String DocumentManagerpage(){
		return "web/sqmquery/document_manager";
	}
	/**
	 * 生产计划查询
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryProductionDataList.json")
	public @ResponseBody JSONMap<String,Object> queryProductionDataList(@FormModel ProductionPlan productionPlan,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			
			String url="http://114.113.147.110:8899/api/SqmService/GetProductData";
			
			String NowDate = DateUtil.getNow(DateUtil.YMD);
			
			HttpClientUtil getDataJson = new HttpClientUtil();
			
			//参数
			 Map<String, String> map = new IdentityHashMap<String, String>();
			 map.put("PlanDateFrom", productionPlan.getStartdate().replace("-", ""));
			 map.put("PlanDateTo", productionPlan.getEnddate().replace("-", ""));
			 
			 
			 //获取接口json
			String  DataJson= getDataJson.doGet(url,map);
			String NewDataJson="";
			List<ProductionPlan> list = new ArrayList<>();
			
			Pagination<ProductionPlan> pageProductionPlan = new Pagination<ProductionPlan>();
			pageProductionPlan.setPageIndex(1);
			pageProductionPlan.setPageMaxSize(5000);
			pageProductionPlan.setPageSize(5000);
			pageProductionPlan.setRecords(list);
			pageProductionPlan.setTotalCount(new Long(list.size()));
			
			//默认数据为"[" 为空，所以是3个字符
			if(DataJson.length()>2){
				NewDataJson = DataJson.replace("\"[", "[").replace("]\"", "]").replaceAll("\\\\","");
				//遍历赋值
				JSONArray jsonArray = JSONArray.fromObject(NewDataJson);
				
				String dateStr = "";
				int datetype = 0;
				for(int i = 0;i<jsonArray.size();i++) {
					JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
					ProductionPlan productionPlanInfo = (ProductionPlan) JSONObject.toBean(jsonObject, ProductionPlan.class);
					if(i == 0){
						productionPlanInfo.setDateType("0");
					}else{
						if(dateStr.equals(productionPlanInfo.getCNFYMD())){
							productionPlanInfo.setDateType(datetype+"");
							
							
						}else{
							datetype++;
							productionPlanInfo.setDateType(datetype+"");
						}
					}
					//添加标识，日期相同的标识一致，区分颜色
					dateStr = productionPlanInfo.getCNFYMD();
					
					
					list.add(productionPlanInfo);
				}

				pageProductionPlan.setRecords(list);
				pageProductionPlan.setTotalCount(new Long(list.size()));

			}
			model.put("data", pageProductionPlan);
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
	 * 生产实际查询
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryPracticalDataList.json")
	public @ResponseBody JSONMap<String,Object> queryPracticalDataList(@FormModel ProductionPractica productionPractica,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			
			
			String theadUrl="http://114.113.147.110:8899/api/SqmService/GetChargeLiter";
			
			String url="http://114.113.147.110:8899/api/SqmService/GetChargeData2";
			
			String NowDate = DateUtil.getNow(DateUtil.YMD);
			
			HttpClientUtil getDataJson = new HttpClientUtil();
			
			HttpClientUtil getTheadDataJson = new HttpClientUtil();
			
			//参数
			 Map<String, String> map = new IdentityHashMap<String, String>();
			 
			 if(!productionPractica.getStartdate().equals("")){
				 map.put("ymd", productionPractica.getStartdate().replace("-", ""));
			 }else{
				 map.put("ymd", NowDate);
			 }
			 
			 
			//获取接口json
			String TheadDataJson= getTheadDataJson.doGet(theadUrl,map);
			String TheadNewDataJson="";
			List<ProductionPractica> TheadPracticaList = new ArrayList<>();
			
		
			
			//默认数据为"[" 为空，所以是3个字符
			if(TheadDataJson.length()>2){
				TheadNewDataJson = TheadDataJson.replace("\"[", "[").replace("]\"", "]").replaceAll("\\\\","");
				//遍历赋值
				JSONArray jsonArray = JSONArray.fromObject(TheadNewDataJson);
				
				for(int i = 0;i<jsonArray.size();i++) {
					JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
					ProductionPractica TheadInfo = (ProductionPractica) JSONObject.toBean(jsonObject, ProductionPractica.class);
					TheadPracticaList.add(TheadInfo);
				}
			}
			
			model.put("theadPracticaList", TheadPracticaList);
			 

			 //获取接口json
			String  DataJson= getDataJson.doGet(url,map);
			String NewDataJson="";
			List<ProductionPractica> productionPracticaList = new ArrayList<>();
			
		
			
			//默认数据为"[" 为空，所以是3个字符
			if(DataJson.length()>2){
				NewDataJson = DataJson.replace("\"[", "[").replace("]\"", "]").replaceAll("\\\\","");
				//遍历赋值
				JSONArray jsonArray = JSONArray.fromObject(NewDataJson);
				
				for(int i = 0;i<jsonArray.size();i++) {
					JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
					ProductionPractica productionPracticaInfo = (ProductionPractica) JSONObject.toBean(jsonObject, ProductionPractica.class);
					productionPracticaList.add(productionPracticaInfo);
				}
			}
			
			model.put("productionPracticaList", productionPracticaList);
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
	 * 样本信息
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/querySampleManagerDataList.json")
	public @ResponseBody JSONMap<String,Object> querySampleManagerDataList(@FormModel SampleInfo sampleInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			
			String url="http://114.113.147.110:8899/api/SqmService/GetSampleData";
			
			String NowDate = DateUtil.getNow(DateUtil.YMD);
			
			HttpClientUtil getDataJson = new HttpClientUtil();
			
			//参数
			 Map<String, String> map = new IdentityHashMap<String, String>();
			 if(sampleInfo.getIsNew().equals("true")){
				 map.put("IsNew", "1");
			 }else{
				 map.put("IsNew", "0");
			 }
			 
			 if(sampleInfo.getIsComplete().equals("true")){
				 map.put("IsComplete", "1");
			 }else{
				 map.put("IsComplete", "0");
			 }
			 
			 map.put("pkg", sampleInfo.getPKG());
			 map.put("prod", sampleInfo.getProd());
			 map.put("strStan", sampleInfo.getStrStan());
			 map.put("pSort", sampleInfo.getPSort());
			
			 
			 
			 //获取接口json
			String  DataJson= getDataJson.doGet(url,map);
			String NewDataJson="";
			List<SampleInfo> list = new ArrayList<>();
			
			//默认数据为"[" 为空，所以是3个字符
			if(DataJson.length()>2){
				NewDataJson = DataJson.replace("\"[", "[").replace("]\"", "]").replaceAll("\\\\","");
				//遍历赋值
				JSONArray jsonArray = JSONArray.fromObject(NewDataJson);
				
				for(int i = 0;i<jsonArray.size();i++) {
					JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
					SampleInfo sampleInfoData = (SampleInfo) JSONObject.toBean(jsonObject, SampleInfo.class);
					list.add(sampleInfoData);
				}
			}
			model.put("data", list);
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
	 * 样本信息详细
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/querySampleDetailDataList.json")
	public @ResponseBody JSONMap<String,Object> querySampleDetailDataList(@FormModel SampleInfo sampleInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			
			String url="http://114.113.147.110:8899/api/SqmService/GetSampleData2";
			
			String NowDate = DateUtil.getNow(DateUtil.YMD);
			
			HttpClientUtil getDataJson = new HttpClientUtil();
			
			//参数
			 Map<String, String> map = new IdentityHashMap<String, String>();
			
			 map.put("pkg", sampleInfo.getPKG());
			 map.put("prod", sampleInfo.getProd());
			 map.put("colcnt", sampleInfo.getColcnt());
			 map.put("revno", sampleInfo.getREVNO());
			
			 
			 
			 //获取接口json
			String  DataJson= getDataJson.doGet(url,map);
			String NewDataJson="";
			List<SampleInfo> list = new ArrayList<>();
			
			//默认数据为"[" 为空，所以是3个字符
			if(DataJson.length()>2){
				NewDataJson = DataJson.replace("\"[", "[").replace("]\"", "]").replaceAll("\\\\","");;
				//遍历赋值
				JSONArray jsonArray = JSONArray.fromObject(NewDataJson);
				
				for(int i = 0;i<jsonArray.size();i++) {
					JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
					SampleInfo sampleInfoData = (SampleInfo) JSONObject.toBean(jsonObject, SampleInfo.class);
					sampleInfoData.setSAMPLE1(sampleInfoData.getSAMPLE1().replaceAll("#","/"));
					sampleInfoData.setSAMPLE2(sampleInfoData.getSAMPLE2().replaceAll("#","/"));
					sampleInfoData.setSAMPLE3(sampleInfoData.getSAMPLE3().replaceAll("#","/"));
					sampleInfoData.setSAMPLE4(sampleInfoData.getSAMPLE4().replaceAll("#","/"));
					list.add(sampleInfoData);
				}
			}
			model.put("data", list);
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
	 * 文档管理
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDocumentDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDocumentDataList(@FormModel DocumentInfo documentInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			
			String url="http://114.113.147.110:8899/api/SqmService/GetDocInfo";
			
			
			
			HttpClientUtil getDataJson = new HttpClientUtil();
			
			//参数
			 Map<String, String> map = new IdentityHashMap<String, String>();

			 //获取接口json
			String  DataJson= getDataJson.doGet(url,map);
			String NewDataJson="";
			List<DocumentInfo> list = new ArrayList<>();
			
			//默认数据为"[" 为空，所以是3个字符
			if(DataJson.length()>2){
				NewDataJson = DataJson.replace("\"[", "[").replace("]\"", "]").replaceAll("\\\\","");
				//遍历赋值
				JSONArray jsonArray = JSONArray.fromObject(NewDataJson);
				
				for(int i = 0;i<jsonArray.size();i++) {
					JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
					DocumentInfo documentInfoData = (DocumentInfo) JSONObject.toBean(jsonObject, DocumentInfo.class);
					list.add(documentInfoData);
				}
			}
			model.put("TreeData", list);
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
	 * 文档管理
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/queryDocumentTableDataList.json")
	public @ResponseBody JSONMap<String,Object> queryDocumentTableDataList(@FormModel DocumentInfo documentInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		try{
			
			String url="http://114.113.147.110:8899/api/SqmService/GetDocInfoByTreeNote";
			
			
			
			HttpClientUtil getDataJson = new HttpClientUtil();
			
			//参数
			 Map<String, String> map = new IdentityHashMap<String, String>();
			 map.put("Note", documentInfo.getNOTE());
			 
			 //获取接口json
			String  DataJson= getDataJson.doGet(url,map);
			String NewDataJson="";
			List<DocumentInfo> list = new ArrayList<>();
			
			//默认数据为"[" 为空，所以是3个字符
			if(DataJson.length()>2){
				NewDataJson = DataJson.replace("\"[", "[").replace("]\"", "]").replaceAll("\\\\","");
				//遍历赋值
				JSONArray jsonArray = JSONArray.fromObject(NewDataJson);
				
				for(int i = 0;i<jsonArray.size();i++) {
					JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
					DocumentInfo documentInfoData = (DocumentInfo) JSONObject.toBean(jsonObject, DocumentInfo.class);
					list.add(documentInfoData);
				}
			}
			model.put("TreeListData", list);
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
	 * 回写
	 * @param sLmsPutware
	 * @param processor
	 * @return
	 */
	@RequestMapping("/backWrightDataList.json")
	public @ResponseBody JSONMap<String,Object> backWrightDataList(@FormModel FillingPlanInfo fillingPlanInfo,@FormModel Processor processor) {
		JSONMap<String,Object> model = new JSONMap<String,Object>();
		
		
		try{
			String url="http://114.113.147.110:8899/api/SqmService/GetUpdateChargeDate";
			
			String NowDate = DateUtil.getNow(DateUtil.YMD);
			
			HttpClientUtil getDataJson = new HttpClientUtil();
			
			FillingPlanInfo bfillingPlanInfo = new FillingPlanInfo();
			bfillingPlanInfo.setCNFYMD(NowDate);
			List<FillingPlanInfo> fillingPlanList =sqmInterfaceService.queryFillingPlanDataList(bfillingPlanInfo);
			for(int i = 0;i<fillingPlanList.size();i++) {
				FillingPlanInfo f = (FillingPlanInfo)fillingPlanList.get(i);
				
				//参数
				 Map<String, String> map = new IdentityHashMap<String, String>();
				 map.put("Date", f.getCNFYMD());
				 map.put("Vsl", f.getVSL());
				 map.put("Turn", f.getTURN());
				 map.put("Pkg", f.getPKG());
				 map.put("Prod", f.getPROD());
				 map.put("Lotno", f.getLOTNO());
				 map.put("StartDate", f.getFILL_STDATE());
				 map.put("EndDate", f.getFILL_EDDATE());
				 
				String tt = getDataJson.doGet(url,map);
				 System.out.print(tt);
			}
			 model.put("DataJson", "true");
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
