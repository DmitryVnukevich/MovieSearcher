package com.example.moviesearcher.dto;

import com.example.moviesearcher.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private String id;
    private String name;
    private List<User> users;
}

