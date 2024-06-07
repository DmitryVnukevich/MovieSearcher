package com.example.moviesearcher.entities;

import lombok.Data;

@Data
public class Users {
    private Long id;
    private String userName;
    private String email;
    private String passwordHash;
    private Long roleId;
}
