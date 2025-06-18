package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.MovieCrewDTO;
import com.example.moviesearcher.entity.CrewMember;
import com.example.moviesearcher.entity.Movie;
import com.example.moviesearcher.entity.MovieCrew;
import static com.example.moviesearcher.mapper.MovieCrewMapper.MOVIE_CREW_MAPPER;
import com.example.moviesearcher.repository.CrewMemberRepository;
import com.example.moviesearcher.repository.MovieCrewRepository;
import com.example.moviesearcher.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieCrewService {

    private final MovieCrewRepository movieCrewRepository;
    private final MovieRepository movieRepository;
    private final CrewMemberRepository crewMemberRepository;

    @Transactional
    public MovieCrewDTO createMovieCrew(MovieCrewDTO movieCrewDTO) {
        Movie movie = movieRepository.findById(movieCrewDTO.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with ID: " + movieCrewDTO.getMovieId()));
        CrewMember crewMember = crewMemberRepository.findById(movieCrewDTO.getCrewMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Crew member not found with ID: " + movieCrewDTO.getCrewMemberId()));

        MovieCrew movieCrew = MOVIE_CREW_MAPPER.movieCrewDTOToMovieCrew(movieCrewDTO);
        movieCrew.setMovie(movie);
        movieCrew.setCrewMember(crewMember);

        movieCrew = movieCrewRepository.save(movieCrew);
        return MOVIE_CREW_MAPPER.movieCrewToMovieCrewDTO(movieCrew);
    }

    @Transactional
    public MovieCrewDTO updateMovieCrew(MovieCrewDTO movieCrewDTO) {
        MovieCrew movieCrew = movieCrewRepository.findById(movieCrewDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("MovieCrew not found with ID: " + movieCrewDTO.getId()));

        Movie movie = movieRepository.findById(movieCrewDTO.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with ID: " + movieCrewDTO.getMovieId()));
        CrewMember crewMember = crewMemberRepository.findById(movieCrewDTO.getCrewMemberId())
                .orElseThrow(() -> new EntityNotFoundException("Crew member not found with ID: " + movieCrewDTO.getCrewMemberId()));

        movieCrew.setMovie(movie);
        movieCrew.setCrewMember(crewMember);
        movieCrew.setRoles(movieCrewDTO.getRoles());

        movieCrew = movieCrewRepository.save(movieCrew);
        return MOVIE_CREW_MAPPER.movieCrewToMovieCrewDTO(movieCrew);
    }

    @Transactional
    public List<MovieCrewDTO> findCrewByMovieId(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new EntityNotFoundException("Movie not found with ID: " + movieId);
        }
        return movieCrewRepository.findByMovieId(movieId)
                .stream()
                .map(MOVIE_CREW_MAPPER::movieCrewToMovieCrewDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMovieCrew(Long movieCrewId) {
        if (!movieCrewRepository.existsById(movieCrewId)) {
            throw new EntityNotFoundException("MovieCrew not found with ID: " + movieCrewId);
        }
        movieCrewRepository.deleteById(movieCrewId);
    }

    @Transactional
    public void deleteByMovieId(Long movieId) {
        if (!movieRepository.existsById(movieId)) {
            throw new EntityNotFoundException("Movie not found with ID: " + movieId);
        }
        movieCrewRepository.deleteByMovieId(movieId);
    }
}