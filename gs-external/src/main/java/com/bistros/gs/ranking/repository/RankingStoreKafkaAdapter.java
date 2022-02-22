package com.bistros.gs.ranking.repository;

import com.bistros.gs.application.ranking.port.PutQueryCountPort;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RankingStoreKafkaAdapter implements PutQueryCountPort {
  private final KafkaTemplate<String, String> producer;

  @Override
  public void write(String query) {
    producer.sendDefault(query);
  }
}
