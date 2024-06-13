package com.example.moviesearcher.repository;

import com.example.moviesearcher.entities.Episodes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodesRepository extends JpaRepository<Episodes, Long> {
}
