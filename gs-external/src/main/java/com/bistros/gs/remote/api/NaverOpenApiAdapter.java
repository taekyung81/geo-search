package com.bistros.gs.remote.api;

import com.bistros.gs.application.search.port.GetPlacePort;
import com.bistros.gs.domain.OtherResource;
import com.bistros.gs.domain.Place;
import com.bistros.gs.domain.Places;
import com.bistros.gs.remote.api.impl.naver.NaverPlaceSearchClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
class NaverOpenApiAdapter extends AbstractPlaceApiAdapter<OtherResource> implements GetPlacePort<OtherResource> {

  private final NaverPlaceSearchClient naverRemoteClient;

  public NaverOpenApiAdapter(NaverPlaceSearchClient naverRemoteClient) {
    super(new OtherResource("naver"));
    this.naverRemoteClient = naverRemoteClient;
  }

  @Override
  public Places getPlaces(String query, int count) {
    var response = naverRemoteClient.getPlaces(query, count);
    var index = new AtomicInteger();
    var items = response.getItems().stream()
        .map(c ->
            Place.builder()
                .placeName(c.getTitle())
                .phone(c.getTelephone())
                .address(c.getAddress())
                .origin(getApiId())
                .originOrder(index.incrementAndGet())
                .build()
        ).collect(Collectors.toUnmodifiableList());
    return Places.of(items);
  }
}
