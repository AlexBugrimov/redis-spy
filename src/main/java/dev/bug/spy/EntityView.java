package dev.bug.spy;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash("entity")
public class EntityView {

    @Id
    private String key;
    private AuthData data;
}
