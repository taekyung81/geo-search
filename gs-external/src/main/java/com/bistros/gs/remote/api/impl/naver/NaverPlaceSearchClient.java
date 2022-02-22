package com.bistros.gs.remote.api.impl.naver;

import com.bistros.gs.remote.api.impl.naver.dto.NaverPlaceApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
    contextId = "naver-api", name = "naver-place-api",
    url = "https://openapi.naver.com",
    fallbackFactory = NaverPlaceClientFallBackFactory.class,
    configuration = NaverClientHeaderProvider.class)
public interface NaverPlaceSearchClient {
  @GetMapping("/v1/search/local.json")
  NaverPlaceApiResponse getPlaces(@RequestParam("query") String query, @RequestParam("display") int display);
}
