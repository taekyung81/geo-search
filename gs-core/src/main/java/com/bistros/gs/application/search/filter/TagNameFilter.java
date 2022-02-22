package com.bistros.gs.application.search.filter;


import org.springframework.stereotype.Component;

/*
  String 에서 <?>형태를 제거한다
 */
@Component
public class TagNameFilter implements PlaceNameFilter {
  @Override
  public String apply(String s) {
    return s.replaceAll("<[^>]*>", "");
  }
}
