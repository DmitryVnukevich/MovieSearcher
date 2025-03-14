package com.example.moviesearcher.service;

import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.entity.CrewRole;
import com.example.moviesearcher.entity.User;
import com.example.moviesearcher.entity.UserInfo;
import static com.example.moviesearcher.mapper.UserInfoMapper.USER_INFO_MAPPER;

import com.example.moviesearcher.repository.CrewMemberRepository;
import com.example.moviesearcher.repository.GenreRepository;
import com.example.moviesearcher.repository.UserInfoRepository;
import com.example.moviesearcher.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;
    private final CrewMemberRepository crewMemberRepository;

    @Transactional
    public UserInfoDTO saveUserInfo(UserInfoDTO userInfoDTO) {
        User user = userRepository.findById(userInfoDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userInfoDTO.getUserId()));

        if (userInfoRepository.findByUserId(userInfoDTO.getUserId()).isPresent()) {
            throw new IllegalArgumentException("UserInfo already exists for user ID: " + userInfoDTO.getUserId());
        }

        validateRanges(userInfoDTO);
        validateIds(userInfoDTO);

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
        UserInfo userInfoFromDb = userInfoRepository.findById(userInfoDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("UserInfo not found with ID: " + userInfoDTO.getId()));

        if (userInfoDTO.getUserId() != null) {
            User user = userRepository.findById(userInfoDTO.getUserId())
                    .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userInfoDTO.getUserId()));
            userInfoFromDb.setUser(user);
        }

        validateRanges(userInfoDTO);
        validateIds(userInfoDTO);

        if (userInfoDTO.getPreferredGenreIds() != null) {
            userInfoFromDb.setPreferredGenreIds(userInfoDTO.getPreferredGenreIds());
        }
        if (userInfoDTO.getFavoriteActorIds() != null) {
            userInfoFromDb.setFavoriteActorIds(userInfoDTO.getFavoriteActorIds());
        }
        if (userInfoDTO.getFavoriteDirectorIds() != null) {
            userInfoFromDb.setFavoriteDirectorIds(userInfoDTO.getFavoriteDirectorIds());
        }
        if (userInfoDTO.getContentTypePreference() != null) {
            userInfoFromDb.setContentTypePreference(userInfoDTO.getContentTypePreference());
        }
        if (userInfoDTO.getMinRating() != null) {
            userInfoFromDb.setMinRating(userInfoDTO.getMinRating());
        }
        if (userInfoDTO.getMaxRating() != null) {
            userInfoFromDb.setMaxRating(userInfoDTO.getMaxRating());
        }
        if (userInfoDTO.getMinDuration() != null) {
            userInfoFromDb.setMinDuration(userInfoDTO.getMinDuration());
        }
        if (userInfoDTO.getMaxDuration() != null) {
            userInfoFromDb.setMaxDuration(userInfoDTO.getMaxDuration());
        }
        if (userInfoDTO.getPreferredAgeRating() != null) {
            userInfoFromDb.setPreferredAgeRating(userInfoDTO.getPreferredAgeRating());
        }

        userInfoFromDb = userInfoRepository.save(userInfoFromDb);
        return USER_INFO_MAPPER.userInfoToUserInfoDTO(userInfoFromDb);
    }

    @Transactional
    public void deleteUserInfo(Long id) {
        if (!userInfoRepository.existsById(id)) {
            throw new IllegalArgumentException("UserInfo not found with ID: " + id);
        }
        userInfoRepository.deleteById(id);;
    }

    private void validateRanges(UserInfoDTO userInfoDTO) {
        if (userInfoDTO.getMinRating() != null && userInfoDTO.getMaxRating() != null &&
                userInfoDTO.getMinRating() > userInfoDTO.getMaxRating()) {
            throw new IllegalArgumentException("Min rating cannot exceed max rating");
        }
        if (userInfoDTO.getMinDuration() != null && userInfoDTO.getMaxDuration() != null &&
                userInfoDTO.getMinDuration() > userInfoDTO.getMaxDuration()) {
            throw new IllegalArgumentException("Min duration cannot exceed max duration");
        }
    }

    private void validateIds(UserInfoDTO userInfoDTO) {
        if (userInfoDTO.getPreferredGenreIds() != null) {
            for (Long genreId : userInfoDTO.getPreferredGenreIds()) {
                genreRepository.findById(genreId)
                        .orElseThrow(() -> new IllegalArgumentException("Genre not found with ID: " + genreId));
            }
        }
        if (userInfoDTO.getFavoriteActorIds() != null) {
            for (Long actorId : userInfoDTO.getFavoriteActorIds()) {
                crewMemberRepository.findById(actorId)
                        .filter(cm -> cm.getRoles().contains(CrewRole.ACTOR))
                        .orElseThrow(() -> new IllegalArgumentException("Actor not found with ID: " + actorId));
            }
        }
        if (userInfoDTO.getFavoriteDirectorIds() != null) {
            for (Long directorId : userInfoDTO.getFavoriteDirectorIds()) {
                crewMemberRepository.findById(directorId)
                        .filter(cm -> cm.getRoles().contains(CrewRole.DIRECTOR))
                        .orElseThrow(() -> new IllegalArgumentException("Director not found with ID: " + directorId));
            }
        }
    }
}