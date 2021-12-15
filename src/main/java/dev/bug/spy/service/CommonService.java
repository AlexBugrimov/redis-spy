package dev.bug.spy.service;

import dev.bug.spy.model.SecurityData;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public record CommonService(RedisTemplate<String, SecurityData> redis) {

    public boolean isUp() {
        RedisConnectionFactory connectionFactory = redis.getConnectionFactory();
        if (Objects.nonNull(connectionFactory)) {
            try (RedisConnection connection = connectionFactory.getConnection()) {
                return Objects.equals(connection.ping(), "PONG");
            }
        }
        return false;
    }
}
