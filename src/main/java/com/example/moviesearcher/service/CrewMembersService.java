package com.example.moviesearcher.service;

import com.example.moviesearcher.entities.CrewMembers;
import com.example.moviesearcher.repository.CrewMembersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CrewMembersService {

    private final CrewMembersRepository crewMembersRepository;

    public CrewMembersService(CrewMembersRepository crewMembersRepository) {
        this.crewMembersRepository = crewMembersRepository;
    }

    @Transactional
    public CrewMembers saveCrewMember(CrewMembers crewMember) {
        return crewMembersRepository.save(crewMember);
    }

    public List<CrewMembers> findAllCrewMembers() {
        return crewMembersRepository.findAll();
    }

    public CrewMembers findCrewMemberById(Long id) {
        return crewMembersRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteCrewMember(Long id) {
        crewMembersRepository.deleteById(id);
    }

    @Transactional
    public CrewMembers updateCrewMember(CrewMembers crewMember) {
        return crewMembersRepository.save(crewMember);
    }
}
