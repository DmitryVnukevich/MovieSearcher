package com.example.moviesearcher.dto;

import com.example.moviesearcher.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private Long id;
    private Integer rating;
    private String review;
    private Date createdAt;
    private String user_id;
    private Movie movie;
}
