package sch.youfitserver.kakaoOauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sch.youfitserver.kakaoOauth.dto.KakaoUserInfoResponseDto;
import sch.youfitserver.kakaoOauth.service.KakaoService;
import sch.youfitserver.user.service.UserService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
/**
    redirect_uri에 요청된 code를 파라미터로 받아서 사용
    성공하면 카카오로부터 받은 code를 카카오에 토큰 발급을 요청하면 토큰을 받을 수 있다.
    https://kauth.kakao.com/oauth/token url로 post 요청을 보내면 토큰을 받을 수 있다.
 **/
public class KakaoLoginController {

    private final KakaoService kakaoService;
    private final UserService userService;

    @GetMapping("/kakao/callback")
    public ResponseEntity<?> callback(@RequestParam("code") String code) throws IOException {
        String accessToken = kakaoService.getAccessTokenFromKakao(code);
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);
        if(userService.findByEmail(userInfo.getKakaoAccount().getEmail()) != null) {
            return ResponseEntity.ok(userService.findByEmail(userInfo.getKakaoAccount().getEmail()));
        }
        userService.save(userInfo);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userInfo);
    }
}
