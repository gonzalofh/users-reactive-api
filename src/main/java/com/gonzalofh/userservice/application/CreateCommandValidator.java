package com.gonzalofh.userservice.application;

import com.gonzalofh.userservice.domain.UserCreateCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

class CreateCommandValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return UserCreateCommand.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    validateEmptys(errors, "firstName", "lastName", "dateOfBirth");
  }

  private static void validateEmptys(Errors errors, String ...fields) {
    for (String field : fields) {
      ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, "user_field.empty",
          new Object[]{ field }, null);
    }
  }
}
