package dev.bug.spy.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.index.Indexed;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class SecurityData implements Serializable {

    @Id
    @Indexed
    private String key;

    private String tokenType;

    private String scope;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    private String jti;

    @Reference
    private List<Decoded> decoded;

    private String accessToken;

    private String refreshToken;

    private Integer expires;
}
