package com.sqm.sqmcomplaintfeedback.controller;

import java.util.Iterator;
import java.util.Set;

import com.repast.core.util.DateUtil;

import redis.clients.jedis.Jedis;

public class RedisPersistence {
	 /**
	 * 存储所有的redis对象方法
	 * @throws Exception 
	 */
	 public static void saveAllRedis(final String redisIp,final int redisPort,final String appCode) throws Exception {
	 // 连接redis
	  Jedis redis = new Jedis(redisIp, redisPort);
	   //redis.auth("redis");//验证密码
	  // KEY操作 列出所有的key，查找特定的key如：redis.keys("foo")
	  Set<?> keys = redis.keys("*");
	  Iterator<?> t1 = keys.iterator();
	  while (t1.hasNext()) {
	   Object obj1 = t1.next();
	   saveRedisObject(redis, obj1 + "", redisIp, redisPort + "", appCode);
	  }
	 }
	 
	 /**
	 * 存储单个对象
	 * @param redis
	 * @param redisKey
	 * @param macIp
	 * @param port
	 * @param appCode
	 * @throws Exception 
	 */
	 private static void saveRedisObject(final Jedis redis,final String redisKey,final String macIp,final String port,final String appCode) throws Exception {
	  String redisType = redis.type(redisKey);
	  RedisTable redisTable = new RedisTable();
	  //redisTable.setAppCode(appCode);
	  redisTable.setCreateTime(DateUtil.getNow("yyyy-MM-dd HH:mm:ss"));
	  redisTable.setMacIp(macIp);
	  redisTable.setPort(port);
	  redisTable.setRedisKey(redisKey);
	  redisTable.setRedisType(redisType);
	  redisTable.setRemark("");
	  redisTable.setUpdateTime(DateUtil.getNow("yyyy-MM-dd HH:mm:ss"));
	  //set集合
	  if("set".equalsIgnoreCase(redisType)){
	   Set<String> setStrings = redis.smembers(redisKey);//获取key的所有set集合
	   if(null != setStrings && !setStrings.isEmpty()){
	    Iterator<String> setIterator = setStrings.iterator() ; 
	    while(setIterator.hasNext()){ 
	     Object obj1 = setIterator.next();
	     redisTable.setRedisValue(obj1+"");
	     printRedis(redisTable);//保存每一个set记录
	    }
	   }
	  //hash集合
	  }else if("hash".equalsIgnoreCase(redisType)){
	   Set<String> hashSets = redis.hkeys(redisKey);
	   if(null != hashSets && !hashSets.isEmpty()){
	    Iterator<String> setIterator = hashSets.iterator() ; 
	    while(setIterator.hasNext()){ 
	     String objectName = setIterator.next()+"";
	     redisTable.setObjectName(objectName);
	     redisTable.setRedisValue(redis.hget(redisKey, objectName));
	     printRedis(redisTable);//保存每一个set记录
	    }
	   }
	  //list集合
	  }else if("list".equalsIgnoreCase(redisType)){
	   Long listLen = redis.llen(redisKey);
	   for (Long i = 0L; i < listLen; i++) {
	    redisTable.setRedisValue(redis.lindex(redisKey, i));
	    printRedis(redisTable);
	   }
	  //sortedset集合
	  }else if("sortedset".equalsIgnoreCase(redisType)){
	  // Long redisLenth = redis.zcard(redisKey);
	   Set<String> sortedsets = redis.zrange(redisKey, 0, -1);
	   if(null != sortedsets && !sortedsets.isEmpty()){
	    Iterator<String> setIterator = sortedsets.iterator() ; 
	    while(setIterator.hasNext()){ 
	     String sortedMember = setIterator.next() +"";
	     redisTable.setRedisValue(sortedMember);
	     redisTable.setScore("" +redis.zscore(redisKey, sortedMember));
	     //保存每一个sortedset记录
	     printRedis(redisTable);
	    }
	   }
	  //string集合
	  }else if("string".equalsIgnoreCase(redisType)){
	   redisTable.setRedisValue(redis.get(redisKey));
	   //保存记录
	   printRedis(redisTable);
	  }else{
	   System.out.println("UnknowRedisType-----redisType: " +redisType+"objValue: "+redis.get(redisKey));
	  }
	 }
	
	 //打印输出
	 public static void printRedis (RedisTable redisTable) {
	  System.out.println("redisType:"+redisTable.getRedisType() + " redisKey:"+redisTable.getRedisKey()+ " ObjectName:"+redisTable.getObjectName()
	      + " redisValue:"+redisTable.getRedisValue()+ " redisScore:"+redisTable.getScore());
	 }
	 
	 public static void main(String[] args) {
	  String redisIp = "127.0.0.1";//redis的IP地址
	  int redisPort = 6379;//redis的端口号
	  String appCode = "FUYOU";//根据不同的应用区分的appcode
	  try {
		saveAllRedis(redisIp,redisPort,appCode);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 }

}
