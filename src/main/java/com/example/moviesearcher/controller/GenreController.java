package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.GenreDTO;
import com.example.moviesearcher.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GenreDTO createGenre(@Valid @RequestBody GenreDTO genreDTO) {
        return genreService.saveGenre(genreDTO);
    }

    @GetMapping
    public List<GenreDTO> getAllGenres() {
        return genreService.findAllGenres();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public GenreDTO updateGenre(@PathVariable Long id, @Valid @RequestBody GenreDTO genreDTO) {
        genreDTO.setId(id);
        return genreService.updateGenre(genreDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }
}