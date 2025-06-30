package net.engineeringdigest.journalApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

     @Bean
     public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
         RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
         redisTemplate.setConnectionFactory(factory);
         redisTemplate.setKeySerializer(new StringRedisSerializer());

         // Use Jackson JSON serializer for values
         GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
         redisTemplate.setValueSerializer(serializer);
         redisTemplate.setHashValueSerializer(serializer);

         return redisTemplate;
     }

}
