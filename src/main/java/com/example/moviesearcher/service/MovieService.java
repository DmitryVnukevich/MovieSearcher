package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.MovieDTO;
import com.example.moviesearcher.entity.Movie;
import com.example.moviesearcher.entity.User;
import com.example.moviesearcher.mapper.MovieMapper;
import com.example.moviesearcher.repository.MovieRepository;
import com.example.moviesearcher.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .collect(Collectors.toList());
    }

    public MovieDTO findMovieById(Long id) {
        return movieRepository.findById(id)
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
                .orElse(null);
    }

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
}