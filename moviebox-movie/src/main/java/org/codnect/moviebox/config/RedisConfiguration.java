package org.codnect.moviebox.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;


@EnableCaching
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {

    public static final String CACHE_KEY_SEPERATOR = "#";

    private CacheProperties cacheProperties;

    @Autowired
    public RedisConfiguration(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getSimpleName());
            sb.append(CACHE_KEY_SEPERATOR);
            sb.append(method.getName());
            sb.append(CACHE_KEY_SEPERATOR);
            for(Object param : params){
                sb.append(param.toString());
                sb.append(CACHE_KEY_SEPERATOR);
            }
            String str = sb.toString();
            return str.substring(0, str.length() - 1);
        };
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
       // RedisCacheManager manager = new RedisCacheManager(redisTemplate);
       // manager.setExpires(cacheProperties.getExpirations());
        return null;
    }

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory factory) {
        final StringRedisTemplate template = new StringRedisTemplate(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
