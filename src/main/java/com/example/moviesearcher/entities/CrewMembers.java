package com.example.moviesearcher.entities;

import lombok.Data;

import java.util.Date;

@Data
public class CrewMembers {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String bio;
    private String photoUrl;
}
