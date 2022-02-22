package com.bistros.gs.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Ranking {
  private final int rank;
  private final String name;
  private final long searchCount;
}
