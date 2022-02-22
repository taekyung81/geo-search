package com.bistros.gs.api.dto;

import com.bistros.gs.domain.Place;
import com.bistros.gs.domain.Places;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(staticName = "of")
public class PlacesDto {
  private final List<PlaceDto> data;

  public static PlacesDto from(Places places) {
    return PlacesDto.of(places.getStream().map(PlaceDto::from)
        .collect(Collectors.toUnmodifiableList()));
  }

  public static PlacesDto fromWithDetail(Places places) {
    return PlacesDto.of(places.getStream().map(PlaceDto::fromWithDetail)
        .collect(Collectors.toUnmodifiableList()));
  }

  @Getter
  @Builder
  public static class PlaceDto {
    private final String name;
    private final String origin;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String address;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String phone;
    private final int order;

    public static PlaceDto from(Place place) {
      return PlaceDto.builder()
          .name(place.getPlaceName())
          .order(place.getOriginOrder())
          .origin(place.getOrigin())
          .build();
    }

    public static PlaceDto fromWithDetail(Place place) {
      return PlaceDto.builder()
          .name(place.getPlaceName())
          .order(place.getOriginOrder())
          .origin(place.getOrigin())
          .address(place.getAddress())
          .phone(place.getPhone())
          .build();
    }
  }
}
