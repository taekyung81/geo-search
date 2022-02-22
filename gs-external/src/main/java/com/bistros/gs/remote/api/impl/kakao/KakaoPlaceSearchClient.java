package com.bistros.gs.remote.api.impl.kakao;

import com.bistros.gs.remote.api.impl.kakao.dto.KakakoPlaceApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@FeignClient(
    contextId = "kakao-api", name = "kakao-place-api", url = "https://dapi.kakao.com",
    fallbackFactory = KakaoPlaceClientFallBackFactory.class,
    configuration = KakaoClientHeaderProvider.class)
public interface KakaoPlaceSearchClient {
  @GetMapping("/v2/local/search/keyword.json")
  KakakoPlaceApiResponse getPlaces(@RequestParam("query") String query, @RequestParam("size") int size);


  default KakakoPlaceApiResponse getPlacesFallBack(String query, int size, Throwable throwable) {
    return new KakakoPlaceApiResponse(Collections.emptyList(), null);
  }
}

