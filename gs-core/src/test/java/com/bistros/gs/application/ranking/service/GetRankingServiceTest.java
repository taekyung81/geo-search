package com.bistros.gs.application.ranking.service;

import com.bistros.gs.application.ranking.port.GetRankingPort;
import com.bistros.gs.domain.Ranking;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GetRankingServiceTest {

  @Test
  @DisplayName("GetRankService 는 요청 갯수만큼 데이터를 가져와야한다")
  void testRankingService() {
    GetRankingPort getRankport = size -> IntStream
        .range(0, size)
        .mapToObj(c -> Ranking.builder().rank(c + 1).searchCount(10).name("장소" + c).build())
        .collect(Collectors.toList());
    var rankService = new GetRankingService(getRankport);
    var requestSize = 3;
    var actual = rankService.apply(new GetRankingService.GetRankingRequest(requestSize));
    assertAll(
        () -> assertThat(actual.getData().size()).isEqualTo(requestSize),
        () -> assertThat(actual.getData().get(0).getRank()).isEqualTo(1)
    );
  }

}