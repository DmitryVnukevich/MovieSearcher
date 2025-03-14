package com.example.moviesearcher.dto;

import com.example.moviesearcher.entity.AgeRating;
import com.example.moviesearcher.entity.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

    private List<Long> preferredGenreIds;

    private List<Long> favoriteActorIds;

    private List<Long> favoriteDirectorIds;

    private ContentType contentTypePreference;

    @Min(value = 1, message = "Minimum rating must be at least 1")
    @Max(value = 10, message = "Minimum rating cannot exceed 10")
    private Byte minRating;

    @Min(value = 1, message = "Maximum rating must be at least 1")
    @Max(value = 10, message = "Maximum rating cannot exceed 10")
    private Byte maxRating;

    @Min(value = 1, message = "Minimum duration must be at least 1 minute")
    @Max(value = 32767, message = "Minimum duration cannot exceed 32767 minutes")
    private Short minDuration;

    @Min(value = 1, message = "Maximum duration must be at least 1 minute")
    @Max(value = 32767, message = "Maximum duration cannot exceed 32767 minutes")
    private Short maxDuration;

    private AgeRating preferredAgeRating;
}
