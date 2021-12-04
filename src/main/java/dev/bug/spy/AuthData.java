package dev.bug.spy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AuthData {

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    @JsonProperty("jti")
    private String jti;

    @JsonProperty("decoded")
    private List<Decoded> decodedList = new ArrayList<>();

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("expires")
    private Integer expires;
}
