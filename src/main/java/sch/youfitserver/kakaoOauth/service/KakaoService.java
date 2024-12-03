package sch.youfitserver.kakaoOauth.service;

import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import sch.youfitserver.kakaoOauth.dto.KakaoTokenResponseDto;
import sch.youfitserver.kakaoOauth.dto.KakaoUserInfoResponseDto;

@Slf4j
@Service
@RequiredArgsConstructor
/**
    Webclient로 HTTP요청을 구현하기 위해 gradle에 webflux 의존성을 추가해 주었다.
**/
public class KakaoService {
    private String clientId;
    private final String KAUTH_TOKEN_URL_HOST;
    private final String KAUTH_USER_URL_HOST;

    @Autowired
    public KakaoService(@Value("${kakao.client_id}") String clientId){
        this.clientId = clientId;
        KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
        KAUTH_USER_URL_HOST = "https://kapi.kakao.com";
    }
    public String getAccessTokenFromKakao(String code){

        KakaoTokenResponseDto kakaoTokenResponseDto = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        // url 예시 : https://kauth.kakao.com/oauth/token?grant_type=authorization_code&client_id=${client_id}&code=${code}
                        .queryParam("client_id", clientId)
                        .queryParam("code", code)
                        .queryParam("prompt", "login")
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED + "; charset=utf-8")
                .retrieve()// HTTP요청을 받아오면 Request Body 내용이 미리 지정해둔 dto에 json이 직렬화되어 들어감.
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoTokenResponseDto.class)
                .block();

        log.info(" [Kakao Service] Access Token ------> {}", kakaoTokenResponseDto.getAccessToken());
        log.info(" [Kakao Service] Refresh Token ------> {}", kakaoTokenResponseDto.getRefreshToken());
        //제공 조건: OpenID Connect가 활성화 된 앱의 토큰 발급 요청인 경우 또는 scope에 openid를 포함한 추가 항목 동의 받기 요청을 거친 토큰 발급 요청인 경우
        log.info(" [Kakao Service] Scope ------> {}", kakaoTokenResponseDto.getScope());

        return kakaoTokenResponseDto.getAccessToken();
    }
    public KakaoUserInfoResponseDto getUserInfo(String accessToken){

        KakaoUserInfoResponseDto userInfo = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")   // https://kapi.kakao.com/v2/user/me url 헤더에 Bearer token을 추가해서 get요청을 보냄
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken) // accessToken 인가
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED + "; charset=utf-8")
                .retrieve()// 요청을 받아오면 Response Body를 받을 수 있음
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();
        log.info(" [Kakao Service] user_Id ------> {}", userInfo.getUserId());

        return userInfo;
    }
}
