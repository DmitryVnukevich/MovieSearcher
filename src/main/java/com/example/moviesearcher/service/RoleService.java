package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.RoleDTO;
import com.example.moviesearcher.entity.Role;
import com.example.moviesearcher.mapper.RoleMapper;
import com.example.moviesearcher.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public RoleDTO saveRole(RoleDTO roleDTO) {
        Role role = RoleMapper.INSTANCE.roleDTOToRole(roleDTO);
        role = roleRepository.save(role);
        return RoleMapper.INSTANCE.roleToRoleDTO(role);
    }

    public List<RoleDTO> findAllRoles() {
        return roleRepository.findAll().stream()
                .map(RoleMapper.INSTANCE::roleToRoleDTO)
                .collect(Collectors.toList());
    }

    public RoleDTO findRoleById(Long id) {
        return roleRepository.findById(id)
                .map(RoleMapper.INSTANCE::roleToRoleDTO)
                .orElse(null);
    }

    @Transactional
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Transactional
    public RoleDTO updateRole(RoleDTO roleDTO) {
        Role role = RoleMapper.INSTANCE.roleDTOToRole(roleDTO);
        role = roleRepository.save(role);
        return RoleMapper.INSTANCE.roleToRoleDTO(role);
    }
}
