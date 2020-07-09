package com.repast.core.util;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>2010 All Rights Reserved. js_todaysoft 版权所有</p>
 * <p>Company: cn.com.todaysoft.js</p>
 * @author chenhua
 * @version 1.0
 * @Date 2016年1月4日 下午7:00:44
 */

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
 
 
public class APIHttpClient {
 
	public static JSONObject post(JSONObject json,String url)
    {
    	DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
          StringEntity s = new StringEntity(json.toString());
          s.setContentEncoding("UTF-8");
          s.setContentType("application/json");//发送json数据需要设置contentType
          post.setEntity(s);
          HttpResponse res = client.execute(post);
          if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            //HttpEntity entity = res.getEntity();
            String result = EntityUtils.toString(res.getEntity());// 返回json格式：
            response = JSONObject.fromObject(result);
          }
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
        return response;
    }
	
	public static void getString(String url)
	{
		try {
				CloseableHttpClient httpclient = HttpClientBuilder.create().build();  
		        HttpGet httpget = new HttpGet(url);     
		  
		        //配置请求的超时设置  
		        RequestConfig requestConfig = RequestConfig.custom()    
		                .setConnectionRequestTimeout(500)  
		                .setConnectTimeout(5000)    
		                .setSocketTimeout(5000).build();    
		        httpget.setConfig(requestConfig);   
		          
		        CloseableHttpResponse response = httpclient.execute(httpget);          
		        System.out.println("StatusCode -> " + response.getStatusLine().getStatusCode());
		  } catch (Exception e) {
			  e.printStackTrace();
		     }
	}
}
