package com.example.moviesearcher.controller;

import com.example.moviesearcher.dto.UserInfoDTO;
import com.example.moviesearcher.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping
    public ResponseEntity<UserInfoDTO> createUserInfo(@RequestBody UserInfoDTO userInfoDTO) {
        UserInfoDTO savedUserInfo = userInfoService.saveUserInfo(userInfoDTO);
        return ResponseEntity.ok(savedUserInfo);
    }

    @GetMapping
    public ResponseEntity<List<UserInfoDTO>> getAllUserInfos() {
        List<UserInfoDTO> userInfos = userInfoService.findAllUserInfos();
        return ResponseEntity.ok(userInfos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDTO> getUserInfoById(@PathVariable Long id) {
        UserInfoDTO userInfo = userInfoService.findUserInfoById(id);
        if (userInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserInfoDTO> getUserInfoByUserId(@PathVariable Long userId) {
        UserInfoDTO userInfo = userInfoService.findUserInfoByUserId(userId);
        if (userInfo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userInfo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInfoDTO> updateUserInfo(@PathVariable Long id, @RequestBody UserInfoDTO userInfoDTO) {
        userInfoDTO.setId(id);
        UserInfoDTO updatedUserInfo = userInfoService.updateUserInfo(userInfoDTO);
        return ResponseEntity.ok(updatedUserInfo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserInfo(@PathVariable Long id) {
        userInfoService.deleteUserInfo(id);
        return ResponseEntity.noContent().build();
    }
}