package dev.bug.spy.configuration;

import dev.bug.spy.service.RedisSpyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RedisAutoConfiguration {

    private final RedisSpyService redisSpyService;

    @Bean
    public ApplicationRunner applicationRunner() {
        return titleRunner("Initialize database", args -> {
            redisSpyService.createDataFrom(20);
        });
    }

    private ApplicationRunner titleRunner(@SuppressWarnings("SameParameterValue") String title,
                                          ApplicationRunner runner) {
        return args -> {
            log.info(title.toUpperCase() + ": ");
            runner.run(args);
        };
    }
}
