package com.bistros.gs.application.search.service;

import com.bistros.gs.application.search.port.GetPlacePort;
import com.bistros.gs.application.search.service.GetPlaceService.GetPlaceRequest;
import com.bistros.gs.domain.KakaoResource;
import com.bistros.gs.domain.OtherResource;
import com.bistros.gs.domain.Place;
import com.bistros.gs.domain.Places;
import static java.util.stream.Collectors.toUnmodifiableList;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

class GetPlaceServiceTest {

  @Test
  @DisplayName("GetPlaceService  동작 확인 ")
  public void testGetPlaceServiceMultipleDataSet() {
    GetPlacePort<KakaoResource> kakao = (query, count) -> genTestData("A곱창", "B곱창", "C곱창", "D곱창");
    List<GetPlacePort<OtherResource>> others = List.of((query, count) -> genTestData("A곱창", "E곱창", "D곱창", "C곱창"));
    var apiCaller = new PlaceApiCaller(Executors.newSingleThreadExecutor(), kakao, others);

    var placeService = new GetPlaceService(s -> {
    }, new PlaceCollector((c) -> c), apiCaller);

    var expected = List.of("A곱창", "C곱창", "D곱창", "B곱창", "E곱창");
    var resultNames = placeService.apply(GetPlaceRequest.of("곱창", 4))
        .getPlaces().getData().stream()
        .map(Place::getPlaceName)
        .collect(Collectors.toList());

    assertThat(resultNames).containsAll(expected);
  }

  @Test
  @DisplayName("GetPlaceService 동작 확인 2 ")
  public void testGetPlaceServiceSingleDataSet() {
    GetPlacePort<KakaoResource> kakao = (query, count) -> genTestData("A곱창", "B곱창", "C곱창", "D곱창");
    List<GetPlacePort<OtherResource>> others = List.of((query, count) -> Places.of(Collections.emptyList()));
    var apiCaller = new PlaceApiCaller(Executors.newSingleThreadExecutor(), kakao, others);
    var placeService = new GetPlaceService(s -> {
    }, new PlaceCollector((c) -> c), apiCaller);

    var expected = List.of("A곱창", "B곱창", "C곱창", "D곱창");
    var resultNames = placeService.apply(GetPlaceRequest.of("곱창", 4))
        .getPlaces().getData().stream()
        .map(Place::getPlaceName)
        .collect(Collectors.toList());

    assertThat(resultNames).containsAll(expected);
  }

  private Places genTestData(String... names) {
    return Places.of(Arrays.stream(names)
        .map(name -> Place.builder().placeName(name).build())
        .collect(toUnmodifiableList()));
  }
}