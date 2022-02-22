package com.bistros.gs.remote.api.impl.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NaverPlace {
  private String title;
  private String link;
  private String category;
  private String description;
  private String telephone;
  private String address;
  private String roadAddress;
  private String mapx;
  private String maxy;
}
