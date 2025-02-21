package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.UserDTO;
import com.example.moviesearcher.entity.User;
import com.example.moviesearcher.mapper.UserMapper;
import com.example.moviesearcher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Transactional
    public UserDTO saveUser(UserDTO userDTO) {
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = UserMapper.INSTANCE.userDTOToUser(userDTO);
        user = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    public List<UserDTO> findAllUsers() {
        return userRepository.findAll().stream()
                .map(UserMapper.INSTANCE::userToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO findUserById(Long id) {
        return userRepository.findById(id)
                .map(UserMapper.INSTANCE::userToUserDTO)
                .orElse(null);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        User user = UserMapper.INSTANCE.userDTOToUser(userDTO);
        user = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserDTO(user);
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
