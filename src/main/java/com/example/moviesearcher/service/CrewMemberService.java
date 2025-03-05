package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.CrewMemberDTO;
import com.example.moviesearcher.entity.CrewMember;
import com.example.moviesearcher.entity.CrewRole;
import static com.example.moviesearcher.mapper.CrewMemberMapper.CREW_MEMBER_MAPPER;
import com.example.moviesearcher.repository.CrewMemberRepository;
import lombok.RequiredArgsConstructor;
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
        crewMemberRepository.deleteById(id);
    }

    @Transactional
    public CrewMemberDTO updateCrewMember(CrewMemberDTO crewMemberDTO) {
        CrewMember crewMember = CREW_MEMBER_MAPPER.crewMemberDTOToCrewMember(crewMemberDTO);
        crewMember = crewMemberRepository.save(crewMember);
        return CREW_MEMBER_MAPPER.crewMemberToCrewMemberDTO(crewMember);
    }
}