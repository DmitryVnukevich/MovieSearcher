package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.GenreDTO;
import com.example.moviesearcher.entity.Genre;
import com.example.moviesearcher.mapper.GenreMapper;
import com.example.moviesearcher.repository.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    public GenreDTO saveGenre(GenreDTO genreDTO) {
        Genre genre = GenreMapper.INSTANCE.genreDTOToGenre(genreDTO);
        genre = genreRepository.save(genre);
        return GenreMapper.INSTANCE.genreToGenreDTO(genre);
    }

    public List<GenreDTO> findAllGenres() {
        return genreRepository.findAll().stream()
                .map(GenreMapper.INSTANCE::genreToGenreDTO)
                .collect(Collectors.toList());
    }

    public GenreDTO findGenreById(Long id) {
        return genreRepository.findById(id)
                .map(GenreMapper.INSTANCE::genreToGenreDTO)
                .orElse(null);
    }

    @Transactional
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    @Transactional
    public GenreDTO updateGenre(GenreDTO genreDTO) {
        Genre genre = GenreMapper.INSTANCE.genreDTOToGenre(genreDTO);
        genre = genreRepository.save(genre);
        return GenreMapper.INSTANCE.genreToGenreDTO(genre);
    }
}
