package com.bistros.gs.remote.api.impl.naver;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class NaverPlaceClientFallBackFactory implements FallbackFactory<NaverPlaceSearchClient> {

  private final NaverPlaceClientFallBack fallBack;

  @Override
  public NaverPlaceSearchClient create(Throwable cause) {
    log.error("Naver API Fallback : {}", cause.getMessage() );
    return fallBack;
  }
}
