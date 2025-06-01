package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.MovieCrewDTO;
import com.example.moviesearcher.dto.MovieDTO;
import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.entity.Movie;
import static com.example.moviesearcher.mapper.MovieMapper.MOVIE_MAPPER;
import static com.example.moviesearcher.mapper.UserInfoMapper.USER_INFO_MAPPER;

import com.example.moviesearcher.entity.UserInfo;
import com.example.moviesearcher.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final UserInfoRepository userInfoRepository;
    private final CommentRepository commentRepository;
    private final MovieCrewService movieCrewService;

    @Transactional
    public MovieDTO saveMovie(MovieDTO movieDTO) {
        validateGenreIds(movieDTO.getGenreIds());

        Movie movie = MOVIE_MAPPER.movieDTOToMovie(movieDTO);
        movie = movieRepository.save(movie);

        if (movieDTO.getCrew() != null && !movieDTO.getCrew().isEmpty()) {
            for (MovieCrewDTO crewDTO : movieDTO.getCrew()) {
                crewDTO.setMovieId(movie.getId());
                movieCrewService.createMovieCrew(crewDTO);
            }
        }

        return MOVIE_MAPPER.movieToMovieDTO(movie);
    }

    @Transactional(readOnly = true)
    public PagedModel<MovieDTO> findAllMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        Page<MovieDTO> movieDTOPage = moviePage
                .map(MOVIE_MAPPER::movieToMovieDTO)
                .map(this::enrichMovieDTOWithAverageRating);
        return new PagedModel<>(movieDTOPage);
    }

    @Transactional(readOnly = true)
    public MovieDTO findMovieById(Long id) {
        return movieRepository.findById(id)
                .map(MOVIE_MAPPER::movieToMovieDTO)
                .map(this::enrichMovieDTOWithAverageRating)
                .map(this::enrichMovieWithCrew)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public PagedModel<MovieDTO> findMoviesByPreferences(Long userId, int page, int size) {
        UserInfo userInfo = userInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("UserInfo not found for user ID: " + userId));

        UserInfoDTO userInfoDTO = USER_INFO_MAPPER.userInfoToUserInfoDTO(userInfo);

        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage = movieRepository.findMoviesByPreferences(userInfoDTO, pageable);
        Page<MovieDTO> movieDTOPage = moviePage
                .map(MOVIE_MAPPER::movieToMovieDTO)
                .map(this::enrichMovieDTOWithAverageRating);

        return new PagedModel<>(movieDTOPage);
    }

    @Transactional
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
            throw new EntityNotFoundException("Movie not found with ID: " + id);
        }
        movieCrewService.deleteByMovieId(id);
        movieRepository.deleteById(id);
    }

    @Transactional
    public MovieDTO updateMovie(MovieDTO movieDTO) {
        Movie movieFromDb = movieRepository.findById(movieDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with ID: " + movieDTO.getId()));

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
        if (movieDTO.getGenreIds() != null) {
            validateGenreIds(movieDTO.getGenreIds());
            movieFromDb.setGenreIds(movieDTO.getGenreIds());
        }

        movieFromDb = movieRepository.save(movieFromDb);

        if (movieDTO.getCrew() != null) {
            movieCrewService.deleteByMovieId(movieFromDb.getId());

            if (!movieDTO.getCrew().isEmpty()) {
                for (MovieCrewDTO crewDTO : movieDTO.getCrew()) {
                    crewDTO.setMovieId(movieFromDb.getId());
                    movieCrewService.createMovieCrew(crewDTO);
                }
            }
        }

        return MOVIE_MAPPER.movieToMovieDTO(movieFromDb);
    }

    private void validateGenreIds(List<Byte> genreIds) {
        if (genreIds != null) {
            for (Byte genreId : genreIds) {
                genreRepository.findById(genreId)
                        .orElseThrow(() -> new EntityNotFoundException("Genre not found with ID: " + genreId));
            }
        }
    }

    private MovieDTO enrichMovieDTOWithAverageRating(MovieDTO movieDTO) {
        Double averageRating = commentRepository.findAverageRatingByMovieId(movieDTO.getId());
        movieDTO.setAverageRating(averageRating != null ? averageRating.floatValue() : null);
        return movieDTO;
    }

    private MovieDTO enrichMovieWithCrew(MovieDTO movieDTO) {
        List<MovieCrewDTO> crew = movieCrewService.findCrewByMovieId(movieDTO.getId());
        movieDTO.setCrew(crew);
        return movieDTO;
    }
}