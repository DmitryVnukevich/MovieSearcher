package com.example.moviesearcher.service;

import com.example.moviesearcher.entities.Users;
import com.example.moviesearcher.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    public Users findUserById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    @Transactional
    public Users updateUser(Users user) {
        return usersRepository.save(user);
    }
}
