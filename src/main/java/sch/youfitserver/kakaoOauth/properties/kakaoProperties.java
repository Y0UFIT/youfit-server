package sch.youfitserver.kakaoOauth.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
/**
    kakao Oauth를 사용하기 위한 client_id와 redirect_url 설정
 **/
public class kakaoProperties {

    @Value("${kakao.client_id}")
    private String client;

    @Value("@{kakao.redirect_uri}")
    private String redirect;
}
