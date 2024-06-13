package com.example.moviesearcher.repository;

import com.example.moviesearcher.entities.Movies;
import org.springframework.data.repository.CrudRepository;

public interface MoviesRepository extends CrudRepository<Movies, Long> {
}
