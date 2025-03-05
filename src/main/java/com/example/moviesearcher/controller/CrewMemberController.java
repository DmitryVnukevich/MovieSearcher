package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.CrewMemberDTO;
import com.example.moviesearcher.service.CrewMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crew-member")
@RequiredArgsConstructor
public class CrewMemberController {

    private final CrewMemberService crewMemberService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CrewMemberDTO createCrewMember(@RequestBody CrewMemberDTO crewMemberDTO) {
        return crewMemberService.saveCrewMember(crewMemberDTO);
    }

    @GetMapping("/{id}")
    public CrewMemberDTO getCrewMemberById(@PathVariable Long id) {
        CrewMemberDTO crewMember = crewMemberService.findCrewMemberById(id);
        if (crewMember == null) {
            throw new IllegalArgumentException("Crew member not found with ID: " + id);
        }
        return crewMember;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CrewMemberDTO updateCrewMember(@PathVariable Long id, @RequestBody CrewMemberDTO crewMemberDTO) {
        crewMemberDTO.setId(id);
        return crewMemberService.updateCrewMember(crewMemberDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCrewMember(@PathVariable Long id) {
        if (crewMemberService.findCrewMemberById(id) == null) {
            throw new IllegalArgumentException("Crew member not found with ID: " + id);
        }
        crewMemberService.deleteCrewMember(id);
    }
}