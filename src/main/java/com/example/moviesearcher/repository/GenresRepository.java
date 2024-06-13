package com.example.moviesearcher.repository;

import com.example.moviesearcher.entities.Genres;
import org.springframework.data.repository.CrudRepository;

public interface GenresRepository extends CrudRepository<Genres, Long> {
}
