package com.example.moviesearcher.dto;

import com.example.moviesearcher.entity.AgeRating;
import com.example.moviesearcher.entity.ContentType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private Long id;

    @NotBlank(message = "Movie title cannot be blank")
    @Size(min = 1, max = 255, message = "Movie title must be between 1 and 255 characters")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 255, message = "Description cannot be longer than 255 characters")
    private String description;

    @NotNull(message = "Release date cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @NotNull(message = "Duration cannot be null")
    @Min(value = 1, message = "Movie duration must be at least 1 minute")
    @Max(value = 32767, message = "Movie duration cannot exceed 32767 minutes")
    private Short duration;

    @NotBlank(message = "Poster is mandatory")
    @Pattern(regexp = "^data:image/(jpeg|png);base64,[A-Za-z0-9+/=]+$", message = "Poster must be a valid Base64 image")
    private String poster;

    @NotNull(message = "Age rating is mandatory")
    private AgeRating ageRating;

    @NotNull(message = "Content type is mandatory")
    private ContentType contentType;

    private Float averageRating;

    private List<MovieCrewDTO> crew;

    private List<Byte> genreIds;
}