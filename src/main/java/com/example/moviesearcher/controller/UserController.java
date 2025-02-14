package com.example.moviesearcher.controller;

import com.example.moviesearcher.entity.User;
import com.example.moviesearcher.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        return userService.verifyUser(user);
    }

    /*@PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }*/

    /*@GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }*/

    /*@GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }*/

    /*@PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        userDetails.setId(id);
        return userService.updateUser(userDetails);
    }*/

    /*@DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }*/
}
