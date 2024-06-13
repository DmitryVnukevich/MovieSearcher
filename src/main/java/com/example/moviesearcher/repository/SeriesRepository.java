package com.example.moviesearcher.repository;

import com.example.moviesearcher.entities.Series;
import org.springframework.data.repository.CrudRepository;

public interface  SeriesRepository extends CrudRepository<Series, Long> {
}
