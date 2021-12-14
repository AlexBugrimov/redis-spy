package dev.bug.spy.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class Decoded implements Serializable {

    @Indexed
    private String securityKey;

    @Id
    private String userId;

    private String phone;

    private String username;

    private List<String> scopes = new ArrayList<>();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    private Integer exp;

    private List<String> authorities = new ArrayList<>();

    private String jti;

    private String clientId;

    private String login;

    private String hash;
}
