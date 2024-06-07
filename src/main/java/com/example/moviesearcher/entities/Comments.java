package com.example.moviesearcher.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Comments {
    private Long id;
    private Integer rating;
    private String review;
    private Date created_at;
    private Integer user_id;
    private Integer movie_id;
    private Integer series_id;
}
