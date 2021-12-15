package dev.bug.spy.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import redis.clients.jedis.exceptions.JedisConnectionException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JedisConnectionException.class)
    public String connectionErrorHandler() {
        return "index";
    }
}
