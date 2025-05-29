package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.MovieDTO;
import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.entity.Movie;
import com.example.moviesearcher.entity.UserInfo;
import com.example.moviesearcher.mapper.MovieMapper;
import com.example.moviesearcher.mapper.UserInfoMapper;
import com.example.moviesearcher.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private UserInfoRepository userInfoRepository;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private UserInfoMapper userInfoMapper;

    @InjectMocks
    private MovieService movieService;

    @Test
    void testFindMoviesByPreferences_success() {
        Long userId = 1L;
        UserInfo userInfo = new UserInfo();
        userInfo.setId(userId);
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(userId);
        Movie movie = new Movie();
        movie.setId(1L);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(1L);
        Page<Movie> moviePage = new PageImpl<>(Collections.singletonList(movie), PageRequest.of(0, 10), 1);

        when(userInfoRepository.findByUserId(userId)).thenReturn(Optional.of(userInfo));
        when(userInfoMapper.userInfoToUserInfoDTO(userInfo)).thenReturn(userInfoDTO);
        when(movieRepository.findMoviesByPreferences(any(UserInfoDTO.class), any(PageRequest.class)))
                .thenReturn(moviePage);
        when(movieMapper.movieToMovieDTO(movie)).thenReturn(movieDTO);

        PagedModel<MovieDTO> result = movieService.findMoviesByPreferences(userId, 0, 10);

        assertEquals(1, result.getContent().size());
        assertEquals(1L, result.getContent().get(0).getId());
        assertEquals(1, ((Page<MovieDTO>)result).getTotalElements()); // Fixed line
    }

    @Test
    void testFindMoviesByPreferences_userNotFound() {
        Long userId = 1L;
        when(userInfoRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> movieService.findMoviesByPreferences(userId, 0, 10));
    }
}