package com.example.moviesearcher.dto;

import com.example.moviesearcher.entity.CrewRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieCrewDTO {

    private Long id;

    @NotNull(message = "Movie ID is mandatory")
    private Long movieId;

    @NotNull(message = "Crew member ID is mandatory")
    private Long crewMemberId;

    @NotEmpty(message = "At least one role is required")
    private List<CrewRole> roles;
}
