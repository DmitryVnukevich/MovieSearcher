package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.MovieDTO;
import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MovieDTO createMovie(@RequestBody MovieDTO movieDTO) {
        return movieService.saveMovie(movieDTO);
    }

    @GetMapping("/{id}")
    public MovieDTO getMovieById(@PathVariable Long id) {
        MovieDTO movie = movieService.findMovieById(id);
        if (movie == null) {
            throw new IllegalArgumentException("Movie not found with ID: " + id);
        }
        return movie;
    }

    @GetMapping("/recommendations")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public Page<MovieDTO> getRecommendedMovies(
            @RequestBody UserInfoDTO userInfoDTO,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "7") int size) {
        return movieService.findMoviesByPreferences(userInfoDTO, page, size);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public MovieDTO updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        movieDTO.setId(id);
        return movieService.updateMovie(movieDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteMovie(@PathVariable Long id) {
        if (movieService.findMovieById(id) == null) {
            throw new IllegalArgumentException("Movie not found with ID: " + id);
        }
        movieService.deleteMovie(id);
    }
}