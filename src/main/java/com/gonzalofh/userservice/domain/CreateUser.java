package com.gonzalofh.userservice.domain;

import java.util.UUID;
import java.util.function.Function;

public interface CreateUser extends Function<UserCreateCommand, User> {

  default User apply(UserCreateCommand userCreateCommand) {
    String id = UUID.randomUUID().toString();
    return new User(
        id,
        userCreateCommand.getFirstName(),
        userCreateCommand.getLastName(),
        userCreateCommand.getDateOfBirth()
    );
  }

}
