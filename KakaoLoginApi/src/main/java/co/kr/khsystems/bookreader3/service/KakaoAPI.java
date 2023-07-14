package co.kr.khsystems.bookreader3.service;

import co.kr.khsystems.bookreader3.controller.AuthenticationController;
import co.kr.khsystems.bookreader3.entity.KakaoUserInfo;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class KakaoAPI {

    private final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Value("${kakao.token.url}")
    private String KAKAO_TOKEN_URL;

    @Value("${kakao.user.info.url}")
    private String KAKAO_USER_INFO_URL;

    @Value("${kakao.redirect.url}")
    private String REDIRECT_URL;

    @Value("${kakao.api.key}")
    private String KAKAO_API_KEY;

    public String getAccessToken(String authorizationCode) {

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "authorization_code");
        map.add("client_id", KAKAO_API_KEY);
        map.add("redirect_uri", REDIRECT_URL);
        map.add("code", authorizationCode);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);

        Map<String, Object> response = restTemplate.postForObject(KAKAO_TOKEN_URL, requestEntity, HashMap.class);
        log.info("response is {}", response);

        return response.get("access_token").toString();
    }

    public KakaoUserInfo getUserInfo(String accessToken){

        RestTemplate restTemplate = new RestTemplate();

        log.info("getUserInfo accessToken is {}", accessToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(headers);


        String response = restTemplate.postForObject(KAKAO_USER_INFO_URL, requestEntity, String.class);
        JsonObject rootObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject properties = rootObject.getAsJsonObject("properties");
        JsonObject accountObject = rootObject.getAsJsonObject("kakao_account");

        log.info("response is {}", response.toString());

        KakaoUserInfo kakaoUserInfo = KakaoUserInfo.builder()
                .id(rootObject.get("id").getAsString())
                .nickname(properties.get("nickname").getAsString())
                .email(accountObject.get("email").getAsString())
                .build();

        log.info("kakaoUserInfo is {}", kakaoUserInfo);

        return kakaoUserInfo;

    }

}

