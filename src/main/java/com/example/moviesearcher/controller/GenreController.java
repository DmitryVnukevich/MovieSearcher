/*
package com.example.moviesearcher.controller;

import com.example.moviesearcher.entity.Genre;
import com.example.moviesearcher.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.saveGenre(genre);
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreService.findAllGenres();
    }

    @GetMapping("/{id}")
    public Genre getGenreById(@PathVariable Long id) {
        return genreService.findGenreById(id);
    }

    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable Long id, @RequestBody Genre genreDetails) {
        genreDetails.setId(id);
        return genreService.updateGenre(genreDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }
}
*/
package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.GenreDTO;
import com.example.moviesearcher.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public GenreDTO createGenre(@RequestBody GenreDTO genreDTO) {
        return genreService.saveGenre(genreDTO);
    }

    @GetMapping
    public List<GenreDTO> getAllGenres() {
        return genreService.findAllGenres();
    }

    @GetMapping("/{id}")
    public GenreDTO getGenreById(@PathVariable Long id) {
        return genreService.findGenreById(id);
    }

    @PutMapping("/{id}")
    public GenreDTO updateGenre(@PathVariable Long id, @RequestBody GenreDTO genreDTO) {
        genreDTO.setId(id);
        return genreService.updateGenre(genreDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }
}
