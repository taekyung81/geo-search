package com.bistros.gs.api;

import com.bistros.gs.application.ranking.service.GetRankingService.GetRankingResponse;
import com.bistros.gs.domain.Ranking;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import java.util.List;

class RankingControllerTest extends AbstractRestDocControllerTest {

  @Test
  @DisplayName("MemoryRankingRepository랭킹 정보가 5개 존재한다")
  void getRanking() throws Exception {
    when(getRankingService.apply(any())).thenReturn(new GetRankingResponse(defaultData()));

    this.mockMvc.perform(
        get("/v1/rank")
            .queryParam("size", "5")
    ).andExpect(
        status().isOk()
    ).andDo(
        document("ranking/success",
            getDocumentRequest(),
            getDocumentResponse(),
            requestParameters(
                parameterWithName("size").description("순위 갯수")
            ),
            responseFields(
                fieldWithPath("[].rank").type(NUMBER).description("순위"),
                fieldWithPath("[].query").type(STRING).description("쿼리"),
                fieldWithPath("[].queryCount").type(NUMBER).description("호출 횟수")
            )
        )
    );
  }

  @Test
  @DisplayName("랭킹 정보가 존재하지 않는다")
  void docNotExistRankingInfo() throws Exception {
    when(getRankingService.apply(any())).thenReturn(new GetRankingResponse(Collections.emptyList()));
    this.mockMvc.perform(
        get("/v1/rank")
            .queryParam("size", "5")
    ).andExpect(
        status().isOk()
    ).andDo(
        document("ranking/not-exsit",
            getDocumentRequest(),
            getDocumentResponse(),
            requestParameters(
                parameterWithName("size").description("순위 갯수")
            )
        )
    );
  }

  private List<Ranking> defaultData() {
    return List.of(
        Ranking.builder().name("A찜닭").searchCount(200).rank(1).build(),
        Ranking.builder().name("은행").searchCount(90).rank(2).build(),
        Ranking.builder().name("오마뎅").searchCount(20).rank(3).build(),
        Ranking.builder().name("학교").searchCount(2).rank(4).build(),
        Ranking.builder().name("D찜닭").searchCount(1).rank(5).build()
    );

  }
}