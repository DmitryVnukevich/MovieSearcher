package com.example.moviesearcher.repository;

import com.example.moviesearcher.entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
}
