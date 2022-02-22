package com.bistros.gs.ranking.repository.impl;

import com.bistros.gs.ranking.repository.RankingRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultRankingRepositoryConfigruation {

  @ConditionalOnMissingBean(RankingRepository.class)
  public RankingRepository rankingRepository() {
    return new DefaultRankingRepository();
  }
}
