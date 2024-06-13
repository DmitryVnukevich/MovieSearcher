package com.example.moviesearcher.service;

import com.example.moviesearcher.entities.Roles;
import com.example.moviesearcher.repository.RolesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Transactional
    public Roles saveRole(Roles role) {
        return rolesRepository.save(role);
    }

    public List<Roles> findAllRoles() {
        return rolesRepository.findAll();
    }

    public Roles findRoleById(Long id) {
        return rolesRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteRole(Long id) {
        rolesRepository.deleteById(id);
    }
}
