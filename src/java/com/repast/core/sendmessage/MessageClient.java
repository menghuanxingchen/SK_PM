package com.repast.core.sendmessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

public class MessageClient implements Runnable {


	private String phoneNumber;
	
	private String msg;
	
	
	
	
	public MessageClient(String phoneNumber, String msg) {
		super();
		this.phoneNumber = phoneNumber;
		this.msg = msg;
	}


	private static String baseurl = "http://221.122.112.136:8080/sms/";
	private static String username="estmis";
	private static String password="estmis";

	public String sendSms(String phone,
			String content) throws Exception {
		return clientSDK(baseurl + "mt.jsp?cpName=" + username
				+ "&cpPwd=" + password + "&phones=" + phone + "&msg="
				+ URLEncoder.encode(content, "gbk"),"gbk",null);
	}
	
	public String clientSDK(String src, String outputencode,
			HashMap<String, String> headers)  throws Exception{
		StringBuffer result = new StringBuffer();
		URL url = new URL(src);
		URLConnection connection = url.openConnection();
		BufferedReader in = null;
		try {
			if (headers != null) {
				Iterator<String> iterators = headers.keySet().iterator();
				while (iterators.hasNext()) {
					String key = iterators.next();
					connection.setRequestProperty(key, headers.get(key));
				}
			}
			connection.connect();
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), outputencode));
			String line;
			while ((line = in.readLine()) != null) {
				result.append("\n" + line);
			}
			return result.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}
	
	
	public void run() {
		try {
			this.sendSms(this.phoneNumber, this.msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}