package com.example.moviesearcher.mapper;

import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.entity.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper (uses = {GenreMapper.class, CrewMemberMapper.class})
public interface UserInfoMapper {
    UserInfoMapper INSTANCE = Mappers.getMapper(UserInfoMapper.class);

    @Mapping(source = "user.id", target = "userId")
    UserInfoDTO userInfoToUserInfoDTO(UserInfo userInfo);

    @Mapping(source = "userId", target = "user.id")
    UserInfo userInfoDTOToUserInfo(UserInfoDTO userInfoDTO);
}