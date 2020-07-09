package com.pm.common.sqmJob;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pm.business.sqmmanage.service.SqmmanageService;
import com.pm.business.userinfo.service.SysUserInfoService;
import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.core.util.APIHttpClient;
import com.repast.core.util.DateUtil;
import com.repast.core.util.HttpClientUtil;
import com.repast.core.util.JsonUtils;
import com.repast.persistence.entity.SysUserInfo;
import com.sqm.SqmInterface.service.SqmInterfaceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Component
public class SqmInterfaceJob {
	
	@Resource
	private SqmInterfaceService sqmInterfaceService;
	
	@Scheduled(cron = "0 39 1 * * ?")  // 每天1.46，获取灌装计划
    public void taskGetFillingPlanInfo1() {
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
				
				
				System.out.print("获取数据成功"+NowDate);
				//遍历赋值
				JSONArray jsonArray = JSONArray.fromObject(NewDataJson);
				List<FillingPlanInfo> list = new ArrayList<>();
				for(int i = 0;i<jsonArray.size();i++) {
					JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i).toString());
					FillingPlanInfo user = (FillingPlanInfo) JSONObject.toBean(jsonObject, FillingPlanInfo.class);
					list.add(user);
				}
				System.out.print("转化List数据成功");
				//删除当日以后的数据
				sqmInterfaceService.deleteFillingPlanInfo(NowDate);
				
				System.out.print("删除当天以后数据成功");
				for (FillingPlanInfo fillingPlanInfo : list) {
					fillingPlanInfo.setFILL_STATE(0);//设置灌装状态未进行
					fillingPlanInfo.setCreate_time(DateUtil.getNow(DateUtil.Y_M_D_HMS));//当前时间
					sqmInterfaceService.saveFillingPlanInfo(fillingPlanInfo);
				}
				System.out.print("保存数据成功");
			}else{
				System.out.print("获取数据失败");
			}
		}catch(Exception e){
			System.out.print("taskGetFillingPlanInfo错误"+e.getMessage());
		}
		
	}
	
	
	@Scheduled(cron = "0 0/30 * * * ?")  // 每天半个小时，获取灌装计划
    public void taskGetFillingPlanInfo3() {
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
			}
			 
			
		}catch(Exception e){
			System.out.print("taskGetFillingPlanInfo错误"+e.getMessage());
		}
		
	}
}
