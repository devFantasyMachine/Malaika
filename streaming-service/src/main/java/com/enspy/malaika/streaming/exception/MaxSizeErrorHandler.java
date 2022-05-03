package com.enspy.malaika.streaming.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class MaxSizeErrorHandler {

  private static final Logger logger = LoggerFactory.getLogger("MaxSizeErrorHandler.class");

  @ExceptionHandler(MultipartException.class)
  public Mono<ServerResponse> maxSizeError(MultipartException e) {
    logger.info("Max File Size Exception Occurs");
    return ServerResponse.badRequest().build();

  }

}