package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.MovieDTO;
import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.entity.Movie;
import static com.example.moviesearcher.mapper.MovieMapper.MOVIE_MAPPER;
import static com.example.moviesearcher.mapper.UserInfoMapper.USER_INFO_MAPPER;

import com.example.moviesearcher.entity.UserInfo;
import com.example.moviesearcher.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CrewMemberRepository crewMemberRepository;
    private final GenreRepository genreRepository;
    private final UserInfoRepository userInfoRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public MovieDTO saveMovie(MovieDTO movieDTO) {
        validateCrewMemberIds(movieDTO.getCrewMemberIds());
        validateGenreIds(movieDTO.getGenreIds());

        Movie movie = MOVIE_MAPPER.movieDTOToMovie(movieDTO);
        movie = movieRepository.save(movie);
        return MOVIE_MAPPER.movieToMovieDTO(movie);
    }

    public PagedModel<MovieDTO> findAllMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        Page<MovieDTO> movieDTOPage = moviePage
                .map(MOVIE_MAPPER::movieToMovieDTO)
                .map(this::enrichMovieDTOWithAverageRating);
        return new PagedModel<>(movieDTOPage);
    }

    public MovieDTO findMovieById(Long id) {
        return movieRepository.findById(id)
                .map(MOVIE_MAPPER::movieToMovieDTO)
                .map(this::enrichMovieDTOWithAverageRating)
                .orElse(null);
    }

    public PagedModel<MovieDTO> findMoviesByPreferences(Long userId, int page, int size) {
        UserInfo userInfo = userInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("UserInfo not found for user ID: " + userId));

        UserInfoDTO userInfoDTO = USER_INFO_MAPPER.userInfoToUserInfoDTO(userInfo);

        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage = movieRepository.findMoviesByPreferences(userInfoDTO, pageable);
        Page<MovieDTO> movieDTOPage = moviePage
                .map(MOVIE_MAPPER::movieToMovieDTO)
                .map(this::enrichMovieDTOWithAverageRating);

        return new PagedModel<>(movieDTOPage);
    }

    public PagedModel<MovieDTO> searchMoviesByTitle(@NotBlank String query, int page, int size) {
        if (query == null || query.trim().isEmpty()) {
            throw new IllegalArgumentException("Search query cannot be empty");
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage = movieRepository.findByTitleContainingIgnoreCase(query.trim(), pageable);
        Page<MovieDTO> movieDTOPage = moviePage
                .map(MOVIE_MAPPER::movieToMovieDTO)
                .map(this::enrichMovieDTOWithAverageRating);
        return new PagedModel<>(movieDTOPage);
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
        if (movieDTO.getPoster() != null) {
            movieFromDb.setPoster(movieDTO.getPoster());
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

    private void validateGenreIds(List<Byte> genreIds) {
        if (genreIds != null) {
            for (Byte genreId : genreIds) {
                genreRepository.findById(genreId)
                        .orElseThrow(() -> new IllegalArgumentException("Genre not found with ID: " + genreId));
            }
        }
    }

    private MovieDTO enrichMovieDTOWithAverageRating(MovieDTO movieDTO) {
        Double averageRating = commentRepository.findAverageRatingByMovieId(movieDTO.getId());
        movieDTO.setAverageRating(averageRating != null ? averageRating.floatValue() : null);
        return movieDTO;
    }
}