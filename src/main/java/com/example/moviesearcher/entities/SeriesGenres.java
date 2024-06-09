package com.example.moviesearcher.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "series_genres")
public class SeriesGenres {
    @Id
    @ManyToOne
    @JoinColumn(name = "series_id", nullable = false)
    private Series series;

    @Id
    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genres genre;
}
