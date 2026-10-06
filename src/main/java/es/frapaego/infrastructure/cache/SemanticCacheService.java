package es.frapaego.infrastructure.cache;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class SemanticCacheService {

	private final StringRedisTemplate redis;

    public SemanticCacheService(StringRedisTemplate redis) {
        this.redis = redis;
    }

    public String get(String key) {
        return redis.opsForValue().get(key);
    }

    public void put(String key, String value) {
        redis.opsForValue().set(key, value, Duration.ofMinutes(10));
    }

    public String key(String q) {
        return "q:" + q;
    }
}