package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.UserDTO;
import com.example.moviesearcher.entity.User;
import static com.example.moviesearcher.mapper.UserMapper.USER_MAPPER;

import com.example.moviesearcher.entity.UserRole;
import com.example.moviesearcher.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists: " + userDTO.getUsername());
        }

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists: " + userDTO.getEmail());
        }

        userDTO.setRoles(Collections.singletonList(UserRole.USER));
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = USER_MAPPER.userDTOToUser(userDTO);
        user = userRepository.save(user);
        return USER_MAPPER.userToUserDTO(user);
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(USER_MAPPER::userToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(Long id) {
        return userRepository.findById(id)
                .map(USER_MAPPER::userToUserDTO)
                .orElse(null);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        User userFromDb = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userDTO.getId()));

        if (userDTO.getUsername() != null && !userDTO.getUsername().equals(userFromDb.getUsername())) {
            if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
                throw new IllegalArgumentException("Username already exists: " + userDTO.getUsername());
            }
            userFromDb.setUsername(userDTO.getUsername());
        }
        if (userDTO.getEmail() != null && !userDTO.getEmail().equals(userFromDb.getEmail())) {
            if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Email already exists: " + userDTO.getEmail());
            }
            userFromDb.setEmail(userDTO.getEmail());
        }
        if (userDTO.getPassword() != null) {
            userFromDb.setPassword(encoder.encode(userDTO.getPassword()));
        }
        if (userDTO.getRoles() != null) {
            userFromDb.setRoles(userDTO.getRoles());
        }

        userFromDb = userRepository.save(userFromDb);
        return USER_MAPPER.userToUserDTO(userFromDb);
    }

    public String verifyUser(UserDTO userDTO) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userDTO.getUsername());
        }

        return "Fail";
    }
}
