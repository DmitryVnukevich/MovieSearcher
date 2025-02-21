package com.example.moviesearcher.repository;

import com.example.moviesearcher.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    //User findByUsername(String username);
}
