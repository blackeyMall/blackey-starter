package com.blackey.redis.config;

import com.blackey.redis.serializer.KryoSerialzer;
import com.blackey.redis.service.RedisLockService;
import com.blackey.redis.service.RedisService;
import com.blackey.redis.service.impl.RedisLockServiceImpl;
import com.blackey.redis.service.impl.RedisServiceImpl;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis Config
 * Created by Kaven
 * Date: 2018/3/15
 * Desc:
 */
@Configuration
@EnableCaching
public class RedisAutoConfiguration extends CachingConfigurerSupport{

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisAutoConfiguration.class);

    @Value("${redis.serializer.name:jdk}")
    private String serializerName;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheManager cacheManager = RedisCacheManager.create(factory);
        return cacheManager;
    }

    @Bean("redisService")
    public RedisService initRedisService(){
        return new RedisServiceImpl();
    }

    @Bean("redisLockService")
    public RedisLockService initRedisLockService(){
        return new RedisLockServiceImpl();
    }

    /**
     *  实例化 RedisTemplate 对象
     * @return RedisTemplate
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory factory) {
        LOGGER.info("初始化RedisTemplate");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(getRedisSerializer());
        redisTemplate.setValueSerializer(getRedisSerializer());
        redisTemplate.afterPropertiesSet();
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

    /**
     * RedisSerializer 序列化方式
     * @return RedisSerializer
     */
    private RedisSerializer getRedisSerializer(){

        if("jdk".equalsIgnoreCase(serializerName)){
            return new JdkSerializationRedisSerializer();
        }else if("kryo".equalsIgnoreCase(serializerName)){
            return new KryoSerialzer();
        }else if("jackson".equalsIgnoreCase(serializerName)){
            Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
            return jackson2JsonRedisSerializer;
        }else {
            return new JdkSerializationRedisSerializer();
        }
    }
}
