package com.gonzalofh.userservice.domain;

import java.util.function.Function;
import reactor.core.publisher.Mono;

public interface GetUser extends Function<String, Mono<User>> {}
