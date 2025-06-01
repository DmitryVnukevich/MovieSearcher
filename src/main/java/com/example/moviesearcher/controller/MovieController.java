package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.MovieDTO;
import com.example.moviesearcher.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MovieDTO createMovie(@Valid @RequestBody MovieDTO movieDTO) {
        return movieService.saveMovie(movieDTO);
    }

    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable Long id) {
        MovieDTO movie = movieService.findMovieById(id);
        return movie;
    }

    @GetMapping
    public PagedModel<MovieDTO> getMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        return movieService.findAllMovies(page, size);
    }

    @GetMapping("/by-preferences")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public PagedModel<MovieDTO> getMoviesByPreferences(
            @RequestParam("id") Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        return movieService.findMoviesByPreferences(userId, page, size);
    }

    @GetMapping("/search")
    public PagedModel<MovieDTO> searchMovies(
            @RequestParam("query") String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "12") int size) {
        return movieService.searchMoviesByTitle(query, page, size);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MovieDTO updateMovie(@PathVariable Long id, @Valid @RequestBody MovieDTO movieDTO) {
        movieDTO.setId(id);
        return movieService.updateMovie(movieDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}