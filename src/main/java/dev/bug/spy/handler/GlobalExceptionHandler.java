package dev.bug.spy.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.exceptions.JedisConnectionException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JedisConnectionException.class)
    public ModelAndView connectionErrorHandler() {
        return new ModelAndView("index");
    }
}
