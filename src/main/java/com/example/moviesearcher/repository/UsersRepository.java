package com.example.moviesearcher.repository;

import com.example.moviesearcher.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
