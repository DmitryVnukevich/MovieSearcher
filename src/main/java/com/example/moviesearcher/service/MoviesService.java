package com.example.moviesearcher.service;

import com.example.moviesearcher.entities.Movies;
import com.example.moviesearcher.repository.MoviesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MoviesService {

    private final MoviesRepository moviesRepository;

    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }

    @Transactional
    public Movies saveMovie(Movies movie) {
        return moviesRepository.save(movie);
    }

    public List<Movies> findAllMovies() {
        return moviesRepository.findAll();
    }

    public Movies findMovieById(Long id) {
        return moviesRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteMovie(Long id) {
        moviesRepository.deleteById(id);
    }
}
