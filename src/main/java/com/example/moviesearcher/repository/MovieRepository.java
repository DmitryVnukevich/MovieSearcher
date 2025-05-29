package com.example.moviesearcher.repository;

import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long>, MovieRecommendationRepository, MovieFindByTitleRepository {

}

interface MovieRecommendationRepository {
    Page<Movie> findMoviesByPreferences(UserInfoDTO userInfoDTO, Pageable pageable);
}

interface MovieFindByTitleRepository {
    Page<Movie> findByTitleContainingIgnoreCase(String query, Pageable pageable);
}