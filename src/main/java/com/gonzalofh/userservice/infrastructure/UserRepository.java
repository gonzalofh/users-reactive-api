package com.gonzalofh.userservice.infrastructure;

import com.gonzalofh.userservice.domain.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {}