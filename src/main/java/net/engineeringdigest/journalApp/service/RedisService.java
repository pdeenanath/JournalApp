package net.engineeringdigest.journalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public <T> T get(String key, Class<T> entryClass) {
        try {
            String json = (String) redisTemplate.opsForValue().get(key);
            if (json == null) {
                log.info("Cache miss for key: {}", key);
                return null;
            }
            log.info("Cache HIT for key: {}", key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, entryClass);
        } catch (Exception e) {
            log.error("Redis GET Exception", e);
            return null;
        }

    }
    public void set(String key, Object o,Long ttl) {
        String jsonValue = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonValue = objectMapper.writeValueAsString(o);
            log.info("Caching weather data. Key: {}, Value: {}", key, jsonValue);
            redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);

        } catch (Exception e) {
            log.error("Redis SET Exception", e);

        }

    }
}
