package sch.youfitserver.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import sch.youfitserver.user.dto.request.UserRequestDto;
import sch.youfitserver.user.dto.response.UserHomeDto;
import sch.youfitserver.user.dto.response.UserResponseDto;
import sch.youfitserver.user.entity.User;
import sch.youfitserver.user.service.UserService;

@Controller
@RequiredArgsConstructor  // final을 사용하는 userService를 매개변수로 갖는 생성자를 생성해줌
/** @RequestBody : 객체를 JSON 형식을 반환한다**/
public class UserApiController {

    private final UserService userService;

    @PatchMapping("/user/{userId}")
    public ResponseEntity<User> update(@PathVariable Long userId, @RequestBody UserRequestDto request) {
        User updateuser = userService.update(userId, request);
        return ResponseEntity.ok()
                .body(updateuser);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> findUser(@PathVariable Long userId){
        User user = userService.findById(userId);
        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
        return ResponseEntity.ok()
                .body(new UserResponseDto(user));
    }
}
