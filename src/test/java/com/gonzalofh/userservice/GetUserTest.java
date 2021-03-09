package com.gonzalofh.userservice;

import com.gonzalofh.userservice.domain.User;
import com.gonzalofh.userservice.infrastructure.UserRepository;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureWebTestClient
class GetUserTest {

	@Autowired
	WebTestClient client;

	@Autowired
	UserRepository userRepository;

  @Test
  void createUser() {

		userRepository.save(new User("123", "Gonzalo", "Fernández",
				Date.valueOf("1988-04-20"))).block();

		client.get()
				.uri("/v1/users/123")
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.id").isEqualTo("123")
				.jsonPath("$.firstName").isEqualTo("Gonzalo")
				.jsonPath("$.lastName").isEqualTo("Fernández")
				.jsonPath("$.dateOfBirth").isEqualTo("1988-04-20");

	}

}
