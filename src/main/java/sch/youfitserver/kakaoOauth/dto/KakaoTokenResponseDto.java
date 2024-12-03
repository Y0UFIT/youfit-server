package sch.youfitserver.kakaoOauth.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // JSON 프로퍼티의 처리를 무시하는 역할 true : 예외나 경고없이 무시됨.
/**
    token을 발급 받거나 token의 생명주기(expire), refresh토큰 발급 등 토큰을 관리하는 dto
 **/
public class KakaoTokenResponseDto {

    @JsonProperty("token_type")
    public String tokenType;

    @JsonProperty("access_token")
    public String accessToken;

    @JsonProperty("expired_in")
    public int expiredIn;

    @JsonProperty("refresh_token")
    public String refreshToken;

    @JsonProperty("refresh_token_expires_in")
    public int refreshTokenExpiresIn;

    @JsonProperty("scope") // 사용자로부터 받은 정보들
    public String scope;
}
