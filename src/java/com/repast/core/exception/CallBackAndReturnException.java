package com.repast.core.exception;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class CallBackAndReturnException extends Exception {
	
	private static Logger logger = Logger.getLogger("logger");
	public CallBackAndReturnException(){
		
		logger.info("手动执行异常回滚！");
	}

}
