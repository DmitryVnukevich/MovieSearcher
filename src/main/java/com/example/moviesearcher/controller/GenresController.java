package com.example.moviesearcher.controller;

import com.example.moviesearcher.entities.Genres;
import com.example.moviesearcher.service.GenresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenresController {

    private final GenresService genresService;

    @Autowired
    public GenresController(GenresService genresService) {
        this.genresService = genresService;
    }

    @PostMapping
    public Genres createGenre(@RequestBody Genres genre) {
        return genresService.saveGenre(genre);
    }

    @GetMapping
    public List<Genres> getAllGenres() {
        return genresService.findAllGenres();
    }

    @GetMapping("/{id}")
    public Genres getGenreById(@PathVariable Long id) {
        return genresService.findGenreById(id);
    }

    @PutMapping("/{id}")
    public Genres updateGenre(@PathVariable Long id, @RequestBody Genres genreDetails) {
        genreDetails.setId(id);
        return genresService.updateGenre(genreDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genresService.deleteGenre(id);
    }
}
