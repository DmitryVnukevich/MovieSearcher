/*
package com.example.moviesearcher.controller;

import com.example.moviesearcher.entity.Role;
import com.example.moviesearcher.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService rolesService;

    @Autowired
    public RoleController(RoleService rolesService) {
        this.rolesService = rolesService;
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return rolesService.saveRole(role);
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return rolesService.findAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return rolesService.findRoleById(id);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role roleDetails) {
        roleDetails.setId(id);
        return rolesService.updateRole(roleDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        rolesService.deleteRole(id);
    }
}
*/
package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.RoleDTO;
import com.example.moviesearcher.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService rolesService;

    @Autowired
    public RoleController(RoleService rolesService) {
        this.rolesService = rolesService;
    }

    @PostMapping
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
        return rolesService.saveRole(roleDTO);
    }

    @GetMapping
    public List<RoleDTO> getAllRoles() {
        return rolesService.findAllRoles();
    }

    @GetMapping("/{id}")
    public RoleDTO getRoleById(@PathVariable Long id) {
        return rolesService.findRoleById(id);
    }

    @PutMapping("/{id}")
    public RoleDTO updateRole(@PathVariable Long id, @RequestBody RoleDTO roleDTO) {
        roleDTO.setId(id);
        return rolesService.updateRole(roleDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        rolesService.deleteRole(id);
    }
}
