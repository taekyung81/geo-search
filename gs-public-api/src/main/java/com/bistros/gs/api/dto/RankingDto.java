package com.bistros.gs.api.dto;

import com.bistros.gs.domain.Ranking;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RankingDto {
  private final int rank;
  private final String query;
  private final long queryCount;

  public static RankingDto from(Ranking ranking) {
    return RankingDto.builder()
        .rank(ranking.getRank())
        .queryCount(ranking.getSearchCount())
        .query(ranking.getName())
        .build();
  }
}
