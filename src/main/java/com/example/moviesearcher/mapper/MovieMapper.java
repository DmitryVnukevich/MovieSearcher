package com.example.moviesearcher.mapper;

import com.example.moviesearcher.dto.MovieDTO;
import com.example.moviesearcher.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {
    MovieMapper MOVIE_MAPPER = Mappers.getMapper(MovieMapper.class);

    MovieDTO movieToMovieDTO(Movie movie);

    Movie movieDTOToMovie(MovieDTO movieDTO);
}