package com.example.moviesearcher.service;

import com.example.moviesearcher.entities.MovieCrew;
import com.example.moviesearcher.repository.MovieCrewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovieCrewService {

    private final MovieCrewRepository movieCrewRepository;

    public MovieCrewService(MovieCrewRepository movieCrewRepository) {
        this.movieCrewRepository = movieCrewRepository;
    }

    @Transactional
    public MovieCrew saveMovieCrew(MovieCrew movieCrew) {
        return movieCrewRepository.save(movieCrew);
    }

    public List<MovieCrew> findAllMovieCrews() {
        return movieCrewRepository.findAll();
    }

    public MovieCrew findMovieCrewById(Long id) {
        return movieCrewRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteMovieCrew(Long id) {
        movieCrewRepository.deleteById(id);
    }

    @Transactional
    public MovieCrew updateMovieCrew(MovieCrew movieCrew) {
        return movieCrewRepository.save(movieCrew);
    }
}
