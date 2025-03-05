package com.example.moviesearcher.repository;

import com.example.moviesearcher.entity.CrewMember;
import com.example.moviesearcher.entity.CrewRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {
    List<CrewMember> findByRolesContaining(CrewRole role);
}
