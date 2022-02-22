package com.bistros.gs.application.search.filter;

import com.bistros.gs.domain.Place;
import com.bistros.gs.domain.Places;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultPlaceNameRefiner implements PlaceNameRefiner {

  private final PlaceNameFilter nameRefiners;

  public DefaultPlaceNameRefiner(List<PlaceNameFilter> refiners) {
    nameRefiners = refiners.stream()
        .reduce((v, r) -> (placeName) -> v.andThen(r).apply(placeName))
        .orElse(name -> name);
  }

  /*
    Places의 모든 장소들 대상으로 장소명을 정제합니다.
   */
  @Override
  public Places refine(Places responses) {
    var items = responses.getData().stream()
        .map(this::refine)
        .collect(Collectors.toUnmodifiableList());
    return Places.of(items);
  }


  Place refine(Place place) {
    return place.withPlaceName(nameRefiners.apply(place.getPlaceName()));
  }


}
