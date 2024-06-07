package com.example.moviesearcher.entities;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class Episodes {
    private Long id;
    private Integer season;
    private Integer episode;
    private String title;
    private String description;
    private Date releaseDate;
    private Time duration;
    private Long seriesId;
}
