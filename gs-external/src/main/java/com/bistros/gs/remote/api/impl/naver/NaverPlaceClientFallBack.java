package com.bistros.gs.remote.api.impl.naver;

import com.bistros.gs.remote.api.impl.naver.dto.NaverPlaceApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
public class NaverPlaceClientFallBack implements NaverPlaceSearchClient {
  @Override
  public NaverPlaceApiResponse getPlaces(String query, int display) {
    log.warn("query:{} fallback", query);
    return new NaverPlaceApiResponse(0, 0, 0, Collections.emptyList());
  }
}
