package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.CrewMemberDTO;
import com.example.moviesearcher.dto.UserDTO;
import com.example.moviesearcher.entity.CrewMember;
import com.example.moviesearcher.entity.CrewRole;
import static com.example.moviesearcher.mapper.CrewMemberMapper.CREW_MEMBER_MAPPER;
import static com.example.moviesearcher.mapper.UserMapper.USER_MAPPER;

import com.example.moviesearcher.entity.User;
import com.example.moviesearcher.repository.CrewMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CrewMemberService {

    private final CrewMemberRepository crewMemberRepository;

    @Transactional
    public CrewMemberDTO saveCrewMember(CrewMemberDTO crewMemberDTO) {
        CrewMember crewMember = CREW_MEMBER_MAPPER.crewMemberDTOToCrewMember(crewMemberDTO);
        crewMember = crewMemberRepository.save(crewMember);
        return CREW_MEMBER_MAPPER.crewMemberToCrewMemberDTO(crewMember);
    }

    public List<CrewMemberDTO> findAllCrewMembers() {
        return crewMemberRepository.findAll().stream()
                .map(CREW_MEMBER_MAPPER::crewMemberToCrewMemberDTO)
                .collect(Collectors.toList());
    }

    public PagedModel<CrewMemberDTO> findAllCrewMembers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CrewMember> crewMemberPage = crewMemberRepository.findAll(pageable);
        Page<CrewMemberDTO> crewMemberDTOPage = crewMemberPage.map(CREW_MEMBER_MAPPER::crewMemberToCrewMemberDTO);
        return new PagedModel<>(crewMemberDTOPage);
    }

    public CrewMemberDTO findCrewMemberById(Long id) {
        return crewMemberRepository.findById(id)
                .map(CREW_MEMBER_MAPPER::crewMemberToCrewMemberDTO)
                .orElse(null);
    }

    public List<CrewMemberDTO> findCrewMembersByRole(CrewRole role) {
        return crewMemberRepository.findByRolesContaining(role).stream()
                .map(CREW_MEMBER_MAPPER::crewMemberToCrewMemberDTO)
                .collect(Collectors.toList());
    }

    public List<CrewMemberDTO> findActors() {
        return findCrewMembersByRole(CrewRole.ACTOR);
    }

    public List<CrewMemberDTO> findDirectors() {
        return findCrewMembersByRole(CrewRole.DIRECTOR);
    }

    @Transactional
    public void deleteCrewMember(Long id) {
        if (!crewMemberRepository.existsById(id)) {
            throw new IllegalArgumentException("Crew member not found with ID: " + id);
        }
        crewMemberRepository.deleteById(id);
    }

    @Transactional
    public CrewMemberDTO updateCrewMember(CrewMemberDTO crewMemberDTO) {
        CrewMember crewMemberFromDb = crewMemberRepository.findById(crewMemberDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Crew member not found with ID: " + crewMemberDTO.getId()));

        if (crewMemberDTO.getFirstName() != null) {
            crewMemberFromDb.setFirstName(crewMemberDTO.getFirstName());
        }
        if (crewMemberDTO.getLastName() != null) {
            crewMemberFromDb.setLastName(crewMemberDTO.getLastName());
        }
        if (crewMemberDTO.getRoles() != null) {
            crewMemberFromDb.setRoles(crewMemberDTO.getRoles());
        }
        if (crewMemberDTO.getBirthDate() != null) {
            crewMemberFromDb.setBirthDate(crewMemberDTO.getBirthDate());
        }
        if (crewMemberDTO.getBio() != null) {
            crewMemberFromDb.setBio(crewMemberDTO.getBio());
        }
        if (crewMemberDTO.getPhoto() != null) {
            crewMemberFromDb.setPhoto(crewMemberDTO.getPhoto());
        }

        crewMemberFromDb = crewMemberRepository.save(crewMemberFromDb);
        return CREW_MEMBER_MAPPER.crewMemberToCrewMemberDTO(crewMemberFromDb);
    }
}