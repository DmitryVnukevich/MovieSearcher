package com.example.moviesearcher.dto;

import com.example.moviesearcher.entity.CrewRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

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

        private List<CrewRole> roles;

        @NotNull(message = "Birth date is mandatory")
        @Past(message = "Birth date must be in the past")
        private Date birthDate;

        @NotBlank(message = "Bio is mandatory")
        @Size(max = 255, message = "Bio cannot be longer than 255 characters")
        private String bio;

        @NotBlank(message = "Photo URL is mandatory")
        private String photoUrl;
}

