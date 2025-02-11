package com.example.moviesearcher.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "release_date", nullable = false)
    private Date releaseDate;

    @Column(nullable = false)
    private Time duration;

    @Column(name = "poster_url", nullable = false)
    private String posterUrl;

    @OneToMany(mappedBy = "movie")
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "movie_crew",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "crew_id")
    )
    private List<CrewMember> movieCrew;

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;
}
