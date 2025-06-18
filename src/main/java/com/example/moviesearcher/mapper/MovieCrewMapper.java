package com.example.moviesearcher.mapper;

import com.example.moviesearcher.dto.MovieCrewDTO;
import com.example.moviesearcher.entity.MovieCrew;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieCrewMapper {
    MovieCrewMapper MOVIE_CREW_MAPPER = Mappers.getMapper(MovieCrewMapper.class);

    @Mapping(target = "movieId", source = "movie.id")
    @Mapping(target = "crewMemberId", source = "crewMember.id")
    MovieCrewDTO movieCrewToMovieCrewDTO(MovieCrew movieCrew);

    @Mapping(target = "movie", ignore = true)
    @Mapping(target = "crewMember", ignore = true)
    MovieCrew movieCrewDTOToMovieCrew(MovieCrewDTO movieCrewDTO);
}
