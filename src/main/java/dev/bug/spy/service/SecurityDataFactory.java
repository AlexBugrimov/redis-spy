package dev.bug.spy.service;

import dev.bug.spy.model.Decoded;
import dev.bug.spy.model.SecurityData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class SecurityDataFactory {

    public SecurityData create(long id) {
        return SecurityData.builder()
                .key(Long.toString(id))
                .tokenType("Bearer")
                .scope("read, write, create, delete")
                .jti("Jti")
                .accessToken("<access_token>:%s".formatted(UUID.randomUUID()))
                .refreshToken("<refresh_token>:%s".formatted(UUID.randomUUID()))
                .expires(LocalDateTime.now().getSecond())
                .createdAt(LocalDateTime.now())
                .decoded(createDecoded())
                .build();
    }

    private List<Decoded> createDecoded() {
        return List.of(
                Decoded.builder()
                        .username("<username>")
                        .phone("+79150630111")
                        .userId(UUID.randomUUID().toString())
                        .hash("<hash>:%s".formatted(UUID.randomUUID()))
                        .scopes(List.of("read", "write", "delete"))
                        .login("<login>")
                        .authorities(List.of("CLIENT", "ADMIN"))
                        .exp(LocalDateTime.now().getSecond())
                        .jti("Jti")
                        .clientId(UUID.randomUUID().toString())
                        .securityKey("<security_key>")
                        .createdAt(LocalDateTime.now())
                        .build(),
                Decoded.builder()
                        .username("<username>")
                        .phone("+79150639925")
                        .userId(UUID.randomUUID().toString())
                        .hash("<hash>:%s".formatted(UUID.randomUUID()))
                        .scopes(List.of("create", "delete"))
                        .login("<login>")
                        .authorities(List.of("ADMIN"))
                        .exp(LocalDateTime.now().getSecond())
                        .jti("Jti")
                        .clientId(UUID.randomUUID().toString())
                        .securityKey("<security_key>")
                        .createdAt(LocalDateTime.now())
                        .build()
        );
    }
}
