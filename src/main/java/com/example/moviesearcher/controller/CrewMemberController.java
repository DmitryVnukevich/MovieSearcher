package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.CrewMemberDTO;
import com.example.moviesearcher.dto.GenreDTO;
import com.example.moviesearcher.dto.UserDTO;
import com.example.moviesearcher.service.CrewMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PagedModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/crew-member")
@RequiredArgsConstructor
public class CrewMemberController {

    private final CrewMemberService crewMemberService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CrewMemberDTO createCrewMember(@Valid @RequestBody CrewMemberDTO crewMemberDTO) {
        return crewMemberService.saveCrewMember(crewMemberDTO);
    }

    @GetMapping
    public List<CrewMemberDTO> getAllCrewMembers() {
        return crewMemberService.findAllCrewMembers();
    }

    @GetMapping("/{id}")
    public CrewMemberDTO getCrewMemberById(@PathVariable Long id) {
        CrewMemberDTO crewMember = crewMemberService.findCrewMemberById(id);
        return crewMember;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CrewMemberDTO updateCrewMember(@PathVariable Long id, @Valid @RequestBody CrewMemberDTO crewMemberDTO) {
        crewMemberDTO.setId(id);
        return crewMemberService.updateCrewMember(crewMemberDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCrewMember(@PathVariable Long id) {
        crewMemberService.deleteCrewMember(id);
    }

    @GetMapping("/pages")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PagedModel<CrewMemberDTO> getCrewMemberPages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return crewMemberService.findAllCrewMembers(page, size);
    }
}