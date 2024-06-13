package com.example.moviesearcher.repository;

import com.example.moviesearcher.entities.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movies, Long> {
}
