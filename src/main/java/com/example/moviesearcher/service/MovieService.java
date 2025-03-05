/*
package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.MovieDTO;
import com.example.moviesearcher.entity.Movie;
import com.example.moviesearcher.entity.User;
import com.example.moviesearcher.mapper.MovieMapper;
import com.example.moviesearcher.repository.MovieRepository;
import com.example.moviesearcher.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public MovieService(MovieRepository movieRepository, UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public MovieDTO saveMovie(MovieDTO movieDTO) {
        Movie movie = MovieMapper.INSTANCE.movieDTOToMovie(movieDTO);
        movie = movieRepository.save(movie);
        return MovieMapper.INSTANCE.movieToMovieDTO(movie);
    }

    public List<MovieDTO> findAllMovies() {
        return movieRepository.findAll().stream()
                .map(MovieMapper.INSTANCE::movieToMovieDTO)
                .collect(Collectors.toList());
    }

    */
/*public List<MovieDTO> findAllMovies() {
        List<MovieDTO> movieDTOS = new ArrayList<>();
        return movieDTOS;

        *//*
*/
/*return movieRepository.findAll().stream()
                .map(movie -> {
                    MovieDTO movieDTO = MovieMapper.INSTANCE.movieToMovieDTO(movie);
                    movieDTO.getComments().forEach(commentDTO -> {
                        User user = userRepository.findById(commentDTO.getUserId()).orElse(null);
                        if (user != null) {
                            commentDTO.setUsername(user.getUsername());
                        }
                    });
                    return movieDTO;
                })
                .collect(Collectors.toList());*//*
*/
/*
    }*//*


    public MovieDTO findMovieById(Long id) {
        return movieRepository.findById(id)
                .map(MovieMapper.INSTANCE::movieToMovieDTO)
                .orElse(null);
    }

    */
/*public MovieDTO findMovieById(Long id) {
        return new MovieDTO();

        *//*
*/
/*return movieRepository.findById(id)
                .map(movie -> {
                    MovieDTO movieDTO = MovieMapper.INSTANCE.movieToMovieDTO(movie);
                    movieDTO.getComments().forEach(commentDTO -> {
                        User user = userRepository.findById(commentDTO.getUserId()).orElse(null);
                        if (user != null) {
                            commentDTO.setUsername(user.getUsername());
                        }
                    });
                    return movieDTO;
                })
                .orElse(null);*//*
*/
/*
    }*//*


    @Transactional
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Transactional
    public MovieDTO updateMovie(MovieDTO movieDTO) {
        Movie movie = MovieMapper.INSTANCE.movieDTOToMovie(movieDTO);
        movie = movieRepository.save(movie);
        return MovieMapper.INSTANCE.movieToMovieDTO(movie);
    }
}*/
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
import java.util.stream.Collectors;

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
        return movieRepository.findMoviesByPreferences(
                userInfoDTO.getPreferredGenreIds(),
                userInfoDTO.getFavoriteActorIds() != null && !userInfoDTO.getFavoriteActorIds().isEmpty()
                        ? userInfoDTO.getFavoriteActorIds()
                        : userInfoDTO.getFavoriteDirectorIds(),
                userInfoDTO.getPreferredAgeRating(),
                userInfoDTO.getContentTypePreference(),
                userInfoDTO.getMinDuration(),
                userInfoDTO.getMaxDuration(),
                pageable
        ).map(MOVIE_MAPPER::movieToMovieDTO);
    }

    @Transactional
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    @Transactional
    public MovieDTO updateMovie(MovieDTO movieDTO) {
        validateCrewMemberIds(movieDTO.getCrewMemberIds());
        validateGenreIds(movieDTO.getGenreIds());

        Movie movie = MOVIE_MAPPER.movieDTOToMovie(movieDTO);
        movie = movieRepository.save(movie);
        return MOVIE_MAPPER.movieToMovieDTO(movie);
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