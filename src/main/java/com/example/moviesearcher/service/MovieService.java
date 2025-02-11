package com.example.moviesearcher.service;

import com.example.moviesearcher.entity.Movie;
import com.example.moviesearcher.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Movie findMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Transactional
    public Movie updateMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
