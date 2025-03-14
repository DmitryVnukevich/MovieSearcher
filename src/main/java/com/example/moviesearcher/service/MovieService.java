package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.MovieDTO;
import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.entity.Movie;
import static com.example.moviesearcher.mapper.MovieMapper.MOVIE_MAPPER;
import com.example.moviesearcher.repository.CrewMemberRepository;
import com.example.moviesearcher.repository.GenreRepository;
import com.example.moviesearcher.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CrewMemberRepository crewMemberRepository;
    private final GenreRepository genreRepository;

    @Transactional
    public MovieDTO saveMovie(MovieDTO movieDTO) {
        validateCrewMemberIds(movieDTO.getCrewMemberIds());
        validateGenreIds(movieDTO.getGenreIds());

        Movie movie = MOVIE_MAPPER.movieDTOToMovie(movieDTO);
        movie = movieRepository.save(movie);
        return MOVIE_MAPPER.movieToMovieDTO(movie);
    }

    public MovieDTO findMovieById(Long id) {
        return movieRepository.findById(id)
                .map(MOVIE_MAPPER::movieToMovieDTO)
                .orElse(null);
    }

    public Page<MovieDTO> findMoviesByPreferences(UserInfoDTO userInfoDTO, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findMoviesByPreferences(userInfoDTO, pageable)
                .map(MOVIE_MAPPER::movieToMovieDTO);
    }

    @Transactional
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new IllegalArgumentException("Movie not found with ID: " + id);
        }
        movieRepository.deleteById(id);
    }

    @Transactional
    public MovieDTO updateMovie(MovieDTO movieDTO) {
        Movie movieFromDb = movieRepository.findById(movieDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + movieDTO.getId()));

        if (movieDTO.getTitle() != null) {
            movieFromDb.setTitle(movieDTO.getTitle());
        }
        if (movieDTO.getDescription() != null) {
            movieFromDb.setDescription(movieDTO.getDescription());
        }
        if (movieDTO.getReleaseDate() != null) {
            movieFromDb.setReleaseDate(movieDTO.getReleaseDate());
        }
        if (movieDTO.getDuration() != null) {
            movieFromDb.setDuration(movieDTO.getDuration());
        }
        if (movieDTO.getPosterUrl() != null) {
            movieFromDb.setPosterUrl(movieDTO.getPosterUrl());
        }
        if (movieDTO.getAgeRating() != null) {
            movieFromDb.setAgeRating(movieDTO.getAgeRating());
        }
        if (movieDTO.getContentType() != null) {
            movieFromDb.setContentType(movieDTO.getContentType());
        }
        if (movieDTO.getCrewMemberIds() != null) {
            validateCrewMemberIds(movieDTO.getCrewMemberIds());
            movieFromDb.setCrewMemberIds(movieDTO.getCrewMemberIds());
        }
        if (movieDTO.getGenreIds() != null) {
            validateGenreIds(movieDTO.getGenreIds());
            movieFromDb.setGenreIds(movieDTO.getGenreIds());
        }

        movieFromDb = movieRepository.save(movieFromDb);
        return MOVIE_MAPPER.movieToMovieDTO(movieFromDb);
    }

    private void validateCrewMemberIds(List<Long> crewMemberIds) {
        if (crewMemberIds != null) {
            for (Long crewId : crewMemberIds) {
                crewMemberRepository.findById(crewId)
                        .orElseThrow(() -> new IllegalArgumentException("Crew member not found with ID: " + crewId));
            }
        }
    }

    private void validateGenreIds(List<Long> genreIds) {
        if (genreIds != null) {
            for (Long genreId : genreIds) {
                genreRepository.findById(genreId)
                        .orElseThrow(() -> new IllegalArgumentException("Genre not found with ID: " + genreId));
            }
        }
    }
}