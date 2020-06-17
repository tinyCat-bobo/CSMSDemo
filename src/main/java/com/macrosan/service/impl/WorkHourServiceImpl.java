package com.macrosan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macrosan.common.PageObject;
import com.macrosan.common.exception.ServiceException;
import com.macrosan.mapper.WorkHourMapper;
import com.macrosan.service.WorkHourService;
import com.macrosan.vo.WorkHourVo;

@Service
public class WorkHourServiceImpl implements WorkHourService {
	
	@Autowired
	private WorkHourMapper workHourMapper;

	@Override
	public PageObject<WorkHourVo> findObjects(String proName, Integer pageCurrent) {
		if(pageCurrent == null || pageCurrent < 1) throw new IllegalArgumentException("当前页码不正确");
		int rowCount = workHourMapper.getRowCount(proName);
		if(rowCount == 0) {
			throw new ServiceException("未找到相关记录");
		};
		int pageSize = 5;
		int startIndex = (pageCurrent-1)*pageSize;
		List<WorkHourVo> WorkHourVo = workHourMapper.findObjects(proName,startIndex,pageSize);
		PageObject<WorkHourVo> pageObject = new PageObject<>();
		pageObject.setPageCount((rowCount-1)/pageSize+1)
				  .setPageCurrent(pageCurrent)
				  .setPageSize(pageSize)
				  .setRecords(WorkHourVo)
				  .setRowCount(rowCount);
		return pageObject;
	}
	
}
