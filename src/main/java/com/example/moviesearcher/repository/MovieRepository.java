package com.example.moviesearcher.repository;

import com.example.moviesearcher.entity.AgeRating;
import com.example.moviesearcher.entity.ContentType;
import com.example.moviesearcher.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE " +
            "(:genreIds IS NULL OR EXISTS (SELECT 1 FROM m.genreIds g WHERE g IN :genreIds)) AND " +
            "(:crewMemberIds IS NULL OR EXISTS (SELECT 1 FROM m.crewMemberIds c WHERE c IN :crewMemberIds)) AND " +
            "(:ageRating IS NULL OR m.ageRating = :ageRating) AND " +
            "(:contentType IS NULL OR m.contentType = :contentType) AND " +
            "(:minDuration IS NULL OR m.duration >= :minDuration) AND " +
            "(:maxDuration IS NULL OR m.duration <= :maxDuration)")
    Page<Movie> findMoviesByPreferences(
            @Param("genreIds") List<Long> genreIds,
            @Param("crewMemberIds") List<Long> crewMemberIds,
            @Param("ageRating") AgeRating ageRating,
            @Param("contentType") ContentType contentType,
            @Param("minDuration") Short minDuration,
            @Param("maxDuration") Short maxDuration,
            Pageable pageable
    );
}
