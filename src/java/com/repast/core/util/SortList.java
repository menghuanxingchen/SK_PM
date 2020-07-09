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

import java.lang.reflect.InvocationTargetException;  
import java.lang.reflect.Method;  
import java.util.Collections;  
import java.util.Comparator;  
import java.util.List;  

/*** 
 * 用户排序 
 * @author gaowenchao 
 * 2015年7月1日 
 * @param <E> 
 */  
public class SortList<E> {  
    public void Sort(List<E> list, final String method, final String sort) {  
        Collections.sort(list, new Comparator() {  
            public int compare(Object a, Object b) {  
                int ret = 0;  
                try {  
                    Method m1 = ((E) a).getClass().getMethod(method, null);  
                    Method m2 = ((E) b).getClass().getMethod(method, null);  
                    if (sort != null && "desc".equals(sort))// 倒序  
                        ret = m2.invoke(((E) b), null).toString()  
                                .compareTo(m1.invoke(((E) a), null).toString());  
                    else  
                        // 正序  
                        ret = m1.invoke(((E) a), null).toString()  
                                .compareTo(m2.invoke(((E) b), null).toString());  
                } catch (NoSuchMethodException ne) {  
                    System.out.println(ne);  
                } catch (IllegalAccessException ie) {  
                    System.out.println(ie);  
                } catch (InvocationTargetException it) {  
                    System.out.println(it);  
                }  
                return ret;  
            }  
        });  
    }  
}  