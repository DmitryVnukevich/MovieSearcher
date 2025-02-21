package com.example.moviesearcher.mapper;

import com.example.moviesearcher.dto.CrewMemberDTO;
import com.example.moviesearcher.entity.CrewMember;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CrewMemberMapper {
    CrewMemberMapper INSTANCE = Mappers.getMapper(CrewMemberMapper.class);

    CrewMemberDTO crewMemberToCrewMemberDTO(CrewMember crewMember);

    CrewMember crewMemberDTOToCrewMember(CrewMemberDTO crewMemberDTO);
}
