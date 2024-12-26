package com.example.moviesearcher.repository;

import com.example.moviesearcher.entities.Series;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  SeriesRepository extends JpaRepository<Series, Long> {
}
