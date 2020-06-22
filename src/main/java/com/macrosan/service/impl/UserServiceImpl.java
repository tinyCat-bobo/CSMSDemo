package com.macrosan.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.macrosan.common.PageObject;
import com.macrosan.common.exception.ServiceException;
import com.macrosan.mapper.UserMapper;
import com.macrosan.mapper.UserRoleMapper;
import com.macrosan.pojo.User;
import com.macrosan.pojo.UserRole;
import com.macrosan.service.UserService;
import com.macrosan.vo.UserRoleVo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public PageObject<User> getPageobjects(String username, Integer pageCurrent) {
		if (pageCurrent == null || pageCurrent < 1) {
			throw new IllegalArgumentException("页码参数错误");
		}
		int rowCount = userMapper.getRowCount(username);
		if (rowCount == 0) {
			throw new ServiceException("未找到相关记录");
		}
		Integer pageSize = 5;
		Integer startIndex = (pageCurrent - 1) * pageSize;
		List<User> userList = userMapper.getUserObjects(username,startIndex,pageSize);
		PageObject<User> pageObject = new PageObject<>(userList, rowCount, pageCurrent, pageSize);
		return pageObject;
	}

	@Override
	public List<UserRoleVo> getUserRoleById(Integer id) {
		if(id == null || id < 1) {
			throw new IllegalArgumentException("ID参数非法");
		}
		List<UserRoleVo> userRole = userMapper.getUserRole(id);
		return userRole;
	}

	@Transactional
	@Override
	public int saveUserObject(User user, Integer[] roleIds) {
		//参数校验
		if(StringUtils.isEmpty(user.getUsername())) {
			throw new IllegalArgumentException("用户名不能为空");
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			throw new IllegalArgumentException("用户密码不能为空");
		}
		if(StringUtils.isEmpty(user.getDeptName())) {
			throw new IllegalArgumentException("用户部门不能为空");
		}
		if(roleIds.length == 0 || roleIds == null) {
			throw new IllegalArgumentException("用户角色不能为空");
		}
		//保存用户信息时,要对用户传输的明文密码进行加密
		String sourcePW = user.getPassword();
		String salt = UUID.randomUUID().toString();
		//使用shiro框架进行加密
		SimpleHash sh = new SimpleHash("MD5", sourcePW, salt, 1);	//加密一次
		user.setSalt(salt).setPassword(sh.toString()).setValid(true);
		int row = userMapper.saveUserObject(user);
		//保存用户角色关系
		userRoleMapper.saveUserRole(user.getId(),roleIds);
		if(row < 1) {
			throw new ServiceException("用户信息保存失败");
		}
		return row;
	}

	@Transactional
	@Override
	public int deleteUserObject(Integer id) {
		if(id == null || id < 1) {
			throw new IllegalArgumentException("ID参数非法");
		}
		int row = userMapper.deleteUserObject(id);
		//删除关联的用户角色信息
		userRoleMapper.deleteUserRole(id);
		if(row == 0) {
			throw new ServiceException("记录可能已经不存在");
		}
		return row;
	}

	@Override
	public User findUserById(Integer id) {
		if(id == null || id < 1) {
			throw new IllegalArgumentException("ID参数非法");
		}
		User user = userMapper.findUserById(id);
		if(user == null) {
			throw new ServiceException("未查询到相关用户");
		}
		return user;
	}

	@Override
	public List<UserRole> getUserRolesById(Integer id) {
		if(id == null || id < 1) {
			throw new IllegalArgumentException("ID参数非法");
		}
		List<UserRole> userRole = userRoleMapper.getUserRolesById(id);
		if(userRole == null) {
			throw new ServiceException("未找到相关角色信息");
		}
		return userRole;
	}
	@Transactional		//开启事物控制
	@Override
	public int updateUserInfo(User user, Integer[] roleIds) {
		//参数校验
		if(StringUtils.isEmpty(user.getUsername())) {
			throw new IllegalArgumentException("用户名不能为空");
		}
		if(StringUtils.isEmpty(user.getDeptName())) {
			throw new IllegalArgumentException("用户部门不能为空");
		}
		if(roleIds.length == 0 || roleIds == null) {
			throw new IllegalArgumentException("用户角色不能为空");
		}
		user.setModifiedTime(new Date());
		int row = userMapper.updateUserInfo(user);
		//更新用户角色信息时,先删除原始角色信息,再插入新的角色信息
		userRoleMapper.deleteUserRole(user.getId());	
		userRoleMapper.saveUserRole(user.getId(),roleIds);
		if(row == 0) {
			throw new ServiceException("用户信息更新失败");
		}
		return row;
	}
}
