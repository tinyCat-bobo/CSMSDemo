package com.macrosan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.SpareAddress;
import com.macrosan.service.SpareService;
import com.macrosan.vo.SysResult;

@RestController
@RequestMapping("/spares/")
public class SpareController {
	@Autowired
	private SpareService spareService;
	
	@RequestMapping("doGetSpareAddress")
	public SysResult doGetSpareAddress(Integer pageCurrent) {
		PageObject<SpareAddress> pageObject = spareService.getAllAddress(pageCurrent);
		return SysResult.success(pageObject);
	}

	/*
	 * spares/doSaveSpareAddress 
	 */
	@RequestMapping("doSaveSpareAddress")
	public SysResult doSaveSpareAddress(SpareAddress spareAddress) {
		int row = spareService.saveSpareAddress(spareAddress);
		return SysResult.success(row);
	}
	
	/*
	 * http://localhost:8100/spares/doDeleteObject 
	 */
	@RequestMapping("doDeleteObject")
	public SysResult doDeleteSpareAddress(Integer id) {
		int row = spareService.deleteSpareAddress(id);
		return SysResult.success(row);
	}
	//deGetSpareAddressById
	@RequestMapping("deGetSpareAddressById")
	public SysResult doFindSpareAddressById(Integer id) {
		SpareAddress spareAddressData = spareService.FindSpareAddressById(id);
		return SysResult.success(spareAddressData);
	}
	
	//http://localhost:8100/spares/doUpdateSpareAddress
	@RequestMapping("doUpdateSpareAddress")
	public SysResult doUpdateSpareAddress(SpareAddress spareAddress) {
		int row = spareService.updateSpareAddress(spareAddress);
		return SysResult.success(row);
	}
}
