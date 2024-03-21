package com.jindev.vict.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SnsUtils {

    @Value("${oauth.naver.secret}")
    private String secret;

    @JsonProperty("client-id")
    @Value("${oauth.naver.client-id}")
    private String clientId;

    @Value("${oauth.naver.url.auth}")
    private String auth;

    @Value("${oauth.naver.url.api}")
    private String api;

}
