package com.pm.common.sqmJob;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class fillingPlanSetDataJob {
	
	@Resource
	private SqmInterfaceService sqmInterfaceService;
	
	@Scheduled(cron = "0 13 1 * * ?")  // 每天23点，回写
    public void taskGetFillingPlanInfo() {
		try{
			String url="http://114.113.147.110:8899/api/SqmService/GetChargeData";
			
			//String NowDate = DateUtil.getNow(DateUtil.YMD);
			
			HttpClientUtil getDataJson = new HttpClientUtil();
			
			FillingPlanInfo fillingPlanInfo = new FillingPlanInfo();
			//fillingPlanInfo.setCNFYMD(NowDate);
			List<FillingPlanInfo> fillingPlanList =sqmInterfaceService.queryFillingPlanDataList(fillingPlanInfo);
			for(int i = 0;i<fillingPlanList.size();i++) {
				FillingPlanInfo f = (FillingPlanInfo)fillingPlanList.get(i);
				
				//参数
				 Map<String, String> map = new IdentityHashMap<String, String>();
				 map.put("DATE", f.getCNFYMD());
				 map.put("VAL", f.getVSL());
				 map.put("TURN", f.getTURN());
				 map.put("PKG", f.getPKG());
				 map.put("PROD", f.getPROD());
				 map.put("LOTNO", f.getLOTNO());
				 map.put("STARDATE", f.getFILL_STDATE());
				 map.put("ENDDATE", f.getFILL_EDDATE());
				 
				 String  DataJson= getDataJson.doGet(url,map);
				 
				 System.out.print(DataJson);
			}
		}catch(Exception e){
			System.out.print("fillingPlanSetDataJob错误");
		}
		
	}
	

}
