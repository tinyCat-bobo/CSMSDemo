package com.macrosan.service;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.SpareAddress;

public interface SpareService {
	PageObject<SpareAddress> getAllAddress(Integer pageCurrent);
	int saveSpareAddress(SpareAddress spareAddress);
	int deleteSpareAddress(Integer id);
	SpareAddress FindSpareAddressById(Integer id);
	int updateSpareAddress(SpareAddress spareAddress);
}
