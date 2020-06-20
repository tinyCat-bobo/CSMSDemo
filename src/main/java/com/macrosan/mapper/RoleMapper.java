package com.macrosan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.macrosan.pojo.Role;

@Mapper
public interface RoleMapper {
	List<Role> getAllRoles();
}
