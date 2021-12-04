package dev.bug.spy;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SpyService {

    private final RedisTemplate<String, Object> redisTemplate;

    public Long put(String key, Object data) {
        return redisTemplate.opsForList().leftPush(key, data);
    }

    public Long clearStore() {
        Set<String> keys = getKeys();
        if (Objects.nonNull(keys) && keys.size() > 0) {
            return redisTemplate.delete(keys);
        }
        return 0L;
    }

    public Page<Object> getAllByPage(int page, int pageSize) {
        Set<String> keys = getKeys();
        if (keys.isEmpty()) {
            return Page.empty();
        }
        Set<Object> objects = redisTemplate.opsForSet().union(keys);
        if (Objects.isNull(objects) || objects.isEmpty()) {
            return Page.empty();
        }
        return Page.toPage(objects, page, pageSize);

    }

    private Set<String> getKeys() {
        return redisTemplate.keys("*");
    }
}
