package dev.bug.spy;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Decoded {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("user_name")
    private String username;

    @JsonProperty("scope")
    private List<String> scopes = new ArrayList<>();

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    @JsonProperty("exp")
    private Integer exp;

    @JsonProperty("authorities")
    private List<String> authorities = new ArrayList<>();

    @JsonProperty("jti")
    private String jti;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("login")
    private String login;

    @JsonProperty("hash")
    private String hash;
}
