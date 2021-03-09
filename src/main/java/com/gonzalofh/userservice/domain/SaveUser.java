package com.gonzalofh.userservice.domain;

import java.util.function.Function;
import reactor.core.publisher.Mono;

public interface SaveUser extends Function<User, Mono<User>> {}
