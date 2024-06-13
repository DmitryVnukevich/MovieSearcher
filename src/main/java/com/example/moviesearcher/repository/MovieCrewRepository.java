package com.example.moviesearcher.repository;

import com.example.moviesearcher.entities.MovieCrew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCrewRepository extends JpaRepository<MovieCrew, Long> {
}
