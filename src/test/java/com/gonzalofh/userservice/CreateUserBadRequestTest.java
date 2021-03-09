package com.gonzalofh.userservice;

import com.gonzalofh.userservice.domain.UserCreateCommand;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureWebTestClient
class CreateUserBadRequestTest {

	@Autowired
	WebTestClient client;

	@Autowired
	MessageSource messageSource;

  @Test
  void createUser() {

    UserCreateCommand userCreateCommand = new UserCreateCommand(" ", "Fernández",
        Date.valueOf("1988-04-20"));

		client.post()
				.uri("/v1/users")
				.body(BodyInserters.fromValue(userCreateCommand))
				.exchange()
				.expectStatus().isBadRequest()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBody()
				.jsonPath("$.error").isEqualTo("Bad Request")
				.jsonPath("$.status").isEqualTo(400)
				.jsonPath("$.path").isEqualTo("/v1/users");
//TODO				.jsonPath("$.message").isEqualTo("['firstName' field is required]");

	}

}
