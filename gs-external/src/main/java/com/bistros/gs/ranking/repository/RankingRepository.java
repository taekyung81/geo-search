package com.bistros.gs.ranking.repository;

import com.bistros.gs.domain.Ranking;

import java.util.List;
import java.util.function.IntConsumer;


// comment : cqrs 패턴이라면 1개의 repository 에 put, get을 같이 안한다. 그래서 repository 와 port 를  독립적으로 선언했다.
public interface RankingRepository {

  IntConsumer increse(String query, long count);

  List<Ranking> getRankings(int size);
}
