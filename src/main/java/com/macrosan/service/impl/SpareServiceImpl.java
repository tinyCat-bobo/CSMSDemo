package com.macrosan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.macrosan.common.PageObject;
import com.macrosan.common.exception.ServiceException;
import com.macrosan.mapper.SpareMapper;
import com.macrosan.pojo.SpareAddress;
import com.macrosan.service.SpareService;

@Service
public class SpareServiceImpl implements SpareService {
	@Autowired
	private SpareMapper spareMapper;

	@Override
	public PageObject<SpareAddress> getAllAddress(Integer pageCurrent) {
		if (pageCurrent == null || pageCurrent < 1) {
			throw new IllegalArgumentException("页码参数错误");
		}
		int rowCount = spareMapper.getRowCount();
		if (rowCount == 0) {
			throw new ServiceException("未找到相关记录");
		}
		Integer pageSize = 5;
		Integer startIndex = (pageCurrent - 1) * pageSize;
		List<SpareAddress> addressList = spareMapper.getAllAddress(pageSize, startIndex);
		PageObject<SpareAddress> pageObject = new PageObject<>(addressList, rowCount, pageCurrent, pageSize);
		return pageObject;
	}

	@Override
	public int saveSpareAddress(SpareAddress spareAddress) {
		if (spareAddress == null) {
			throw new IllegalArgumentException("提交参数不能为空");
		}
		if (StringUtils.isEmpty(spareAddress.getAddress())) {
			throw new IllegalArgumentException("备件地址不能为空");
		}
		int row = spareMapper.saveSpareAddress(spareAddress);
		return row;
	}

	@Override
	public int deleteSpareAddress(Integer id) {
		if (id == null || id < 1) {
			throw new IllegalArgumentException("ID参数非法");
		}
		int row = spareMapper.deleteSpareAddress(id);
		if (row == 0) {
			throw new ServiceException("记录可能已经不存在");
		}
		return row;
	}

	@Override
	public SpareAddress FindSpareAddressById(Integer id) {
		if (id == null || id < 1) {
			throw new IllegalArgumentException("ID参数非法");
		}
		SpareAddress address = spareMapper.FindSpareAddressById(id);
		if(address == null) {
			throw new ServiceException("未找到相关记录");
		}
		return address;
	}

	@Override
	public int updateSpareAddress(SpareAddress spareAddress) {
		if(StringUtils.isEmpty(spareAddress.getId())) {
			throw new IllegalArgumentException("ID不能为空"); 
		}
		int row = spareMapper.updateSpareAddress(spareAddress);
		if(row == 0) {
			throw new ServiceException("记录更新失败");
		}
		return row;
	}

}
