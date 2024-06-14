package com.example.moviesearcher.controller;

import com.example.moviesearcher.entities.Movies;
import com.example.moviesearcher.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MoviesController {

    private final MoviesService moviesService;

    @Autowired
    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @PostMapping
    public Movies createMovie(@RequestBody Movies movie) {
        return moviesService.saveMovie(movie);
    }

    @GetMapping
    public List<Movies> getAllMovies() {
        return moviesService.findAllMovies();
    }

    @GetMapping("/{id}")
    public Movies getMovieById(@PathVariable Long id) {
        return moviesService.findMovieById(id);
    }

    @PutMapping("/{id}")
    public Movies updateMovie(@PathVariable Long id, @RequestBody Movies movieDetails) {
        movieDetails.setId(id);
        return moviesService.updateMovie(movieDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        moviesService.deleteMovie(id);
    }
}
