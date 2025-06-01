package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.MovieCrewDTO;
import com.example.moviesearcher.service.MovieCrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/movie-crew")
@RequiredArgsConstructor
public class MovieCrewController {

    private final MovieCrewService movieCrewService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    public MovieCrewDTO createMovieCrew(@Valid @RequestBody MovieCrewDTO movieCrewDTO) {
        return movieCrewService.createMovieCrew(movieCrewDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    public MovieCrewDTO updateMovieCrew(@PathVariable Long id, @Valid @RequestBody MovieCrewDTO movieCrewDTO) {
        movieCrewDTO.setId(id);
        return movieCrewService.updateMovieCrew(movieCrewDTO);
    }

    @GetMapping("/movie/{movieId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public List<MovieCrewDTO> findCrewByMovieId(@PathVariable Long movieId) {
        return movieCrewService.findCrewByMovieId(movieId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    public void deleteMovieCrew(@PathVariable Long id) {
        movieCrewService.deleteMovieCrew(id);
    }

    @DeleteMapping("/movie/{movieId}")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    public void deleteByMovieId(@PathVariable Long movieId) {
        movieCrewService.deleteByMovieId(movieId);
    }
}
