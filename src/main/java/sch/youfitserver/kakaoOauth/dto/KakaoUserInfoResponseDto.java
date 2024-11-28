package sch.youfitserver.kakaoOauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoUserInfoResponseDto{
    @JsonProperty("id")                     // 회원 번호
    public Long id;

    @JsonProperty("connected_at")           // 서비스에 연결 완료된 시각. UT
    public String connectedAt;

    @JsonProperty("properties")             // 사용자 프로퍼티
    public HashMap<String, String> properties;

    @JsonProperty("kakao_account")          // 카카오 계정 정보
    public KakaoAccount kakaoAccount;

    @JsonProperty("synched_at")              //카카오싱크 간편가입을 통해 로그인한 시각. UTC
    public Date synchedAt;

    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class KakaoAccount{
        @JsonProperty("profile_nickname_needs_agreement")    // 닉네임 제공 동의 여부
        public boolean isNickNameAgree;

        @JsonProperty("email_needs_agreement")               // 이메일 제공 동의 여부
        public boolean isEmailAgree;

        @JsonProperty("is_email_valid")                      // 이메일 유효 여부
        public boolean isEmailValid;

        @JsonProperty("profile")                             // 사용자 프로필 정보
        public Profile profile;

        @JsonProperty("is_email_verified")                   // 이메일 인증 여부
        public boolean isEmailVerified;

        @JsonProperty("email")                               // 카카오계졍 대표 이메일
        public String email;
    }
    @Getter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Profile{

        @JsonProperty("nickname")                           // 닉네임
        public String nickname;

        @JsonProperty("is_default_nickname")                // 닉네임이 기본 닉네임인지 여부
        public Boolean isDefaultNickname;
    }
}