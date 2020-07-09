package com.JiGuangPush;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pm.persistence.Sqmmanage.entity.FillingPlanInfo;
import com.sqm.sqmpushinfo.entity.SqmPushInfo;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JPush {
	private static Boolean ApnsProduction = true;
	// 设置好账号的app_key和masterSecret是必须的
	private static String APP_KEY = "d7e2a8659abcf6d174665a80";
	private static String MASTER_SECRET = "3682ebcfcf8187a6fb6e019c";
	
	private static String MOVINGAPP_KEY = "5843009784db283d9006b75f";
	private static String MOVINGMASTER_SECRET = "e2c528c8e300736eeeeb95ef";
	
	public static void main(String[] args) throws APIConnectionException, APIRequestException {
		
		FillingPlanInfo planInfo = new FillingPlanInfo();
		planInfo.setTestNum(1);
		planInfo.setFault_user_id("lg00691");
		SqmPushInfo sqmPushInfo = new SqmPushInfo();
		sqmPushInfo.setPush_msg("推送测试");
		sqmPushInfo.setPush_type("continueDate");
		Map<String, String> parm = new HashMap<String, String>();
		parm.put("id", "32ef425214");
		// 设置提示信息,内容是文章标题
		parm.put("msg", sqmPushInfo.getPush_msg());
		jpushSomeOneSegment(parm, "iOS", "dsdsdw", planInfo, sqmPushInfo);//jpushSomeOne("121c83f7604c96aa701,1114a89792c950cdc89", sqmPushInfo, "iOS", "dfdf524fd", planInfo);
		
	}
	// 极光推送>>All所有平台
	public static void jpushAll(SqmPushInfo sqmPushInfo,String push_id,FillingPlanInfo planInfo) {
		// 创建JPushClient
		JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
		// 创建option
/*		PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.ios())// ios平台的用户
				.setAudience(Audience.all())// 所有用户
				.setNotification(
						Notification.newBuilder()
						.addPlatformNotification(IosNotification.newBuilder().setAlert(msg)
								.setBadge(+1).setSound("happy")// 这里是设置提示音(更多可以去官网看看)
								.build())
						.build())
				.setOptions(Options.newBuilder().setApnsProduction(false).build())
				.setMessage(Message.newBuilder().setMsgContent(msg)
						.build())
				// 自定义信息
				.build();
*/
		PushPayload payload =  PushPayload.newBuilder().setPlatform(Platform.ios())// ios平台的用户
				.setAudience(Audience.all())// 所有用户
	                .setNotification(Notification.newBuilder()
	                        .setAlert(sqmPushInfo.getPush_msg())
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .setBadge(1)
	                                .setSound("default").addExtra("push_id",push_id).addExtra("TestNum",planInfo.getTestNum()).addExtra("push_type",sqmPushInfo.getPush_type()).addExtra("fault_user_id",planInfo.getFault_user_id())
	                                .build())
	                        .build()).setOptions(Options.newBuilder().setApnsProduction(ApnsProduction).build())// 指定开发环境 true为生产模式 false 为测试模式
	                .build();


/*		PushPayload payload1 = PushPayload.newBuilder().setPlatform(Platform.android())// 指定android平台的用户
				.setAudience(Audience.all())// 你项目中的所有用户
				.setNotification(Notification.newBuilder().addPlatformNotification(AndroidNotification.newBuilder() // 发送android
						.setAlert(msg) // 消息体
						.build()).build())
				.setOptions(Options.newBuilder().setApnsProduction(true).build())// 指定开发环境 true为生产模式 false 为测试模式
				// (android不区分模式,ios区分模式)
				.setMessage(Message.newBuilder().setMsgContent(msg)
						.build())// 自定义信息
				.build();*/


		try {
			jpushClient.sendPush(payload);
			/*jpushClient.sendPush(payload1);*/
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}

	// 给指定一个或多人传消息
	public static void jpushPeople(String ids,SqmPushInfo sqmPushInfo,String deviceType,String push_id,FillingPlanInfo planInfo) {
		// 创建JPushClient
		JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
		String str[] = ids.split(",");
		List<String> li = Arrays.asList(str);
		for (int i = 0; i < li.size(); i++) {
			Map<String, String> parm = new HashMap<String, String>();
			parm.put("id", li.get(i));
			// 设置提示信息,内容是文章标题
			parm.put("msg", sqmPushInfo.getPush_msg());
			JPush.jpushSomeOne(parm, jpushClient,deviceType,push_id,planInfo,sqmPushInfo);
		}
	}

	// 传消息
	public static void jpushSomeOne(Map<String, String> parm, JPushClient jpushClient,String deviceType,String push_id,FillingPlanInfo planInfo,SqmPushInfo sqmPushInfo) {

		try {
			
			if("iOS".equals(deviceType)) {
				// 创建option
/*				PushPayload payload = PushPayload.newBuilder().setPlatform(Platform.ios())// ios平台的用户
						.setAudience(Audience.registrationId(parm.get("id")))// registrationId指定用户
						.setNotification(
								Notification.newBuilder()
								.addPlatformNotification(IosNotification.newBuilder().setAlert(parm.get("msg"))
										.setBadge(+1).setSound("happy")// 这里是设置提示音(更多可以去官网看看)
										.build())
								.build())
						.setOptions(Options.newBuilder().setApnsProduction(false).build())
						.setMessage(Message.newBuilder().setMsgContent(parm.get("msg"))
								.build())// 自定义信息
						.build();*/
				
				PushPayload payload =  PushPayload.newBuilder().setPlatform(Platform.ios())// ios平台的用户
						.setAudience(Audience.registrationId(parm.get("id")))// 所有用户
			                .setNotification(Notification.newBuilder()
			                        .setAlert(sqmPushInfo.getPush_msg())
			                        .addPlatformNotification(IosNotification.newBuilder()
			                                .setBadge(1)
			                                .setSound("default").addExtra("push_id",push_id).addExtra("TestNum",planInfo.getTestNum()).addExtra("push_type",sqmPushInfo.getPush_type()).addExtra("fault_user_id",planInfo.getFault_user_id())
			                                .build())
			                        .build()).setOptions(Options.newBuilder().setApnsProduction(ApnsProduction).build())// 指定开发环境 true为生产模式 false 为测试模式
			                .build();
				
				
				
				jpushClient.sendPush(payload);
			}else {
				PushPayload payload1 = PushPayload.newBuilder().setPlatform(Platform.android())// 指定android平台的用户
						.setAudience(Audience.registrationId(parm.get("id")))// registrationId指定用户
						.setNotification(Notification.newBuilder().addPlatformNotification(AndroidNotification.newBuilder() // 发送android
								.setAlert(parm.get("msg")) // 消息体
								.build()).build())
						.setOptions(Options.newBuilder().setApnsProduction(ApnsProduction).build())// 指定开发环境 true为生产模式 false 为测试模式
						// (android不区分模式,ios区分模式)
						.setMessage(Message.newBuilder().setMsgContent(parm.get("msg"))
								.build())// 自定义信息
						.build();		
				jpushClient.sendPush(payload1);
			}


		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
	
	// 用户分组消息
		public static void jpushSomeOneSegment(Map<String, String> parm,String deviceType,String push_id,FillingPlanInfo planInfo,SqmPushInfo sqmPushInfo) {

			try {
				JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);
				if("iOS".equals(deviceType)) {
					// 创建option
					PushPayload payload =  PushPayload.newBuilder().setPlatform(Platform.ios())// ios平台的用户
							.setAudience(Audience.segment(parm.get("id")))// 分组id
				                .setNotification(Notification.newBuilder()
				                        .setAlert(sqmPushInfo.getPush_msg())
				                        .addPlatformNotification(IosNotification.newBuilder()
				                                .setBadge(1)
				                                .setSound("default").addExtra("push_id",push_id).addExtra("TestNum",planInfo.getTestNum()).addExtra("push_type",sqmPushInfo.getPush_type()).addExtra("fault_user_id",planInfo.getFault_user_id())
				                                .build())
				                        .build()).setOptions(Options.newBuilder().setApnsProduction(ApnsProduction).build())// 指定开发环境 true为生产模式 false 为测试模式
				                .build();
					
					
					
					jpushClient.sendPush(payload);
				}else {
					PushPayload payload1 = PushPayload.newBuilder().setPlatform(Platform.android())// 指定android平台的用户
							.setAudience(Audience.registrationId(parm.get("id")))// registrationId指定用户
							.setNotification(Notification.newBuilder().addPlatformNotification(AndroidNotification.newBuilder() // 发送android
									.setAlert(parm.get("msg")) // 消息体
									.build()).build())
							.setOptions(Options.newBuilder().setApnsProduction(ApnsProduction).build())// 指定开发环境 true为生产模式 false 为测试模式
							// (android不区分模式,ios区分模式)
							.setMessage(Message.newBuilder().setMsgContent(parm.get("msg"))
									.build())// 自定义信息
							.build();		
					jpushClient.sendPush(payload1);
				}


			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
		}
	
	
	
	// 极光推送>>All所有平台搬入搬出推送
		public static void movingjpushAll(SqmPushInfo sqmPushInfo,String push_id,FillingPlanInfo planInfo) {
			// 创建JPushClient
			JPushClient jpushClient = new JPushClient(MOVINGMASTER_SECRET, MOVINGAPP_KEY);
			// 创建option
	
			PushPayload payload =  PushPayload.newBuilder().setPlatform(Platform.ios())// ios平台的用户
					.setAudience(Audience.all())// 所有用户
		                .setNotification(Notification.newBuilder()
		                        .setAlert(sqmPushInfo.getPush_msg())
		                        .addPlatformNotification(IosNotification.newBuilder()
		                                .setBadge(1)
		                                .setSound("default").addExtra("push_id",push_id).addExtra("TestNum",planInfo.getTestNum()).addExtra("push_type",sqmPushInfo.getPush_type()).addExtra("fault_user_id",planInfo.getFault_user_id())
		                                .build())
		                        .build()).setOptions(Options.newBuilder().setApnsProduction(ApnsProduction).build())// 指定开发环境 true为生产模式 false 为测试模式
		                .build();


			PushPayload payload1 = PushPayload.newBuilder().setPlatform(Platform.android())// 指定android平台的用户
					.setAudience(Audience.all())// 你项目中的所有用户
					.setNotification(Notification.newBuilder().addPlatformNotification(AndroidNotification.newBuilder() // 发送android
							.setAlert(sqmPushInfo.getPush_msg()) // 消息体
							.build()).build())
					.setOptions(Options.newBuilder().setApnsProduction(ApnsProduction).build())// 指定开发环境 true为生产模式 false 为测试模式
					// (android不区分模式,ios区分模式)
					.setMessage(Message.newBuilder().setMsgContent("234234234")
							.build())// 自定义信息
					.build();


			try {
				jpushClient.sendPush(payload1);
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
			try {
				jpushClient.sendPush(payload);
			} catch (APIConnectionException e) {
				e.printStackTrace();
			} catch (APIRequestException e) {
				e.printStackTrace();
			}
		}

		// 给指定一个或多人传消息 搬入搬出推送
		public static void movingjpushPeople(String ids,SqmPushInfo sqmPushInfo,String deviceType,String push_id,FillingPlanInfo planInfo) {
			// 创建JPushClient
			JPushClient jpushClient = new JPushClient(MOVINGMASTER_SECRET, MOVINGAPP_KEY);
			String str[] = ids.split(",");
			List<String> li = Arrays.asList(str);
			for (int i = 0; i < li.size(); i++) {
				Map<String, String> parm = new HashMap<String, String>();
				parm.put("id", li.get(i));
				// 设置提示信息,内容是文章标题
				parm.put("msg", sqmPushInfo.getPush_msg());
				JPush.jpushSomeOne(parm, jpushClient,deviceType,push_id,planInfo,sqmPushInfo);
			}
		}
}
