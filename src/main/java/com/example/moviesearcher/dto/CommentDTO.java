package com.example.moviesearcher.dto;

import com.example.moviesearcher.entity.Movie;
import com.example.moviesearcher.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Integer rating;

    @NotBlank(message = "Review is mandatory")
    @Size(max = 255, message = "Review cannot be longer than 255 characters")
    private String review;

    private Date createdAt;

    private Long userId;

    private String username;

    private Long movieId;
}
