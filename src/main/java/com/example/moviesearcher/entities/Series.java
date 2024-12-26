package com.example.moviesearcher.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "series")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "poster_url", nullable = false)
    private String posterUrl;

    @OneToMany(mappedBy = "series")
    private List<Comments> comments;

    @OneToMany(mappedBy = "series")
    private List<Episodes> episodes;

    @OneToMany(mappedBy = "series")
    private List<SeriesCrew> seriesCrew;

    @ManyToMany
    @JoinTable(
            name = "series_genres",
            joinColumns = @JoinColumn(name = "series_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genres> genres;
}