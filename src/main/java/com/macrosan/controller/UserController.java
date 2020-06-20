package com.macrosan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.User;
import com.macrosan.pojo.UserRole;
import com.macrosan.service.UserService;
import com.macrosan.vo.SysResult;
import com.macrosan.vo.UserRoleVo;

@RestController
@RequestMapping("/users/")
public class UserController {
	@Autowired
	private UserService userService;

	//http://localhost:8100/users/doGetObjects?pageCurrent=1 
	@RequestMapping("doGetPageObjects")
	public SysResult doGetAllUsers(String userName,Integer pageCurrent) {
		PageObject<User> pageObject = userService.getPageobjects(userName,pageCurrent);
		return SysResult.success(pageObject);
	}
	//http://localhost:8100/users/doGetUserRoleById?id=35
	@RequestMapping("doGetUserRoleById")
	public SysResult doGetUserRole(Integer id) {
		List<UserRoleVo> userRole = userService.getUserRoleById(id);
		return SysResult.success(userRole);
	}
	//http://localhost:8100/user/doSaveObject
	@RequestMapping("doSaveObject")
	public SysResult doSaveUserObject(User user,Integer[] roleIds) {
		int row = userService.saveUserObject(user,roleIds);
		return SysResult.success(row);
	}
	//http://localhost:8100/users/doDeleteObject
	@RequestMapping("doDeleteObject")
	public SysResult deDeleteUserObject(Integer id) {
		int row = userService.deleteUserObject(id);
		return SysResult.success(row);
	}
	//http://localhost:8100/users/doGetUserById?id=45 
	@RequestMapping("doGetUserById")
	public SysResult doGetUserById(Integer id) {
		User user = userService.findUserById(id);
		return SysResult.success(user);
	}
	//http://localhost:8100/users/doGetUserRolesById?id=45
	@RequestMapping("doGetUserRolesById")
	public SysResult doGetSelectedUserRole(Integer id) {
		List<UserRole> userRole = userService.getUserRolesById(id);
		return SysResult.success(userRole);
	}
	
}
