package com.JiGuangPush;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class Weatherrpt {

    //parm：请求的url链接  返回的是json字符串
	public static String getURLContent(String urlStr,String type) {               
		//请求的url 
		URL url = null;      
	    //请求的输入流
	    BufferedReader in = null;   
		//输入流的缓冲
	    StringBuffer sb = new StringBuffer(); 
		try{
			url = new URL(urlStr);     
			in = new BufferedReader(new InputStreamReader(url.openStream(),type)); 
			String str = null;  
			//一行一行进行读入
			while((str = in.readLine()) != null) {
				sb.append( str );     
			}     
		} catch (Exception ex) {   
			            
		} finally{    
			try{
				if(in!=null) {
					in.close(); //关闭流    
			    }     
		    }catch(IOException ex) {      
			        
		    }     
		}     
	   String result =sb.toString();     
	   return result;    
	}  

public static void main(String []args) {
    // 三天预报链接
    String day3Url = "https://free-api.heweather.com/s6/weather/forecast?location=hefei&key=322389fe745246a88c9371a867475438";
    // 实时预报链接
    String realTimeUrl = "https://free-api.heweather.com/s6/weather/now?location=hefei&key=322389fe745246a88c9371a867475438";
    String string = getURLContent(day3Url,"utf-8");
    System.out.println(string); // 打印 天气信息
}
}