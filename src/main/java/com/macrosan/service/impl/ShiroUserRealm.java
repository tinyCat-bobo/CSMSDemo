package com.macrosan.service.impl;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macrosan.mapper.UserMapper;
import com.macrosan.pojo.User;

@Service
public class ShiroUserRealm extends AuthorizingRealm {
	
	@Autowired
	private UserMapper userMapper;
	
	/*
	 * 设置凭证管理器(与用户添加操作使用相同的加密算法) 
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		//构建凭证匹配对象
		HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
		//设置加密算法
		cMatcher.setHashAlgorithmName("MD5");
		//设置加密次数
		cMatcher.setHashIterations(1);
		super.setCredentialsMatcher(cMatcher);
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		return null;
	}

	/*
	 * 通过此方法完成认证数据的获取及封装,系统底层会将认证数据传递给认证管理器,由认证管理器完成认证操作
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.获取用户名,先将token转换为UsernamePasswordToken
		UsernamePasswordToken uToken = (UsernamePasswordToken)token;
		String username = uToken.getUsername();
		//基于用户名查询用户信息
		User userFinded = userMapper.findUserByUserName(username);
		if(userFinded == null) {
			throw new UnknownAccountException();
		}
		//判断用户是否被禁用
		if(!userFinded.getValid()) {
			throw new LockedAccountException();
		}
		//获取盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(userFinded.getSalt());
		//封装用户信息
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
								userFinded, 		//principal(身份)
								userFinded.getPassword(), 	//密码
								credentialsSalt, 	//盐值
								getName()			//realName
								);
		return info;	//返回封装结果,返回结果会传递给认证管理器(后续认证管理器会通过此信息完成认证)
	}

}
