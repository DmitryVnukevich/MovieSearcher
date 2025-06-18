package com.example.moviesearcher.repository;

import com.example.moviesearcher.entity.CrewRole;
import com.example.moviesearcher.entity.MovieCrew;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieCrewRepository extends JpaRepository<MovieCrew, Long> {
    @EntityGraph(attributePaths = {"movie", "crewMember"})
    List<MovieCrew> findByMovieId(Long movieId);

    @EntityGraph(attributePaths = {"movie", "crewMember"})
    List<MovieCrew> findByCrewMemberId(Long crewMemberId);

    List<MovieCrew> findByRolesContaining(CrewRole role);

    void deleteByMovieId(Long movieId);

    void deleteByCrewMemberId(Long crewMemberId);

    boolean existsByMovieIdAndCrewMemberId(Long movieId, Long crewMemberId);
}
