package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.GenreDTO;
import com.example.moviesearcher.entity.Genre;
import static com.example.moviesearcher.mapper.GenreMapper.GENRE_MAPPER;
import com.example.moviesearcher.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    @Transactional
    public GenreDTO saveGenre(GenreDTO genreDTO) {
        Genre genre = GENRE_MAPPER.genreDTOToGenre(genreDTO);
        genre = genreRepository.save(genre);
        return GENRE_MAPPER.genreToGenreDTO(genre);
    }

    public List<GenreDTO> findAllGenres() {
        return genreRepository.findAll().stream()
                .map(GENRE_MAPPER::genreToGenreDTO)
                .collect(Collectors.toList());
    }

    public GenreDTO findGenreById(Long id) {
        return genreRepository.findById(id)
                .map(GENRE_MAPPER::genreToGenreDTO)
                .orElse(null);
    }

    public GenreDTO findGenreByName(String name) {
        return genreRepository.findByName(name)
                .map(GENRE_MAPPER::genreToGenreDTO)
                .orElse(null);
    }

    @Transactional
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    @Transactional
    public GenreDTO updateGenre(GenreDTO genreDTO) {
        Genre genre = GENRE_MAPPER.genreDTOToGenre(genreDTO);
        genre = genreRepository.save(genre);
        return GENRE_MAPPER.genreToGenreDTO(genre);
    }
}