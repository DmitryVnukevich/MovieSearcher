package com.example.moviesearcher.entities;

import lombok.Data;

import java.util.Date;

@Data
public class Series {
    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String posterUrl;
}
