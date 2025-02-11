package com.example.moviesearcher.repository;

import com.example.moviesearcher.entity.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {
}
