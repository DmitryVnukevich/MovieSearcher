package com.example.moviesearcher.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "episodes")
public class Episodes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer season;

    @Column(nullable = false)
    private Integer episode;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "release_date", nullable = false)
    private Date releaseDate;

    @Column(nullable = false)
    private Time duration;

    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    private Series series;
}
