package com.macrosan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.macrosan.pojo.SpareAddress;

@Mapper
public interface SpareMapper {
	List<SpareAddress> getAllAddress(Integer pageSize, Integer startIndex);
	int getRowCount();
	int saveSpareAddress(@Param("spareAddress")SpareAddress spareAddress);
	int deleteSpareAddress(Integer id);
	SpareAddress FindSpareAddressById(Integer id);
	int updateSpareAddress(@Param("address")SpareAddress spareAddress);
}
