package com.example.moviesearcher.controller;

import com.example.moviesearcher.entities.CrewMembers;
import com.example.moviesearcher.service.CrewMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crew-members")
public class CrewMembersController {

    private final CrewMembersService crewMembersService;

    @Autowired
    public CrewMembersController(CrewMembersService crewMembersService) {
        this.crewMembersService = crewMembersService;
    }

    @PostMapping
    public CrewMembers createCrewMember(@RequestBody CrewMembers crewMember) {
        return crewMembersService.saveCrewMember(crewMember);
    }

    @GetMapping
    public List<CrewMembers> getAllCrewMembers() {
        return crewMembersService.findAllCrewMembers();
    }

    @GetMapping("/{id}")
    public CrewMembers getCrewMemberById(@PathVariable Long id) {
        return crewMembersService.findCrewMemberById(id);
    }

    @PutMapping("/{id}")
    public CrewMembers updateCrewMember(@PathVariable Long id, @RequestBody CrewMembers crewMemberDetails) {
        crewMemberDetails.setId(id);
        return crewMembersService.updateCrewMember(crewMemberDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCrewMember(@PathVariable Long id) {
        crewMembersService.deleteCrewMember(id);
    }
}
