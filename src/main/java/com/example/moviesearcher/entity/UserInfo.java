package com.example.moviesearcher.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "user_info_genres",
            joinColumns = @JoinColumn(name = "user_info_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> preferredGenres;

    @ManyToMany
    @JoinTable(
            name = "user_info_actors",
            joinColumns = @JoinColumn(name = "user_info_id"),
            inverseJoinColumns = @JoinColumn(name = "crew_member_id")
    )
    private List<CrewMember> favoriteActors;

    @ManyToMany
    @JoinTable(
            name = "user_info_directors",
            joinColumns = @JoinColumn(name = "user_info_id"),
            inverseJoinColumns = @JoinColumn(name = "crew_member_id")
    )
    private List<CrewMember> favoriteDirectors;

    @ElementCollection
    @CollectionTable(name = "user_info_countries", joinColumns = @JoinColumn(name = "user_info_id"))
    @Column(name = "country")
    private List<String> preferredCountries;

    @Column(name = "content_type_preference")
    @Enumerated(EnumType.STRING)
    private ContentType contentTypePreference;

    @Column(name = "preferred_rating_range")
    private String preferredRatingRange;

    @Column(name = "preferred_duration_range")
    private String preferredDurationRange;

    @Column(name = "preferred_age_rating")
    @Enumerated(EnumType.STRING)
    private AgeRating preferredAgeRating;
}