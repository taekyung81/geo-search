package com.bistros.gs.ranking.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ContainerProperties;

@Slf4j
@Configuration
public class GsKafkaConfiguration {

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> batchContainerFactory(KafkaProperties properties) {
    var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
    factory.setConcurrency(1);
    factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(properties.buildConsumerProperties()));
    factory.setBatchListener(true);
    factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.BATCH);
    factory.getContainerProperties().setIdleBetweenPolls(1000);
    return factory;
  }

  /*
    KafkaFactoryBeanPostProcessor 에 의해 TraceProducerPostProcessor 가 동작하기 위해 Bean 으로 등록
    if manually 하게 code 로 작성한다면 수동으로 TraceProduer를 처리해야함
   */
  @Bean
  public ProducerFactory<String, String> kafkaProducerFactory(KafkaProperties properties) {
    return new DefaultKafkaProducerFactory<>(properties.buildProducerProperties());
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate(KafkaProperties properties,
                                                     ProducerFactory<String, String> kafkaProducerFactory) {
    var template = new KafkaTemplate<>(kafkaProducerFactory);
    template.setDefaultTopic(properties.getTemplate().getDefaultTopic());
    return template;
  }
}
