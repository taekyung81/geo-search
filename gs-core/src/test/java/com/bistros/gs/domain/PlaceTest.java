package com.bistros.gs.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class PlaceTest {

  @Test
  @DisplayName("장소명만 동일하면 같은 개체이다(equals)")
  public void testPlaceEqual() {
    var p1 = Place.builder().placeName("곱창").phone("123-456").address("제주").build();
    var p2 = Place.builder().placeName("곱창").phone("1").address("서울").build();
    Assertions.assertThat(p1).isEqualTo(p2);
  }

  @Test
  @DisplayName("장소명이 다르면, 다른 개체이다")
  public void testPlaceEquals2() {
    var p1 = Place.builder().placeName("A곱창").phone("1").address("제주").build();
    var p2 = Place.builder().placeName("B곱창").phone("1").address("제주").build();
    Assertions.assertThat(p1).isNotEqualTo(p2);
  }

}