package dev.bug.spy;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class AuthData {

    private String tokenType;
    private String scope;
    private OffsetDateTime createdAt;
    private String jti;
}
