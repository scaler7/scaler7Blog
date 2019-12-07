package com.scaler7.config;

import java.time.Duration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * 
* @ClassName: scaler7
* @Description: redis配置类 
* @author Zzz  
* @date 2019年12月4日  
*
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {
	
	private Log log = LogFactory.getLog(RedisConfig.class);
	
	private Duration timeToLive = Duration.ofMinutes(30);
	
	@Override
	public CacheManager cacheManager() {
		
		return super.cacheManager();
	}

	@Bean
	public RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties) {
		
		CacheProperties.Redis redisProperties = cacheProperties.getRedis();
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
		config = config
				.serializeValuesWith(
				RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
				.entryTtl(timeToLive);
		if(redisProperties.getTimeToLive() != null) {
			config = config.entryTtl(redisProperties.getTimeToLive());
		}
		
		if(redisProperties.getKeyPrefix() != null) {
			config = config.prefixKeysWith(redisProperties.getKeyPrefix());
		}
		
		if(!redisProperties.isCacheNullValues()) {
			config = config.disableCachingNullValues();
		}
		
		if(!redisProperties.isUseKeyPrefix()) {
			config = config.disableKeyPrefix();
		}
		return config;
	}

	@Override
	@Bean
	public CacheErrorHandler errorHandler() {
		log.error("异常处理RedisCacheErrorHandler初始化...........");
		CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
			
			@Override
			public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
				log.error("-----缓存Put异常,出现异常的key值为:"+key+"-----");
			}
			
			@Override
			public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
				log.error("-----缓存Get异常,出现异常的key值为:"+key+"-----");
			}
			
			@Override
			public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
				log.error("-----缓存Evict异常,出现异常的key值为:"+key+"-----");
			}
			
			@Override
			public void handleCacheClearError(RuntimeException exception, Cache cache) {
				log.error("-----缓存Clear异常-----");
			}
		};
		return cacheErrorHandler;
	}
	
	
}
