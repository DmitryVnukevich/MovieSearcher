package com.example.moviesearcher.repository;

import com.example.moviesearcher.entities.CrewMembers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewMembersRepository extends JpaRepository<CrewMembers, Long> {
}
