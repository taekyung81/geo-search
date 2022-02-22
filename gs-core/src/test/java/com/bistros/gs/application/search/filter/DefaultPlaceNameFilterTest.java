package com.bistros.gs.application.search.filter;

import com.bistros.gs.domain.Place;
import com.bistros.gs.domain.Places;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

class DefaultPlaceNameFilterTest {

  @Test
  @DisplayName("Refiner 가 없을 경우 동작 한다")
  public void m1() {
    var definer = new DefaultPlaceNameRefiner(Collections.emptyList());
    var placeName = "우리 동네 맛집";
    var place = Place.builder().placeName(placeName).build();

    var refineResult = definer.refine(place);

    assertThat(refineResult.getPlaceName()).isEqualTo(placeName);
  }


  @Test
  @DisplayName("2개의 Refiner 가 존재할 경우 동작 한다")
  public void m2() {
    var definer = new DefaultPlaceNameRefiner(List.of(new WhiteSpaceFilter(), new TagNameFilter()));
    var place = Place.builder().placeName("우리 동네<b>맛집</b>").build();

    var refineResult = definer.refine(place);

    var expectedName = "우리동네맛집";
    assertThat(refineResult.getPlaceName()).isEqualTo(expectedName);
  }

  @Test
  @DisplayName("2개의 Refiner가 정의되어 있고, 여러 장소명을 대상으로 동작 한다")
  public void m3() {
    var definer = new DefaultPlaceNameRefiner(List.of(new WhiteSpaceFilter(), new TagNameFilter()));
    var places = Places.of(List.of(
        Place.builder().placeName("우리 동네<b>맛집</b>").build(),
        Place.builder().placeName("<b>비밀</b> 집").build()));

    var refineResult = definer.refine(places);

    var expected = Places.of(List.of(
        Place.builder().placeName("우리동네맛집").build(),
        Place.builder().placeName("비밀집").build()));

    assertThat(refineResult.getData()).containsAll(expected.getData());

  }

}