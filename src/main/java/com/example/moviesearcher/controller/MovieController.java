package com.example.moviesearcher.controller;

import com.example.moviesearcher.entity.Movie;
import com.example.moviesearcher.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.saveMovie(movie);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.findAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.findMovieById(id);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        movieDetails.setId(id);
        return movieService.updateMovie(movieDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}
