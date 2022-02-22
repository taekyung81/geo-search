package com.bistros.gs.application.search.filter;

import org.springframework.stereotype.Component;

/*
  String 에서 공백을 제거한다
 */
@Component
public class WhiteSpaceFilter implements PlaceNameFilter {
  @Override
  public String apply(String s) {
    return s.replaceAll("\\s", "");
  }
}
