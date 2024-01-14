package com.example.springbootflightsearchapi.repository;

import com.example.springbootflightsearchapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String userName);

    Optional<User> findByEmail(String email);
}
