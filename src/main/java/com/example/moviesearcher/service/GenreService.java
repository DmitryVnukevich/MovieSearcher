package com.example.moviesearcher.service;

import com.example.moviesearcher.entity.Genre;
import com.example.moviesearcher.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    public Genre saveGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    public Genre findGenreById(Long id) {
        return genreRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }
    @Transactional
    public Genre updateGenre(Genre genre) {
        return genreRepository.save(genre);
    }
}
