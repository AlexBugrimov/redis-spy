package dev.bug.spy;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("redis")
public class RedisProperties {

    private String host;
    private Integer port;
}
