package com.example.moviesearcher.entities;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class Movies {
    private Long id;
    private String title;
    private String description;
    private Date releaseDate;
    private Time duration;
    private String posterUrl;
}
