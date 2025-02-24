package com.example.moviesearcher.dto;

import com.example.moviesearcher.entity.AgeRating;
import com.example.moviesearcher.entity.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {
    private Long id;

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    private List<GenreDTO> preferredGenres;

    private List<CrewMemberDTO> favoriteActors;

    private List<CrewMemberDTO> favoriteDirectors;

    private List<String> preferredCountries;

    private ContentType contentTypePreference;

    private String preferredRatingRange;

    private String preferredDurationRange;

    private AgeRating preferredAgeRating;
}
