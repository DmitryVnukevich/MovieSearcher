package com.example.moviesearcher.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie_crew")
public class MovieCrew {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_member_id", nullable = false)
    private CrewMember crewMember;

    @ElementCollection
    @CollectionTable(name = "movie_crew_role", joinColumns = @JoinColumn(name = "movie_crew_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<CrewRole> roles;
}