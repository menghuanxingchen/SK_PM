package com.pm.business.worktimeinfo.controller;

import java.util.Calendar;

public class Test {

	public static void main(String[] args) {
		String[] week={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
		Calendar date = Calendar.getInstance();
    	String yearNow = String.valueOf(date.get(Calendar.YEAR));
    	String monthNow = String.valueOf(date.get(Calendar.MONTH));
        int year = Integer.parseInt(yearNow);
        int month = Integer.parseInt(monthNow)+1;
        if(month==12){
        	year++;
        	month=0;
        }
        int monthshow = month+1;
        if(monthshow==13){
        	monthshow=1;
        }
    	 Calendar c = Calendar.getInstance(); //获取Calendar实例
         c.set(Calendar.YEAR, year); //设置年
         c.set(Calendar.MONTH, month); //设置月
         c.set(Calendar.DAY_OF_MONTH, 1); //设置月开始第一天日期
         int end = c.getActualMaximum(Calendar.DAY_OF_MONTH); //获得月末日期
         for (int i=1; i<=end; i++) { //循环打印即可
        	 System.out.println(year+"-"+monthshow);
        	 System.out.println(String.valueOf(c.get(Calendar.DATE)));
        	 System.out.println(String.valueOf(week[c.get(Calendar.DAY_OF_WEEK)-1]));
             c.add(Calendar.DAY_OF_MONTH, 1);
         }
	}
}
