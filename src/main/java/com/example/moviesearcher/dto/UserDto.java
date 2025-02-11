package com.example.moviesearcher.dto;

import com.example.moviesearcher.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String userName;
    private String email;
    private String passwordHash;
    private Role role;
}

