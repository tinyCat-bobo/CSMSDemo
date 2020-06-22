package com.macrosan.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration		//注解描述一个对象为类对象
public class ShiroConfig {
	
	//添加安全管理器,并交给spring管理
	@Bean
	public SecurityManager securityManager(Realm realm) {
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		return sManager;
	}
	
	//添加ShiroFilterFactoryBean对象,通过此对象设置资源的匿名访问,认证访问等
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean sFBean = new ShiroFilterFactoryBean();
		sFBean.setSecurityManager(securityManager);
		//设置登录拦截页面(登录页面)
		sFBean.setLoginUrl("/doLoginUI");
		//定义Map指定请求过滤规则(哪些资源允许匿名访问,哪些资源需要认证访问)
		LinkedHashMap<String, String> filterChainMap = new LinkedHashMap<>();
		filterChainMap.put("/bower_components/**", "anon");
		filterChainMap.put("/dist/**", "anon");
		filterChainMap.put("/plugins/**", "anon");
		filterChainMap.put("/imgs/**", "anon");
		filterChainMap.put("/users/doLogin", "anon");	//允许该路径匿名访问
		filterChainMap.put("/doLogout", "logout");	//允许该路径匿名访问
		//除了静态资源可以匿名访问,其他资源均需要认证访问
		filterChainMap.put("/**", "authc");
		sFBean.setFilterChainDefinitionMap(filterChainMap);
		return sFBean;
	}
}
