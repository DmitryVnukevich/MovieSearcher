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
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date releaseDate;

    @NotNull(message = "Duration cannot be null")
    @Min(value = 1, message = "Movie duration must be at least 1 minute")
    @Max(value = 32767, message = "Movie duration cannot exceed 32767 minutes")
    private Short duration;

    @NotBlank(message = "Photo URL is mandatory")
    private String posterUrl;

    @NotNull(message = "Age rating is mandatory")
    private AgeRating ageRating;

    @NotNull(message = "Content type is mandatory")
    private ContentType contentType;

    private List<Long> crewMemberIds;

    private List<Long> genreIds;
}