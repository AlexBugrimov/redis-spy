package dev.bug.spy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Locale;

@SpringBootApplication
public class RedisSpyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisSpyApplication.class, args);
    }

}
