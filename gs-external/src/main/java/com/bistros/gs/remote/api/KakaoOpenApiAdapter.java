package com.bistros.gs.remote.api;

import com.bistros.gs.application.search.port.GetPlacePort;
import com.bistros.gs.domain.KakaoResource;
import com.bistros.gs.domain.Place;
import com.bistros.gs.domain.Places;
import com.bistros.gs.remote.api.impl.kakao.KakaoPlaceSearchClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Component
class KakaoOpenApiAdapter extends AbstractPlaceApiAdapter<KakaoResource> implements GetPlacePort<KakaoResource> {

  private final KakaoPlaceSearchClient kakaoRmoeteClient;

  public KakaoOpenApiAdapter(KakaoPlaceSearchClient kakaoRmoeteClient) {
    super(new KakaoResource());
    this.kakaoRmoeteClient = kakaoRmoeteClient;
  }


  @Override
  public Places getPlaces(String query, int count) {
    var res = kakaoRmoeteClient.getPlaces(query, count);
    var index = new AtomicInteger();
    var items = res.getDocuments().stream()
        .map(c ->
            Place.builder()
                .placeName(c.getPlace_name())
                .phone(c.getPhone())
                .address(c.getAddress_name())
                .origin(getApiId())
                .originOrder(index.incrementAndGet())
                .build()
        ).collect(Collectors.toUnmodifiableList());
    return Places.of(items);
  }
}
