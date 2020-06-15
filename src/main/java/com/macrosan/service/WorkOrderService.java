package com.macrosan.service;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.WorkOrder;

public interface WorkOrderService {
	PageObject<WorkOrder> findObjects(String orderName, Integer pageCurrent);
	int deleteObjects(Integer[] ids);
}
