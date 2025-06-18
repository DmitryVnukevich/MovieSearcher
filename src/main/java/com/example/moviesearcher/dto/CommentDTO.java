package com.example.moviesearcher.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;

    @NotNull(message = "Rating is mandatory")
    @Min(value = 1, message = "Rating must be between 1 and 10")
    @Max(value = 10, message = "Rating must be between 1 and 10")
    private Byte rating;

    @NotBlank(message = "Review is mandatory")
    @Size(max = 255, message = "Review cannot be longer than 255 characters")
    private String review;

    @NotNull(message = "Created date is mandatory")
    @PastOrPresent(message = "Created date must be in the past or present")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private Date createdAt;

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    private String username;

    @NotNull(message = "Movie ID is mandatory")
    private Long movieId;
}
