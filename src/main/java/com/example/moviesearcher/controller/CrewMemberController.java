/*
package com.example.moviesearcher.controller;

import com.example.moviesearcher.entity.CrewMember;
import com.example.moviesearcher.service.CrewMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crew-members")
public class CrewMemberController {

    private final CrewMemberService crewMemberService;

    @Autowired
    public CrewMemberController(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    @PostMapping
    public CrewMember createCrewMember(@RequestBody CrewMember crewMember) {
        return crewMemberService.saveCrewMember(crewMember);
    }

    @GetMapping
    public List<CrewMember> getAllCrewMembers() {
        return crewMemberService.findAllCrewMembers();
    }

    @GetMapping("/{id}")
    public CrewMember getCrewMemberById(@PathVariable Long id) {
        return crewMemberService.findCrewMemberById(id);
    }

    @PutMapping("/{id}")
    public CrewMember updateCrewMember(@PathVariable Long id, @RequestBody CrewMember crewMemberDetails) {
        crewMemberDetails.setId(id);
        return crewMemberService.updateCrewMember(crewMemberDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteCrewMember(@PathVariable Long id) {
        crewMemberService.deleteCrewMember(id);
    }
}
*/
package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.CrewMemberDTO;
import com.example.moviesearcher.service.CrewMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crew-members")
public class CrewMemberController {

    private final CrewMemberService crewMemberService;

    @Autowired
    public CrewMemberController(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    @PostMapping
    public CrewMemberDTO createCrewMember(@RequestBody CrewMemberDTO crewMemberDTO) {
        return crewMemberService.saveCrewMember(crewMemberDTO);
    }

    @GetMapping
    public List<CrewMemberDTO> getAllCrewMembers() {
        return crewMemberService.findAllCrewMembers();
    }

    @GetMapping("/{id}")
    public CrewMemberDTO getCrewMemberById(@PathVariable Long id) {
        return crewMemberService.findCrewMemberById(id);
    }

    @PutMapping("/{id}")
    public CrewMemberDTO updateCrewMember(@PathVariable Long id, @RequestBody CrewMemberDTO crewMemberDTO) {
        crewMemberDTO.setId(id);
        return crewMemberService.updateCrewMember(crewMemberDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCrewMember(@PathVariable Long id) {
        crewMemberService.deleteCrewMember(id);
    }
}
