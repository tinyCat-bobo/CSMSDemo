package com.macrosan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.macrosan.pojo.WorkOrder;

@Mapper
public interface WorkOrderMapper {
	List<WorkOrder> findObjects(@Param("orderName")String orderName, 
								@Param("startIndex")Integer startIndex,
								@Param("pageSize")Integer pageSize);
	Integer getRowCount(@Param("orderName")String orderName);
	int saveObject(WorkOrder workOrder);
	int deleteObjects(@Param("ids")Integer[] ids);
}
