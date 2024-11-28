package sch.youfitserver.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import sch.youfitserver.user.dto.UserRequestDto;
import sch.youfitserver.user.dto.UserResponseDto;
import sch.youfitserver.user.entity.User;
import sch.youfitserver.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PatchMapping("/user/{userId}")
    public ResponseEntity<User> update(@PathVariable Long userId, @RequestBody UserRequestDto request) {
        User updateuser = userService.update(userId, request);
        return ResponseEntity.ok()
                .body(updateuser);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserResponseDto> findUser(@PathVariable Long userId){
        User user = userService.findById(userId);
        return ResponseEntity.ok()
                .body(new UserResponseDto(user));
    }
}
