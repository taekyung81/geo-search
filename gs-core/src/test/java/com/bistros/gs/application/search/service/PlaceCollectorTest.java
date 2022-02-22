package com.bistros.gs.application.search.service;

import com.bistros.gs.domain.Place;
import com.bistros.gs.domain.Places;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toUnmodifiableList;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

@DisplayName("API 결과 셋트 중복 확인")
class PlaceCollectorTest {

  @Test
  @DisplayName("카카오 API 결과세트만 존재하면 그대로 반환한다")
  public void m1() {
    var kakaoPlaces = genTestData("A곱창", "B곱창", "C곱창", "D곱창");
//    var naverPlaces = genTestData("A곱창", "E곱창", "D곱창", "C곱창");

    var result = new PlaceCollector(null).intersect(kakaoPlaces, emptyList());

    var expected = genTestData("A곱창", "B곱창", "C곱창", "D곱창");
    assertThat(result.getData()).containsAll(expected.getData());
  }

  @Test
  @DisplayName("카카오, 네이버 결과 세트가 존재할 경우 정상적으로 중복 장소를 반환한다")
  public void m2() {
    var kakaoPlaces = genTestData("A곱창", "B곱창", "C곱창", "D곱창");
    var naverPlaces = genTestData("A곱창", "E곱창", "D곱창", "C곱창");

    var result = new PlaceCollector(null).intersect(kakaoPlaces, List.of(naverPlaces));

    var expected = genTestData("A곱창", "C곱창", "D곱창");
    assertThat(result.getData()).containsAll(expected.getData());
  }


  @Nested
  @DisplayName("카카오, 네이버에 추가로 구글 API를 추가 했을 때 ")
  class TestGoogle {

    @Test
    @DisplayName("API 결과가 0건 일 경우 중복 장소 결과도 없다")
    public void m2() {
      var kakaoPlaces = genTestData("A곱창", "B곱창", "C곱창", "D곱창");
      var naverPlaces = genTestData("A곱창", "E곱창", "D곱창", "C곱창");

      var googlePlaces = Places.EMPTY;

      var result = new PlaceCollector(null).intersect(kakaoPlaces, List.of(naverPlaces, googlePlaces));
      assertThat(result.getData()).isEmpty();
    }

    @Test
    @DisplayName("API 결과에 카카오,네이버 결과와 동일한 정보가 있을때 정상 동작한다")
    public void m3() {
      var kakaoPlaces = genTestData("A곱창", "B곱창", "C곱창", "D곱창");
      var naverPlaces = genTestData("A곱창", "E곱창", "D곱창", "C곱창");

      var googlePlaces = genTestData("C곱창");

      var result = new PlaceCollector(null).intersect(kakaoPlaces, List.of(naverPlaces, googlePlaces));

      var expected = genTestData("C곱창");
      assertThat(result.getData()).containsAll(expected.getData());
    }
  }


  private Places genTestData(String... names) {
    return Places.of(Arrays.stream(names)
        .map(name -> Place.builder().placeName(name).build())
        .collect(toUnmodifiableList()));
  }

}