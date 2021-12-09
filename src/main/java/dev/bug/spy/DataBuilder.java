package dev.bug.spy;

import dev.bug.spy.model.Decoded;
import dev.bug.spy.model.SecurityData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Component
public class DataBuilder {

    public SecurityData buildSecurityData(int index) {
        return SecurityData.builder()
                .key(index + "-" + UUID.randomUUID())
                .accessToken(UUID.randomUUID().toString())
                .refreshToken(UUID.randomUUID().toString())
                .jti(UUID.randomUUID().toString())
                .createdAt(LocalDateTime.now())
                .decodedList(List.of(buildDecoded(), buildDecoded()))
                .expires(new Random().nextInt())
                .build();
    }

    private Decoded buildDecoded() {
        return Decoded.builder()
                .clientId(UUID.randomUUID().toString())
                .exp(new Random().nextInt())
                .jti(UUID.randomUUID().toString())
                .hash("hash#" + UUID.randomUUID())
                .login(String.valueOf(new Random().nextInt()))
                .authorities(List.of("CLIENT", "ADMIN"))
                .phone(String.valueOf(new Random().nextInt()))
                .createdAt(LocalDateTime.now())
                .scopes(List.of("create", "delete", "update"))
                .userId(UUID.randomUUID().toString())
                .username("Username|" + UUID.randomUUID())
                .build();
    }
}
