package sch.youfitserver.kakaoOauth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private String email;
    private String nickname;

    public UserResponseDto(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
