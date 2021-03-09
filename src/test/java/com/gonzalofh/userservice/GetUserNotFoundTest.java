package com.gonzalofh.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureDataMongo
@AutoConfigureWebTestClient
class GetUserNotFoundTest {

	@Autowired
	WebTestClient client;

  @Test
  void createUser() {

		client.get()
				.uri("/v1/users/123")
				.exchange()
				.expectStatus().isNotFound();

	}

}
