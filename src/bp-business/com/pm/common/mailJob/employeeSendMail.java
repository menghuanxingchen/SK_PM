package com.pm.common.mailJob;


import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
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

import com.pm.business.userinfo.service.SysUserInfoService;
import com.repast.core.constant.SysConstant;
import com.repast.core.spring.mvc.util.JSONMap;
import com.repast.persistence.entity.SysUserInfo;


@Component
public class employeeSendMail {
	
	@Resource
	private SysUserInfoService sysUserInfoService;
	
	@Scheduled(cron = "0 30 1 1 * ?") // 每月1号上午1.30点，给员工发
    public void taskSendMailbyEmployee() {
		 
		SysUserInfo sysUserInfo = new SysUserInfo();
		sysUserInfo.setTongYiCount(0);
		List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoListByReport(sysUserInfo);
		try{

			Calendar cale = Calendar.getInstance();
		         
			for (SysUserInfo s : sysUserInfoList) {
				
				String StrJianCount = "";
				String htmlSubject = "提案数量提醒";
				
				// 创建一个数值格式化对象
	            NumberFormat numberFormat = NumberFormat.getInstance();
		 
				// 设置精确到小数点后2位
		        numberFormat.setMaximumFractionDigits(2);

				//String WanChengL = numberFormat.format((float) s.getReportCount() / (float) 6 * 100);
				
				
				if(s.getReportCount()< 1){
					StrJianCount = "您还没完成改善提案！";
				}else{
					StrJianCount = "您已经完成改善提案！";
				}
				
				String htmlContent = "<p><span style='font-size: 18px;'>您好：<strong>"+s.getUser_nm()+"</strong></span></p>"+
						 "<p>&nbsp; &nbsp; &nbsp; &nbsp;  提案目标为每人每年1件. 您"+cale.get(Calendar.YEAR)+"年度的提案数为："
						 +s.getReportCount()+"件，"+StrJianCount+"</p>";
						
	
				sendMailbyUserInfo(htmlContent,htmlSubject,s.getEmail());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
    }
	
	
	@Scheduled(cron = "0 30 2 1 * ?") // 每月1号上午2.30点给乔海滨科长发
    public void taskSendMailbySCKSection() {
		
		SysUserInfo sysUserInfo = new SysUserInfo();
		sysUserInfo.setDept_code("2");
		List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoListByReport(sysUserInfo);
		
		SysUserInfo sysUserInfo1 = new SysUserInfo();
		sysUserInfo1.setTongYiCount(6);
		sysUserInfo1.setDept_code("2");
		List<SysUserInfo> sysUserInfoList1 = sysUserInfoService.querySysUserInfoListByAll(sysUserInfo1);
		
		try{

			Calendar cale = Calendar.getInstance();
		        
			String StrJianCount = "";
			String htmlSubject = "提案数量提醒";
			String ContentDetail = "";//表格内容
			
			int PersonCnt = sysUserInfoList.size();
			int WanchengCnt = sysUserInfoList1.size();
			
			// 创建一个数值格式化对象
            NumberFormat numberFormat = NumberFormat.getInstance();
	 
			// 设置精确到小数点后2位
	        numberFormat.setMaximumFractionDigits(2);

			String WanChengL = "0";
			
			if(PersonCnt != 0){
				WanChengL = numberFormat.format((float) WanchengCnt / (float) PersonCnt * 100);
			}

			
			for (SysUserInfo s : sysUserInfoList) {
				ContentDetail += "	<tr>"+
						"	<td >"+s.getUser_num()+"</td>"+
						"	<td >"+s.getUser_nm()+"</td>"+
						"	<td >"+s.getReportCount()+"</td>"+
						"	<td >"+s.getTongYiCount()+"</td>"+
						"	<td >"+s.getBoHuiCount()+"</td>"+
						"	<td >"+s.getWeiShenCount()+"</td>"+
						"	</tr>";
				
			}
			
			String htmlContent = "<p><span style='font-size: 18px;'>您好：<strong>乔海滨</strong>&nbsp;科长</span></p>"+
					 "<p>&nbsp; &nbsp; &nbsp; &nbsp;  提案目标为每人每年1件. 您部门"+cale.get(Calendar.YEAR)+"年度的完成率为："+WanChengL+"%,详情如下："+
					
					 "   <table  border='1'>"+
					
					"	        <tr ><td style='width:150px'>工号</td><td style='width:150px'>姓名</td><td style='width:150px'>提案数</td><td style='width:150px'>已同意数"+
					"	        </td><td style='width:150px'>驳回件数</td><td style='width:150px'>未审批件数</td>"+
					"	        </tr>"+ContentDetail+		
					"	</table>";

			sendMailbyUserInfo(htmlContent,htmlSubject,"qiaohaibin@sk.com");
				
		} catch (Exception e) {
			e.printStackTrace();
			
		}
    }
	
	
	@Scheduled(cron = "0 30 3 1 * ?") // 每月1号上午2.30点给赵正博科长发
    public void taskSendMailbyPZKSection() {
		
		SysUserInfo sysUserInfo = new SysUserInfo();
		sysUserInfo.setDept_code("1");
		List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoListByReport(sysUserInfo);
		
		
		SysUserInfo sysUserInfo1 = new SysUserInfo();
		sysUserInfo1.setTongYiCount(6);
		sysUserInfo1.setDept_code("1");
		List<SysUserInfo> sysUserInfoList1 = sysUserInfoService.querySysUserInfoListByAll(sysUserInfo1);
		
		try{

			Calendar cale = Calendar.getInstance();
		         
			
				
				String StrJianCount = "";
				String htmlSubject = "提案数量提醒";
				String ContentDetail = "";//表格内容
				
				int PersonCnt = sysUserInfoList.size();
				int WanchengCnt = sysUserInfoList1.size();
				
				// 创建一个数值格式化对象
				 
				NumberFormat numberFormat = NumberFormat.getInstance();
		 
				// 设置精确到小数点后2位
		 
				numberFormat.setMaximumFractionDigits(2);

				
				
				String WanChengL = "0";
				
				if(PersonCnt != 0){
					WanChengL = numberFormat.format((float) WanchengCnt / (float) PersonCnt * 100);
				}

				
				
				for (SysUserInfo s : sysUserInfoList) {
					ContentDetail += "	<tr>"+
							"	<td >"+s.getUser_num()+"</td>"+
							"	<td >"+s.getUser_nm()+"</td>"+
							"	<td >"+s.getReportCount()+"</td>"+
							"	<td >"+s.getTongYiCount()+"</td>"+
							"	<td >"+s.getBoHuiCount()+"</td>"+
							"	<td >"+s.getWeiShenCount()+"</td>"+
							"	</tr>";
					
				}
				
				String htmlContent = "<p><span style='font-size: 18px;'>您好：<strong>赵正博</strong>&nbsp;科长</span></p>"+
						 "<p>&nbsp; &nbsp; &nbsp; &nbsp;  提案目标为每人每年1件. 您部门"+cale.get(Calendar.YEAR)+"年度的完成率为："+WanChengL+"%,详情如下："+
						
						 "   <table  border='1'>"+
						
						"	        <tr ><td style='width:150px'>工号</td><td style='width:150px'>姓名</td><td style='width:150px'>提案数</td><td style='width:150px'>已同意数"+
						"	        </td><td style='width:150px'>驳回件数</td><td style='width:150px'>未审批件数</td>"+
						"	        </tr>"+ContentDetail+		
						"	</table>";

				sendMailbyUserInfo(htmlContent,htmlSubject,"zhaozhengbo@sk.com");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
    }
	
	
	@Scheduled(cron = "0 30 4 1 * ?") // 每月1号上午2.30点给张志勇科长发
    public void taskSendMailbySCZYKSection() {
		
		SysUserInfo sysUserInfo = new SysUserInfo();
		sysUserInfo.setDept_code("3");
		List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoListByReport(sysUserInfo);
		
		
		SysUserInfo sysUserInfo1 = new SysUserInfo();
		sysUserInfo1.setTongYiCount(6);
		sysUserInfo1.setDept_code("3");
		List<SysUserInfo> sysUserInfoList1 = sysUserInfoService.querySysUserInfoListByAll(sysUserInfo1);
		
		try{

			Calendar cale = Calendar.getInstance();
		         
				String StrJianCount = "";
				String htmlSubject = "提案数量提醒";
				String ContentDetail = "";//表格内容
				
				int PersonCnt = sysUserInfoList.size();
				int WanchengCnt = sysUserInfoList1.size();
				
				// 创建一个数值格式化对象
				 
				NumberFormat numberFormat = NumberFormat.getInstance();
		 
				// 设置精确到小数点后2位
		 
				numberFormat.setMaximumFractionDigits(2);

				
				
				String WanChengL = "0";
				
				if(PersonCnt != 0){
					WanChengL = numberFormat.format((float) WanchengCnt / (float) PersonCnt * 100);
				}

				
				for (SysUserInfo s : sysUserInfoList) {
					ContentDetail += "	<tr>"+
							"	<td >"+s.getUser_num()+"</td>"+
							"	<td >"+s.getUser_nm()+"</td>"+
							"	<td >"+s.getReportCount()+"</td>"+
							"	<td >"+s.getTongYiCount()+"</td>"+
							"	<td >"+s.getBoHuiCount()+"</td>"+
							"	<td >"+s.getWeiShenCount()+"</td>"+
							"	</tr>";
					
				}
				
				String htmlContent = "<p><span style='font-size: 18px;'>您好：<strong>张志勇</strong>&nbsp;科长</span></p>"+
						 "<p>&nbsp; &nbsp; &nbsp; &nbsp;  提案目标为每人每年1件. 您部门"+cale.get(Calendar.YEAR)+"年度完成率为："+WanChengL+"%,详情如下："+
						
						 "   <table  border='1'>"+
						
						"	        <tr ><td style='width:150px'>工号</td><td style='width:150px'>姓名</td><td style='width:150px'>提案数</td><td style='width:150px'>已同意数"+
						"	            </td><td style='width:150px'>驳回件数</td><td style='width:150px'>未审批件数</td>"+
						"	        </tr>"+ContentDetail+		
						"	</table>";

				sendMailbyUserInfo(htmlContent,htmlSubject,"zhangzhiyong@sk.com");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
    }
	
	
	@Scheduled(cron = "0 30 5 1 * ?") // 每月1号上午2.30点给厂长发
    public void taskSendMailbyFactory() {
		
		SysUserInfo sysUserInfo = new SysUserInfo();
		
		List<SysUserInfo> sysUserInfoList = sysUserInfoService.querySysUserInfoListByAll(sysUserInfo);
		
		
		SysUserInfo sysUserInfo1 = new SysUserInfo();
		sysUserInfo1.setTongYiCount(6);
		List<SysUserInfo> sysUserInfoList1 = sysUserInfoService.querySysUserInfoListByAll(sysUserInfo1);
		
		try{

				Calendar cale = Calendar.getInstance();
			         
				
				String StrJianCount = "";
				String htmlSubject = "提案数量提醒";
				String ContentDetail = "";//表格内容
				int PersonCnt = sysUserInfoList.size();
				int WanchengCnt = sysUserInfoList1.size();
				
				// 创建一个数值格式化对象
				 
				NumberFormat numberFormat = NumberFormat.getInstance();
		 
				// 设置精确到小数点后2位
		 
				numberFormat.setMaximumFractionDigits(2);
	
				
				
				String WanChengL = "0";
				
				if(PersonCnt != 0){
					WanChengL = numberFormat.format((float) WanchengCnt / (float) PersonCnt * 100);
				}
	
				for (SysUserInfo s : sysUserInfoList) {
					ContentDetail += "	<tr>"+
							"	<td >"+s.getUser_num()+"</td>"+
							"	<td >"+s.getUser_nm()+"</td>"+
							"	<td >"+s.getReportCount()+"</td>"+
							"	<td >"+s.getTongYiCount()+"</td>"+
							"	<td >"+s.getBoHuiCount()+"</td>"+
							"	<td >"+s.getWeiShenCount()+"</td>"+
							"	</tr>";
					
				}

				String htmlContent = "<p><span style='font-size: 18px;'>您好：<strong>邵勇</strong>&nbsp;厂长</span></p>"+
						 "<p>&nbsp; &nbsp; &nbsp; &nbsp;  提案目标为每人每年1件. "+cale.get(Calendar.YEAR)+"年度员工提案完成率为："+WanChengL+"% ,详情如下："+
						
						 "   <table  border='1'>"+
						
						 "	        <tr ><td style='width:150px'>工号</td><td style='width:150px'>姓名</td><td style='width:150px'>提案数</td><td style='width:150px'>已同意数"+
						"	            </td><td style='width:150px'>驳回件数</td><td style='width:150px'>未审批件数</td>"+
						"	        </tr>"+ContentDetail+		
						"	</table>";
	
				sendMailbyUserInfo(htmlContent,htmlSubject,"shaoyong@sk.com");
			
	
		} catch (Exception e) {
			e.printStackTrace();
			
		}
    }
	
	/**
	 * 发送邮件
	 * 
	 * @param 发送邮件
	 * @param processor
	 * @return
	 */
	
	public @ResponseBody JSONMap<String, Object>  sendMailbyUserInfo(String htmlContent,String htmlSubject,String htmlReceive){
		JSONMap<String, Object> model = new JSONMap<String, Object>();
		try{
			 Properties prop = new Properties();
			 prop.setProperty("mail.transport.protocol", "smtp");
			 prop.setProperty("mail.debug", "true");
			 prop.setProperty("mail.smtp.host", "61.161.213.5");
			 prop.setProperty("mail.smtp.port", "587");
			 prop.setProperty("mail.smtp.auth", "true");
			 
			 // 1、创建session
			 Session session = Session.getInstance(prop);
			 Transport ts = null;
			 // 2、通过session得到transport对象
			 ts = session.getTransport();
			 // 3、连上邮件服务器
			 ts.connect("61.161.213.5", "SklMail@skccchina.com", "china123!");
			 // 4、创建邮件
			 MimeMessage message = new MimeMessage(session);
			 // 邮件消息头
			 message.setFrom(new InternetAddress("SklMail@skccchina.com")); // 邮件的发件人
			 message.setRecipient(Message.RecipientType.TO, new InternetAddress(htmlReceive)); // 邮件的收件人
	
			 message.setSubject(htmlSubject); // 邮件的标题
			 
			 MimeBodyPart text = new MimeBodyPart();
			 text.setContent(htmlContent, "text/html;charset=UTF-8");
			
			
			 // 描述数据关系
			 MimeMultipart mm = new MimeMultipart();
			 mm.addBodyPart(text);
			
			 mm.setSubType("related");
			 message.setContent(mm);
			 message.saveChanges();
			 // 5、发送邮件
			 ts.sendMessage(message, message.getAllRecipients());
			 ts.close();
			 model.put(SysConstant.OP_FLAG, true);
			 model.put(SysConstant.OP_MESSAGE, SysConstant.SUCCESS);
		
		} catch (Exception e) {
			e.printStackTrace();
			model.put(SysConstant.OP_FLAG, false);
			model.put(SysConstant.OP_MESSAGE, SysConstant.Exception);
		}
		return model;
	}
}
