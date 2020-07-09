package com.repast.core.exception;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Resource
public class MyExceptionHandler implements HandlerExceptionResolver {
	
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		if (ex instanceof NullPointerException) {
			// 这里就写如何处理,比如:记日志
			System.out.println("now is NullPointer");
		} else if (ex instanceof ArrayIndexOutOfBoundsException) {
			// 这里就写如何处理,比如:记日志
		}
		return new ModelAndView("exception");
	}
	
}
