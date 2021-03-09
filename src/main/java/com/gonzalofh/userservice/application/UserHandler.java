package com.gonzalofh.userservice.application;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import com.gonzalofh.userservice.domain.CreateUser;
import com.gonzalofh.userservice.domain.GetUser;
import com.gonzalofh.userservice.domain.SaveUser;
import com.gonzalofh.userservice.domain.UserCreateCommand;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserHandler {

  private final MessageSource messageSource;
  private final SaveUser saveUserFn;
  private final GetUser getUserFn;
  private final CreateUser createUserFn = new CreateUser() {};
  private final Validator validator = new CreateCommandValidator();

  public Mono<ServerResponse> createUser(ServerRequest serverRequest) {
    return serverRequest.bodyToMono(UserCreateCommand.class)
        .doOnNext(this::validateCreateCommand)
        .map(createUserFn)
        .flatMap(saveUserFn)
        .flatMap(user -> ServerResponse.status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(user)));
  }

  public Mono<ServerResponse> getUser(ServerRequest serverRequest) {
    String userId = serverRequest.pathVariable("id");
    return getUserFn.apply(userId)
        .flatMap(student -> ServerResponse.ok().body(fromValue(student)))
        .switchIfEmpty(ServerResponse.notFound().build());
  }

  private void validateCreateCommand(UserCreateCommand createCommand) {
    Errors errors = new BeanPropertyBindingResult(createCommand, "createCommand");
    validator.validate(createCommand, errors);
    if (errors.hasErrors()) {
      throw new ServerWebInputException(errors.getAllErrors()
          .stream()
          .map(objectError -> messageSource.getMessage(objectError, Locale.getDefault()))
          .collect(Collectors.toList()).toString());
    }
  }

}
