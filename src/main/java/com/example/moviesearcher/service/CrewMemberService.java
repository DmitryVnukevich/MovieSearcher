package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.CrewMemberDTO;
import com.example.moviesearcher.entity.CrewMember;
import com.example.moviesearcher.mapper.CrewMemberMapper;
import com.example.moviesearcher.repository.CrewMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrewMemberService {

    private final CrewMemberRepository crewMemberRepository;

    public CrewMemberService(CrewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    @Transactional
    public CrewMemberDTO saveCrewMember(CrewMemberDTO crewMemberDTO) {
        CrewMember crewMember = CrewMemberMapper.INSTANCE.crewMemberDTOToCrewMember(crewMemberDTO);
        crewMember = crewMemberRepository.save(crewMember);
        return CrewMemberMapper.INSTANCE.crewMemberToCrewMemberDTO(crewMember);
    }

    public List<CrewMemberDTO> findAllCrewMembers() {
        return crewMemberRepository.findAll().stream()
                .map(CrewMemberMapper.INSTANCE::crewMemberToCrewMemberDTO)
                .collect(Collectors.toList());
    }

    public CrewMemberDTO findCrewMemberById(Long id) {
        return crewMemberRepository.findById(id)
                .map(CrewMemberMapper.INSTANCE::crewMemberToCrewMemberDTO)
                .orElse(null);
    }

    @Transactional
    public void deleteCrewMember(Long id) {
        crewMemberRepository.deleteById(id);
    }

    @Transactional
    public CrewMemberDTO updateCrewMember(CrewMemberDTO crewMemberDTO) {
        CrewMember crewMember = CrewMemberMapper.INSTANCE.crewMemberDTOToCrewMember(crewMemberDTO);
        crewMember = crewMemberRepository.save(crewMember);
        return CrewMemberMapper.INSTANCE.crewMemberToCrewMemberDTO(crewMember);
    }
}
