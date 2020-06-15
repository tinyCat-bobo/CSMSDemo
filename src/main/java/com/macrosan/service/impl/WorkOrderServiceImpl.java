package com.macrosan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macrosan.common.PageObject;
import com.macrosan.common.exception.ServiceException;
import com.macrosan.mapper.WorkOrderMapper;
import com.macrosan.pojo.WorkOrder;
import com.macrosan.service.WorkOrderService;

@Service
public class WorkOrderServiceImpl implements WorkOrderService {
	
	@Autowired
	private WorkOrderMapper workOrderMapper;

	@Override
	public PageObject<WorkOrder> findObjects(String orderName, Integer pageCurrent) {
		if(pageCurrent == null || pageCurrent < 1) throw new IllegalArgumentException("当前页码不正确");
		int rowCount = workOrderMapper.getRowCount(orderName);
		if(rowCount == 0) {
			throw new ServiceException("未找到相关记录");
		};
		int pageSize = 5;
		int startIndex = (pageCurrent-1)*pageSize;
		List<WorkOrder> workOrderList = workOrderMapper.findObjects(orderName, startIndex, pageSize);
		PageObject<WorkOrder> pageObject = new PageObject<>();
		pageObject.setPageCurrent(pageCurrent)
				.setPageSize(pageSize)
				.setRecords(workOrderList)
				.setRowCount(rowCount)
				.setPageCount((rowCount-1)/pageSize+1);
		return pageObject;
	}

	@Override
	public int deleteObjects(Integer[] ids) {
		if(ids.length <= 0 || ids == null) {
			throw new IllegalArgumentException("参数不合法");
		}
		int rows = 0;
		try {
			rows = workOrderMapper.deleteObjects(ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("系统故障,请联系管理员");
		}
		if(rows==0) {
			throw new ServiceException("记录可能已经不存在");
		}
		return rows;
	}
	
}
