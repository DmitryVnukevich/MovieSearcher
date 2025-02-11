package com.example.moviesearcher.service;

import com.example.moviesearcher.entity.Role;
import com.example.moviesearcher.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Transactional
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }
}
