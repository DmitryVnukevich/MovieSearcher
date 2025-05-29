package com.example.moviesearcher.mapper;

import com.example.moviesearcher.dto.MovieDTO;
import com.example.moviesearcher.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MovieMapper {
    MovieMapper MOVIE_MAPPER = Mappers.getMapper(MovieMapper.class);

    MovieDTO movieToMovieDTO(Movie movie);

    Movie movieDTOToMovie(MovieDTO movieDTO);

    List<MovieDTO> toDtoList(List<Movie> movies);
}