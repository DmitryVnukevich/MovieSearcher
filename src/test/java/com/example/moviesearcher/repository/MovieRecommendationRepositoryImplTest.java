package com.example.moviesearcher.repository;

import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.entity.Movie;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@RequiredArgsConstructor
public class MovieRecommendationRepositoryImplTest {

    private final MovieRecommendationRepository movieRecommendationRepository;

    @Test
    @Sql(statements = {
            "INSERT INTO genre (id, name) VALUES (1, 'Action')",
            "INSERT INTO movie (id, title) VALUES (1, 'Test Movie')",
            "INSERT INTO movie_genre (movie_id, genre_id) VALUES (1, 1)"
    })
    void testFindMoviesByPreferences_withGenreMatch() {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setPreferredGenreIds(Collections.singletonList(1L));
        PageRequest pageable = PageRequest.of(0, 10);

        Page<Movie> result = movieRecommendationRepository.findMoviesByPreferences(userInfoDTO, pageable);

        assertEquals(1, result.getContent().size());
        assertEquals("Test Movie", result.getContent().get(0).getTitle());
        assertEquals(1, result.getTotalElements());
    }

    @Test
    @Sql(statements = {
            "INSERT INTO genre (id, name) VALUES (1, 'Action')",
            "INSERT INTO movie (id, title) VALUES (1, 'Test Movie')"
    })
    void testFindMoviesByPreferences_noMatch() {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setPreferredGenreIds(Collections.singletonList(1L));
        PageRequest pageable = PageRequest.of(0, 10);

        Page<Movie> result = movieRecommendationRepository.findMoviesByPreferences(userInfoDTO, pageable);

        assertEquals(0, result.getContent().size());
        assertEquals(1, result.getTotalElements());
    }
}
