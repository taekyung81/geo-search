package com.bistros.gs.application.search.filter;

import com.bistros.gs.domain.Place;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = DefaultPlaceNameRefinerSpringTest.InnerTestConfig.class)
class DefaultPlaceNameRefinerSpringTest {

  @Autowired
  DefaultPlaceNameRefiner refiner;

  @Test
  @DisplayName("스프링 컨텍스트에 Filter가 존재하지 않아도 Refiner는 정상 동작한다")
  public void m() {
    var acutal = Place.builder().placeName("테 스 트").build();
    var expected = "테 스 트";
    assertThat(refiner.refine(acutal).getPlaceName()).isEqualTo(expected);
  }

  @Configuration
  @Import(DefaultPlaceNameRefiner.class)
  static class InnerTestConfig {
  }
}