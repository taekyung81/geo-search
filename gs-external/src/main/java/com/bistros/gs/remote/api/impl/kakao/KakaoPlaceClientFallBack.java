package com.bistros.gs.remote.api.impl.kakao;

import com.bistros.gs.remote.api.impl.kakao.dto.KakakoPlaceApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class KakaoPlaceClientFallBack implements KakaoPlaceSearchClient {
  @Override
  public KakakoPlaceApiResponse getPlaces(String query, int size) {
    log.warn("{} fallback", query);
    return new KakakoPlaceApiResponse(Collections.emptyList(), null);
  }
}
