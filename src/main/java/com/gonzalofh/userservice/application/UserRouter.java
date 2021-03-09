package com.gonzalofh.userservice.application;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouter {

  @Bean
  public RouterFunction<ServerResponse> route(UserHandler userHandler) {
    return RouterFunctions
        .route(GET("/v1/users/{id}").and(accept(APPLICATION_JSON)), userHandler::getUser)
        .andRoute(POST("/v1/users").and(accept(APPLICATION_JSON)), userHandler::createUser);
  }

}