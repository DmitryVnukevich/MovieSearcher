package com.example.moviesearcher.service;

import com.example.moviesearcher.entities.Genres;
import com.example.moviesearcher.repository.GenresRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenresService {

    private final GenresRepository genresRepository;

    public GenresService(GenresRepository genresRepository) {
        this.genresRepository = genresRepository;
    }

    @Transactional
    public Genres saveGenre(Genres genre) {
        return genresRepository.save(genre);
    }

    public List<Genres> findAllGenres() {
        return genresRepository.findAll();
    }

    public Genres findGenreById(Long id) {
        return genresRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteGenre(Long id) {
        genresRepository.deleteById(id);
    }
    @Transactional
    public Genres updateGenre(Genres genre) {
        return genresRepository.save(genre);
    }
}
