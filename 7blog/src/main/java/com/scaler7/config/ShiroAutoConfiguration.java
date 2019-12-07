package com.scaler7.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "shiro")
public class ShiroAutoConfiguration {
	
	//默认加密方式
	private String hashAlgorithmName = "md5";
	//默认加密散列次数
	private Integer hashIterations = 2;
	//
	private String targetBeanName = "shiroFilter";
	
	private boolean targetFilterLifecycle = true;
	private String loginUrl = "/login.html";// 未登陆跳转
	private String unauthorizedUrl = "/unauthorized.html";// 未授权跳转
	
	private String[] anonUrls;
	private String[] authcUrls;
	
	/**
	 * 声明凭证匹配器，并注入属性
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = 
				new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName(hashAlgorithmName);
		hashedCredentialsMatcher.setHashIterations(hashIterations);
		return hashedCredentialsMatcher;
	}
	
	/**
	 * 声明userRolem 并注入凭证匹配器
	 * @param hashedCredentialsMatcher
	 * @return
	 */
	@Bean
	public UserRealm userRolem(HashedCredentialsMatcher hashedCredentialsMatcher) {
		UserRealm userRolem = new UserRealm();
		userRolem.setCredentialsMatcher(hashedCredentialsMatcher);
		return userRolem;
	}
	
	/**
	 * 声明安全管理器，并注入userRolem
	 * @param userRolem
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(UserRealm userRolem) {
		DefaultWebSecurityManager securityManager = new 
				DefaultWebSecurityManager();
		securityManager.setRealm(userRolem);
		return securityManager;
	}
	
	@Bean
	public FilterRegistrationBean<DelegatingFilterProxy> filterRegistrationBeanDelegatingFilterProxy(){
		//创建注册器
		FilterRegistrationBean<DelegatingFilterProxy> registrationBean = 
				new FilterRegistrationBean<DelegatingFilterProxy>();
		//创建过滤器
		DelegatingFilterProxy proxy = new DelegatingFilterProxy();
		//设置过滤参数
		proxy.setTargetFilterLifecycle(targetFilterLifecycle);
		proxy.setTargetBeanName(targetBeanName);
		//注册
		registrationBean.setFilter(proxy);
		Collection<String> servletNames = new ArrayList<>();
		servletNames.add(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
		registrationBean.setServletNames(servletNames);
		return registrationBean;
	}
	
	@Bean(value = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		//注入安全管理器
		factoryBean.setSecurityManager(securityManager);
		//设置未登录跳转的页面
		factoryBean.setLoginUrl(loginUrl);
		//未授权跳转的页面
		factoryBean.setUnauthorizedUrl(unauthorizedUrl);
		
		//设置放行url
		Map<String, String> filterChainDefinitionMap = new HashMap<String, String>();
		if(null!=anonUrls && anonUrls.length>0) {
			for(String url:anonUrls) {
				filterChainDefinitionMap.put(url, "anon");
			}
		}
		//设置拦截的url
		if(null!=authcUrls && authcUrls.length>0) {
			for (String url : authcUrls) {
				filterChainDefinitionMap.put(url, "authc");
			}
		}
		factoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		Map<String, Filter> filters = new HashMap<>();
		filters.put("authc", new FormAuthenticationFilter());
		//重写authc过滤器
		factoryBean.setFilters(filters);
		
		return factoryBean;
	}
	
	
	//这里是为了能在html标签里引用shiro标签
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
				new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
	
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator =
				new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}
	
}
