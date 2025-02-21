package com.example.moviesearcher.mapper;

import com.example.moviesearcher.dto.CrewMemberDTO;
import com.example.moviesearcher.entity.CrewMember;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CrewMemberMapper {
    CrewMemberMapper INSTANCE = Mappers.getMapper(CrewMemberMapper.class);
    @Mapping(source = "roles", target = "roles")
    CrewMemberDTO crewMemberToCrewMemberDTO(CrewMember crewMember);
    @Mapping(source = "roles", target = "roles")
    CrewMember crewMemberDTOToCrewMember(CrewMemberDTO crewMemberDTO);
}
