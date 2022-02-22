package com.bistros.gs.remote.api.impl.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NaverPlaceApiResponse {
  private int total;
  private int start;
  private int display;
  private List<NaverPlace> items;
}
