package com.bistros.gs.application.search.service;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class PlaceApiCallerThreadPool {
  @Bean
  ExecutorService apiExecutor(BeanFactory beanFactory) {
    var executorService = Executors.newFixedThreadPool(50);
    return new TraceableExecutorService(beanFactory, executorService, "remote-place-api");
  }
}
