package com.macrosan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

	@RequestMapping("")
	public String doIndexUI() {
		return "starter";
	}
	
	//使用restful风格的实现通用页面跳转
	@RequestMapping("{module}/{module_list}")
	public String doModuleUI(@PathVariable String module_list) {
		return "sys/"+module_list;
	}
	
	//加载分页信息
	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "/common/page";
	}
}
