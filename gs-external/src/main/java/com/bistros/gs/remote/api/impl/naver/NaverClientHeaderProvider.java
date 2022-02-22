package com.bistros.gs.remote.api.impl.naver;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NaverClientHeaderProvider implements RequestInterceptor {

  public final String naverApiClientId;
  public final String naverApiClientSecret;

  public NaverClientHeaderProvider(NaverPlaceSearchProperties properties) {
    this.naverApiClientId = properties.getClientId();
    this.naverApiClientSecret = properties.getClientSecret();
  }

  @Override
  public void apply(RequestTemplate template) {
    template.header("X-Naver-Client-Id", naverApiClientId);
    template.header("X-Naver-Client-Secret", naverApiClientSecret);
  }


}
