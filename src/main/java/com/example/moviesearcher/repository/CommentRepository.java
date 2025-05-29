package com.example.moviesearcher.repository;

import com.example.moviesearcher.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByMovieId(Long movieId, Pageable pageable);
    @Query("SELECT AVG(c.rating) FROM Comment c WHERE c.movieId = :movieId")
    Double findAverageRatingByMovieId(@Param("movieId") Long movieId);
}
