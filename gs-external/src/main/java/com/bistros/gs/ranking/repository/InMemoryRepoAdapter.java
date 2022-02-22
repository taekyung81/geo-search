package com.bistros.gs.ranking.repository;

import com.bistros.gs.application.ranking.port.GetRankingPort;
import com.bistros.gs.domain.Ranking;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
class InMemoryRepoAdapter implements GetRankingPort {

  private final RankingRepository rankingRepository;

  @Override
  public List<Ranking> getRank(int size) {
    return rankingRepository.getRankings(size);
  }
}
