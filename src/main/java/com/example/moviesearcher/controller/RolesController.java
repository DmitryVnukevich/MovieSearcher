package com.example.moviesearcher.controller;

import com.example.moviesearcher.entities.Roles;
import com.example.moviesearcher.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    private final RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @PostMapping
    public Roles createRole(@RequestBody Roles role) {
        return rolesService.saveRole(role);
    }

    @GetMapping
    public List<Roles> getAllRoles() {
        return rolesService.findAllRoles();
    }

    @GetMapping("/{id}")
    public Roles getRoleById(@PathVariable Long id) {
        return rolesService.findRoleById(id);
    }

    @PutMapping("/{id}")
    public Roles updateRole(@PathVariable Long id, @RequestBody Roles roleDetails) {
        roleDetails.setId(id);
        return rolesService.updateRole(roleDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        rolesService.deleteRole(id);
    }
}
