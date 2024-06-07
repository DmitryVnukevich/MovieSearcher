package com.example.moviesearcher.entities;

import lombok.Data;

@Data
public class MovieCrew {
    private Long id;
    private Long movieId;
    private Long crewId;
    private Long roleId;
}
