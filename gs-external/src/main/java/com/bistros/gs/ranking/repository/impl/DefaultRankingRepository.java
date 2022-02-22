package com.bistros.gs.ranking.repository.impl;

import com.bistros.gs.domain.Ranking;
import com.bistros.gs.ranking.repository.RankingRepository;
import static java.util.stream.Collectors.toUnmodifiableList;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.IntConsumer;

/*
  랭킹 정보를 저장하는 메모리 기반 저장소이다.
  HashMap 을 사용했지만 LongAdder 등을 이용하여 Thread 하게 작성되었다 (테스트코드를 확인하라)

  처음에는 ConcurrentHashMap 로 구현하였고, 현재 방식이 훨씬 빠르지 않을까 했으나 크게 차이 안났음... (로컬에서 대략 적인 테스트만 진행)
 */
@Component
public class DefaultRankingRepository implements RankingRepository {

  private final HashMap<String, LongAdder> map;

  public DefaultRankingRepository() {
    map = new HashMap<>();
  }

  @Override
  public IntConsumer increse(String query, long count) {
    map.computeIfAbsent(query, k -> new LongAdder()).add(count);
    return null;
  }

  public List<Ranking> getRankings(int size) {
    var entries = map.entrySet().stream()
        .sorted((o1, o2) -> Long.compare(o2.getValue().longValue(), o1.getValue().longValue()))
        .collect(toUnmodifiableList());

    AtomicInteger index = new AtomicInteger(0);
    return entries.stream().map(c -> Ranking.builder()
        .rank(index.incrementAndGet())
        .name(c.getKey())
        .searchCount(c.getValue().longValue())
        .build()
    ).collect(toUnmodifiableList());
  }

}
