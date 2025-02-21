package com.example.moviesearcher.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreDTO {

    private Long id;

    @NotBlank(message = "Genre name cannot be blank")
    @Size(min = 1, max = 50, message = "Genre name must be between 1 and 50 characters")
    private String name;
}

