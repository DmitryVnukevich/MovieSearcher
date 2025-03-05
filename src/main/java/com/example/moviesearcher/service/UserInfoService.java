package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.entity.User;
import com.example.moviesearcher.entity.UserInfo;
import static com.example.moviesearcher.mapper.UserInfoMapper.USER_INFO_MAPPER;
import com.example.moviesearcher.repository.UserInfoRepository;
import com.example.moviesearcher.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;
    @Transactional
    public UserInfoDTO saveUserInfo(UserInfoDTO userInfoDTO) {
        User user = userRepository.findById(userInfoDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userInfoDTO.getUserId()));

        UserInfo userInfo = USER_INFO_MAPPER.userInfoDTOToUserInfo(userInfoDTO);
        userInfo.setUser(user);
        userInfo = userInfoRepository.save(userInfo);

        return USER_INFO_MAPPER.userInfoToUserInfoDTO(userInfo);
    }

    public UserInfoDTO findUserInfoById(Long id) {
        return userInfoRepository.findById(id)
                .map(USER_INFO_MAPPER::userInfoToUserInfoDTO)
                .orElse(null);
    }

    public UserInfoDTO findUserInfoByUserId(Long userId) {
        return userInfoRepository.findByUserId(userId)
                .map(USER_INFO_MAPPER::userInfoToUserInfoDTO)
                .orElse(null);
    }

    @Transactional
    public UserInfoDTO updateUserInfo(UserInfoDTO userInfoDTO) {
        User user = userRepository.findById(userInfoDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userInfoDTO.getUserId()));

        UserInfo userInfo = USER_INFO_MAPPER.userInfoDTOToUserInfo(userInfoDTO);
        userInfo.setUser(user);
        userInfo = userInfoRepository.save(userInfo);

        return USER_INFO_MAPPER.userInfoToUserInfoDTO(userInfo);
    }

    @Transactional
    public void deleteUserInfo(Long id) {
        userInfoRepository.deleteById(id);
    }
}