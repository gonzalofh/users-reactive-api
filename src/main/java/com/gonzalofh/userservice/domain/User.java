package com.gonzalofh.userservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@RequiredArgsConstructor
public final class User {

  @Id
  private final String id;
  @NotNull
  private final String firstName;
  @NotNull
  private final String lastName;
  @NotNull
  @JsonFormat(pattern = "yyyy-MM-dd")
  private final Date dateOfBirth;

}
