package com.bistros.gs.remote.api.impl.kakao;

import lombok.AllArgsConstructor;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class KakaoPlaceClientFallBackFactory implements FallbackFactory<KakaoPlaceSearchClient> {

  private final KakaoPlaceClientFallBack fallBack;

  @Override
  public KakaoPlaceSearchClient create(Throwable cause) {
    return fallBack;
  }


}
