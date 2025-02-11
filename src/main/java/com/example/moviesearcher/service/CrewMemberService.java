package com.example.moviesearcher.service;

import com.example.moviesearcher.entity.CrewMember;
import com.example.moviesearcher.repository.CrewMemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CrewMemberService {

    private final CrewMemberRepository crewMemberRepository;

    public CrewMemberService(CrewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    @Transactional
    public CrewMember saveCrewMember(CrewMember crewMember) {
        return crewMemberRepository.save(crewMember);
    }

    public List<CrewMember> findAllCrewMembers() {
        return crewMemberRepository.findAll();
    }

    public CrewMember findCrewMemberById(Long id) {
        return crewMemberRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteCrewMember(Long id) {
        crewMemberRepository.deleteById(id);
    }

    @Transactional
    public CrewMember updateCrewMember(CrewMember crewMember) {
        return crewMemberRepository.save(crewMember);
    }
}
