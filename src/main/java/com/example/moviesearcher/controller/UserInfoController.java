package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.CrewMemberDTO;
import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.service.CrewMemberService;
import com.example.moviesearcher.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user-info")
@RequiredArgsConstructor
public class UserInfoController {

    private final UserInfoService userInfoService;
    private final CrewMemberService crewMemberService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public UserInfoDTO createUserInfo(@Valid @RequestBody UserInfoDTO userInfoDTO) {
        return userInfoService.saveUserInfo(userInfoDTO);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public UserInfoDTO getUserInfoByUserId(@PathVariable Long userId) {
        UserInfoDTO userInfo = userInfoService.findUserInfoByUserId(userId);
        return userInfo;
    }

    @GetMapping("/actors")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public List<CrewMemberDTO> getAllActors() { return crewMemberService.findActors(); }

    @GetMapping("/directors")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public List<CrewMemberDTO> getAllDirectors() {
        return crewMemberService.findDirectors();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public UserInfoDTO updateUserInfo(@PathVariable Long id, @Valid @RequestBody UserInfoDTO userInfoDTO) {
        userInfoDTO.setId(id);
        return userInfoService.updateUserInfo(userInfoDTO);
    }

    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public void deleteUserInfoByUserId(@PathVariable Long userId) {
        userInfoService.deleteUserInfoByUserId(userId);
    }
}