package com.bistros.gs.application.ranking.port;

import com.bistros.gs.domain.Ranking;

import java.util.List;

public interface GetRankingPort {
  List<Ranking> getRank(int size);
}
