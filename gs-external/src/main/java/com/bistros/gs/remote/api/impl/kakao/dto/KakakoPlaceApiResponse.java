package com.bistros.gs.remote.api.impl.kakao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakakoPlaceApiResponse {

  private List<KakaoPlace> documents;
  private Meta meta;

  static class Meta {
    boolean isEnd;
    int pageableCount;
    int totalCount;
  }
}
