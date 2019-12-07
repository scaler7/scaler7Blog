package com.scaler7.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

import lombok.Data;

/**
 * 
* @ClassName: scaler7
* @Description: druid配置类 
* @author Zzz  
* @date 2019年12月4日  
*
 */
@Configuration
@Data
@ConditionalOnClass(value = DruidDataSource.class)
@ConfigurationProperties(prefix = "spring.druid.web")
public class DruidAutoConfiguration {
	
	@Bean(initMethod = "init")
	@ConfigurationProperties(prefix = "spring.druid")
	public DruidDataSource druidDataSource() {
		return new DruidDataSource();
	}
	
}
