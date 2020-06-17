package com.macrosan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macrosan.common.PageObject;
import com.macrosan.service.WorkHourService;
import com.macrosan.vo.SysResult;
import com.macrosan.vo.WorkHourVo;

@RestController
@RequestMapping("/workhours/")
public class WorkHourController {
	
	@Autowired
	private WorkHourService workHourService;

	@RequestMapping("doFindPageObjects")
	public SysResult doFindPageObjects(String proName,Integer pageCurrent) {
		PageObject<WorkHourVo> pageObject= workHourService.findObjects(proName,pageCurrent);
		return SysResult.success(pageObject);
	}
	
}
