package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.CrewMemberDTO;
import com.example.moviesearcher.entity.CrewMember;
import com.example.moviesearcher.entity.CrewRole;

import static com.example.moviesearcher.mapper.CrewMemberMapper.CREW_MEMBER_MAPPER;
import com.example.moviesearcher.repository.CrewMemberRepository;
import com.example.moviesearcher.repository.MovieCrewRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private final MovieCrewRepository movieCrewRepository;

    @Transactional
    public CrewMemberDTO saveCrewMember(CrewMemberDTO crewMemberDTO) {
        CrewMember crewMember = CREW_MEMBER_MAPPER.crewMemberDTOToCrewMember(crewMemberDTO);
        crewMember = crewMemberRepository.save(crewMember);
        return CREW_MEMBER_MAPPER.crewMemberToCrewMemberDTO(crewMember);
    }

    @Transactional(readOnly = true)
    public List<CrewMemberDTO> findAllCrewMembers() {
        return crewMemberRepository.findAll().stream()
                .map(CREW_MEMBER_MAPPER::crewMemberToCrewMemberDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PagedModel<CrewMemberDTO> findAllCrewMembers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CrewMember> crewMemberPage = crewMemberRepository.findAll(pageable);
        Page<CrewMemberDTO> crewMemberDTOPage = crewMemberPage.map(CREW_MEMBER_MAPPER::crewMemberToCrewMemberDTO);
        return new PagedModel<>(crewMemberDTOPage);
    }

    @Transactional(readOnly = true)
    public CrewMemberDTO findCrewMemberById(Long id) {
        return crewMemberRepository.findById(id)
                .map(CREW_MEMBER_MAPPER::crewMemberToCrewMemberDTO)
                .orElseThrow(() -> new EntityNotFoundException("Crew member not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<CrewMemberDTO> findCrewMembersByMovieId(Long movieId) {
        return movieCrewRepository.findByMovieId(movieId).stream()
                .map(movieCrew -> CREW_MEMBER_MAPPER.crewMemberToCrewMemberDTO(movieCrew.getCrewMember()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CrewMemberDTO> findActors() {
        return movieCrewRepository.findByRolesContaining(CrewRole.ACTOR).stream()
                .map(movieCrew -> CREW_MEMBER_MAPPER.crewMemberToCrewMemberDTO(movieCrew.getCrewMember()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CrewMemberDTO> findDirectors() {
        return movieCrewRepository.findByRolesContaining(CrewRole.DIRECTOR).stream()
                .map(movieCrew -> CREW_MEMBER_MAPPER.crewMemberToCrewMemberDTO(movieCrew.getCrewMember()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteCrewMember(Long id) {
        if (!crewMemberRepository.existsById(id)) {
            throw new EntityNotFoundException("Crew member not found with ID: " + id);
        }
        movieCrewRepository.deleteByCrewMemberId(id); // Удаляем связанные записи MovieCrew
        crewMemberRepository.deleteById(id);
    }

    @Transactional
    public CrewMemberDTO updateCrewMember(CrewMemberDTO crewMemberDTO) {
        CrewMember crewMemberFromDb = crewMemberRepository.findById(crewMemberDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Crew member not found with ID: " + crewMemberDTO.getId()));

        if (crewMemberDTO.getFirstName() != null) {
            crewMemberFromDb.setFirstName(crewMemberDTO.getFirstName());
        }
        if (crewMemberDTO.getLastName() != null) {
            crewMemberFromDb.setLastName(crewMemberDTO.getLastName());
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