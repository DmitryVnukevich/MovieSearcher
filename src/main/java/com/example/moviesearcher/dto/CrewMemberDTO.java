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
public class CrewMemberDTO {

        private Long id;

        @NotBlank(message = "First name is mandatory")
        @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters")
        private String firstName;

        @NotBlank(message = "Last name is mandatory")
        @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters")
        private String lastName;

        @NotNull(message = "Birth date is mandatory")
        @Past(message = "Birth date must be in the past")
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        @JsonFormat(pattern = "dd-MM-yyyy")
        private Date birthDate;

        @NotBlank(message = "Bio is mandatory")
        @Size(max = 255, message = "Bio cannot be longer than 255 characters")
        private String bio;

        @NotBlank(message = "Photo is mandatory")
        private String photo;
}
