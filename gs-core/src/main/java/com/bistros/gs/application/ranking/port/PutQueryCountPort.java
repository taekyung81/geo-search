package com.bistros.gs.application.ranking.port;

import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.cloud.sleuth.annotation.SpanTag;

public interface PutQueryCountPort {
  @NewSpan("query-counter")
  void write(@SpanTag(key = "query") String query);
}
