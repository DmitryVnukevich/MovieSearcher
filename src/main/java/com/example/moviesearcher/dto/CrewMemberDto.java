package com.example.moviesearcher.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrewMemberDto {
        private Long id;
        private String firstName;
        private String lastName;
        private Date birthDate;
        private String bio;
        private String photoUrl;
}

