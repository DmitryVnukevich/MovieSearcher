package com.example.moviesearcher.service;

import com.example.moviesearcher.entity.CustomUserDetails;
import com.example.moviesearcher.entity.User;
import com.example.moviesearcher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            System.out.print("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(user);
    }
}
