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

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_info_genres", joinColumns = @JoinColumn(name = "user_info_id"))
    @Column(name = "genre_id")
    private List<Long> preferredGenreIds;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_info_actors", joinColumns = @JoinColumn(name = "user_info_id"))
    @Column(name = "actor_id")
    private List<Long> favoriteActorIds;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "user_info_directors", joinColumns = @JoinColumn(name = "user_info_id"))
    @Column(name = "director_id")
    private List<Long> favoriteDirectorIds;

    @Column(name = "content_type_preference")
    @Enumerated(EnumType.STRING)
    private ContentType contentTypePreference;

    @Column(name = "min_rating")
    private Byte minRating;

    @Column(name = "max_rating")
    private Byte maxRating;

    @Column(name = "min_duration")
    private Short minDuration;

    @Column(name = "max_duration")
    private Short maxDuration;

    @Column(name = "preferred_age_rating")
    @Enumerated(EnumType.STRING)
    private AgeRating preferredAgeRating;
}