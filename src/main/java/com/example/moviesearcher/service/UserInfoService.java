package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.entity.User;
import com.example.moviesearcher.entity.UserInfo;
import com.example.moviesearcher.mapper.UserInfoMapper;
import com.example.moviesearcher.repository.UserInfoRepository;
import com.example.moviesearcher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserInfoDTO saveUserInfo(UserInfoDTO userInfoDTO) {
        User user = userRepository.findById(userInfoDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userInfoDTO.getUserId()));

        UserInfo userInfo = UserInfoMapper.INSTANCE.userInfoDTOToUserInfo(userInfoDTO);
        userInfo.setUser(user);
        userInfo = userInfoRepository.save(userInfo);

        return UserInfoMapper.INSTANCE.userInfoToUserInfoDTO(userInfo);
    }

    public List<UserInfoDTO> findAllUserInfos() {
        return userInfoRepository.findAll().stream()
                .map(UserInfoMapper.INSTANCE::userInfoToUserInfoDTO)
                .collect(Collectors.toList());
    }

    public UserInfoDTO findUserInfoById(Long id) {
        return userInfoRepository.findById(id)
                .map(UserInfoMapper.INSTANCE::userInfoToUserInfoDTO)
                .orElse(null);
    }

    public UserInfoDTO findUserInfoByUserId(Long userId) {
        return userInfoRepository.findByUserId(userId)
                .map(UserInfoMapper.INSTANCE::userInfoToUserInfoDTO)
                .orElse(null);
    }

    @Transactional
    public UserInfoDTO updateUserInfo(UserInfoDTO userInfoDTO) {
        User user = userRepository.findById(userInfoDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userInfoDTO.getUserId()));

        UserInfo userInfo = UserInfoMapper.INSTANCE.userInfoDTOToUserInfo(userInfoDTO);
        userInfo.setUser(user);
        userInfo = userInfoRepository.save(userInfo);

        return UserInfoMapper.INSTANCE.userInfoToUserInfoDTO(userInfo);
    }

    @Transactional
    public void deleteUserInfo(Long id) {
        userInfoRepository.deleteById(id);
    }
}