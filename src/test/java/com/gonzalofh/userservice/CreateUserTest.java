package com.gonzalofh.userservice;

import com.gonzalofh.userservice.domain.UserCreateCommand;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureWebTestClient
class CreateUserTest {

	@Autowired
	WebTestClient client;

  @Test
  void createUser() {

    UserCreateCommand userCreateCommand = new UserCreateCommand("Gonzalo", "Fernández",
        Date.valueOf("1988-04-20"));

		client.post()
				.uri("/v1/users")
				.body(BodyInserters.fromValue(userCreateCommand))
				.exchange()
				.expectStatus().isCreated()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.id").isNotEmpty()
				.jsonPath("$.firstName").isEqualTo("Gonzalo")
				.jsonPath("$.lastName").isEqualTo("Fernández")
				.jsonPath("$.dateOfBirth").isEqualTo("1988-04-20");

	}

}
