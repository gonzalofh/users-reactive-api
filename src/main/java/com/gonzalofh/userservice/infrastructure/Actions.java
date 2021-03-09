package com.gonzalofh.userservice.infrastructure;

import com.gonzalofh.userservice.domain.GetUser;
import com.gonzalofh.userservice.domain.SaveUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Actions {

  @Bean
  SaveUser saveUserFn(UserRepository userRepository) {
    return userRepository::save;
  }

  @Bean
  GetUser getUserFn(UserRepository userRepository) {
    return userRepository::findById;
  }

}
