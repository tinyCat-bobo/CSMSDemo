package com.macrosan.service;

import com.macrosan.common.PageObject;
import com.macrosan.vo.WorkHourVo;

public interface WorkHourService {
	PageObject<WorkHourVo> findObjects(String proName, Integer pageCurrent);
}
