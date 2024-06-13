package com.example.moviesearcher.repository;

import com.example.moviesearcher.entities.Genres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenresRepository extends JpaRepository<Genres, Long> {
}
