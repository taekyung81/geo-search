package com.bistros.gs.remote.api.impl.kakao;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KakaoClientHeaderProvider implements RequestInterceptor {

  public static final String AUTHORIZATION = "Authorization";
  private final String KAKAO_RESTAPI_KEY;

  public KakaoClientHeaderProvider(@Value("${key.kakao.rest-key}") String kakaoRestKey) {
    this.KAKAO_RESTAPI_KEY = String.format("KakaoAK %s", kakaoRestKey);
  }

  @Override
  public void apply(RequestTemplate template) {
    template.header(AUTHORIZATION, KAKAO_RESTAPI_KEY);
  }
}
