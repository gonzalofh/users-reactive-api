package com.gonzalofh.userservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public final class UserCreateCommand {

  @NotNull
  private final String firstName;
  @NotNull
  private final String lastName;
  @NotNull
  @JsonFormat(pattern = "MM-dd-yyyy")
  private final Date dateOfBirth;

}