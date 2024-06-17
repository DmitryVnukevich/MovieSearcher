package com.example.moviesearcher.controller;

import com.example.moviesearcher.entities.MovieCrew;
import com.example.moviesearcher.service.MovieCrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie-crew")
public class MovieCrewController {

    private final MovieCrewService movieCrewService;

    @Autowired
    public MovieCrewController(MovieCrewService movieCrewService) {
        this.movieCrewService = movieCrewService;
    }

    @PostMapping
    public MovieCrew createMovieCrew(@RequestBody MovieCrew movieCrew) {
        return movieCrewService.saveMovieCrew(movieCrew);
    }

    @GetMapping
    public List<MovieCrew> getAllMovieCrews() {
        return movieCrewService.findAllMovieCrews();
    }

    @GetMapping("/{id}")
    public MovieCrew getMovieCrewById(@PathVariable Long id) {
        return movieCrewService.findMovieCrewById(id);
    }

    @PutMapping("/{id}")
    public MovieCrew updateMovieCrew(@PathVariable Long id, @RequestBody MovieCrew movieCrewDetails) {
        movieCrewDetails.setId(id);
        return movieCrewService.updateMovieCrew(movieCrewDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteMovieCrew(@PathVariable Long id) {
        movieCrewService.deleteMovieCrew(id);
    }
}
