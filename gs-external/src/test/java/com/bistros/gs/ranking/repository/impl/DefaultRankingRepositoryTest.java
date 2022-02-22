package com.bistros.gs.ranking.repository.impl;

import com.bistros.gs.ranking.repository.RankingRepository;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

class DefaultRankingRepositoryTest {

  @Test
  @RepeatedTest(3)
  public void threadTest1() throws InterruptedException {
    var repository = new DefaultRankingRepository();
    var threadCount = 10;
    var size = 1_000_000;

    var countDownLatch = new CountDownLatch(threadCount);
    IntStream.range(0, threadCount)
        .forEach(c -> {
          new Runner(repository, countDownLatch, size).run();
        });

    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      throw e;
    }

    var expected = threadCount * size;
    var resultRanking = repository.getRankings(1).get(0);

    assertThat(resultRanking.getSearchCount()).isEqualTo(expected);
  }

  static class Runner implements Runnable {
    private final RankingRepository repository;
    private final CountDownLatch countDownLatch;
    private int size;

    Runner(RankingRepository repository, CountDownLatch countDownLatch, int size) {
      this.repository = repository;
      this.countDownLatch = countDownLatch;
      this.size = size;
    }

    @Override
    public void run() {
      for (int i = 1; i <= size; i++) {
        repository.increse("A", 1);
      }
      countDownLatch.countDown();
    }
  }

}