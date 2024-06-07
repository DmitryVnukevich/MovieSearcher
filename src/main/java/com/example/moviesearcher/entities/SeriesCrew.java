package com.example.moviesearcher.entities;

import lombok.Data;

@Data
public class SeriesCrew {
    private Long id;
    private Long seriesId;
    private Long crewId;
    private Long roleId;
}
