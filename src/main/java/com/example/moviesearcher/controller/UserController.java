package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.UserDTO;
import com.example.moviesearcher.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        return userService.verifyUser(userDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        if (userService.findUserById(id) == null) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        return userService.updateUser(userDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        if (userService.findUserById(id) == null) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userService.deleteUser(id);
    }
}