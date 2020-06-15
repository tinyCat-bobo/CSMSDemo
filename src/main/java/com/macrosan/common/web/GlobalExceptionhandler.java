package com.macrosan.common.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.macrosan.vo.SysResult;

@ControllerAdvice		//全局异常处理类
public class GlobalExceptionhandler {
	@ExceptionHandler
	@ResponseBody
	public SysResult doHandleRuntimeException(Throwable e) {
		e.printStackTrace();
		return SysResult.fail();
	}
}
