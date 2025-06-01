package com.example.moviesearcher.repository;

import com.example.moviesearcher.entity.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {
    List<CrewMember> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
    boolean existsByFirstNameAndLastNameAndBirthDate(String firstName, String lastName, Date birthDate);
}
