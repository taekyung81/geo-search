package com.bistros.gs.api;

import static com.bistros.gs.application.search.service.GetPlaceService.GetPlaceResponse;
import com.bistros.gs.domain.KakaoResource;
import com.bistros.gs.domain.Place;
import com.bistros.gs.domain.Places;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

class SearchPlaceControllerTest extends AbstractRestDocControllerTest {

  @Test
  @DisplayName("정상적인 결과 응답")
  void docSearch() throws Exception {
    when(getPlaceService.apply(any())).thenReturn(GetPlaceResponse.of(testData(), null, null));

    this.mockMvc.perform(
        RestDocumentationRequestBuilders.get("/v1/search")
            .queryParam("query", "곱창")
            .queryParam("size", "5")
    ).andExpect(
        status().isOk()
    ).andDo(
        document("search/simple/success",
            getDocumentRequest(),
            getDocumentResponse(),
            requestParameters(
                parameterWithName("query").description("검색 쿼리"),
                parameterWithName("size").description("API 별 데이터 요청 갯수")
            )
        )
    );
  }


  @Test
  @DisplayName("디테일한 결과 포함하여 응답")
  void docSearchDetail() throws Exception {
    when(getPlaceService.apply(any())).thenReturn(GetPlaceResponse.of(testData(), testDataOfKakao(), testDataOfNaver()));

    this.mockMvc.perform(
        RestDocumentationRequestBuilders.get("/v1/search")
            .queryParam("query", "곱창")
            .queryParam("size", "5")
            .queryParam("detail", "true")
    ).andExpect(
        status().isOk()
    ).andDo(
        document("search/detail/success",
            getDocumentRequest(),
            getDocumentResponse(),
            requestParameters(
                parameterWithName("query").description("검색 쿼리"),
                parameterWithName("size").description("API 별 데이터 요청 갯수").optional(),
                parameterWithName("detail").description("상세 결과 조회").optional()
            )
        )
    );
  }


  private Places testData() {
    var resource = new KakaoResource();
    return Places.of(List.of(
        Place.builder().placeName("A곱창").origin("intersect").originOrder(1).build(),
        Place.builder().placeName("B곱창").origin(resource.getId()).originOrder(1).build(),
        Place.builder().placeName("C곱창").origin(resource.getId()).originOrder(1).build()
    ));
  }

  private Places testDataOfKakao() {
    var resource = new KakaoResource();
    return Places.of(List.of(
        Place.builder().placeName("B곱창").origin(resource.getId()).originOrder(1).build(),
        Place.builder().placeName("A곱창").origin(resource.getId()).originOrder(2).build()
    ));
  }

  private List<Places> testDataOfNaver() {
    var resource = new KakaoResource();
    return List.of(Places.of(List.of(
        Place.builder().placeName("A곱창").origin(resource.getId()).originOrder(1).build(),
        Place.builder().placeName("C곱창").origin(resource.getId()).originOrder(2).build()))
    );
  }
}