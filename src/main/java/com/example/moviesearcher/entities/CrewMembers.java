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
@Table(name = "crew_members")
public class CrewMembers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private String bio;

    @Column(name = "photo_url", nullable = false)
    private String photoUrl;

    @OneToMany(mappedBy = "crewMember")
    private List<MovieCrew> movieCrew;

    @OneToMany(mappedBy = "crewMember")
    private List<SeriesCrew> seriesCrew;
}
