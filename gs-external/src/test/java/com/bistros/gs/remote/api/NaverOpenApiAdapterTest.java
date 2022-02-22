package com.bistros.gs.remote.api;

import com.bistros.gs.remote.api.impl.naver.dto.NaverPlace;
import com.bistros.gs.remote.api.impl.naver.dto.NaverPlaceApiResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class NaverOpenApiAdapterTest {

  @Test
  @DisplayName("네이버 데이터 반환")
  void m1() {
    var PLACE_NAME1 = "장소명1";
    var PLACE_NAME2 = "장소명2";
    var naverAdapter = new NaverOpenApiAdapter(
        (query, display) -> new NaverPlaceApiResponse(display, 1, display,
            List.of(
                new NaverPlace(PLACE_NAME1, "링크", "식당>서양", "", "123-456",
                    "주소", "길주소", "", ""),
                new NaverPlace(PLACE_NAME2, "", "식당>주점", "", "02-555-5525",
                    "주소", "길주소", "", "")
            )));

    var actual = naverAdapter.getPlaces("장소명", 2);

    assertAll(
        () -> assertThat(actual.getData().size()).isEqualTo(2),
        () -> assertThat(actual.getData().get(0).getPlaceName()).isEqualTo(PLACE_NAME1),
        () -> assertThat(actual.getData().get(1).getPlaceName()).isEqualTo(PLACE_NAME2)
    );

  }

}
