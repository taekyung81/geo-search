//package com.bistros.gs.configuration;
//
//import com.bistros.gs.ranking.repository.RankingRepository;
//import com.bistros.gs.ranking.repository.impl.MemoryRankingRepository;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RankingRepositoryConfiguration {
//
//  @Bean
//  @ConditionalOnMissingBean(RankingRepository.class)
//  public RankingRepository defaultRankingRepository() {
//    return new MemoryRankingRepository();
//  }
//
//}
