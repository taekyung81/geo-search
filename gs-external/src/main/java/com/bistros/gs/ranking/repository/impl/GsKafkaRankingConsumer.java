package com.bistros.gs.ranking.repository.impl;

import com.bistros.gs.ranking.repository.RankingRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GsKafkaRankingConsumer {

  private RankingRepository rankingRepository;

  public GsKafkaRankingConsumer(RankingRepository rankingRepository) {
    this.rankingRepository = rankingRepository;
  }

  @KafkaListener(containerFactory = "batchContainerFactory", topics = "${spring.kafka.topic}")
  public void listen(List<ConsumerRecord<String, String>> queries) throws InterruptedException {
    var result = queries.stream().map(ConsumerRecord::value)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    result.forEach((key, value) -> rankingRepository.increse(key, value));


    log.info("{} 개를 업데이트 합니다 - {}", queries.size(), result.entrySet());
  }

}
