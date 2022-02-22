package com.bistros.gs.api.dto;

import com.bistros.gs.domain.Places;
import static java.util.stream.Collectors.toUnmodifiableList;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@SuperBuilder
public class SearchGeoPlaceDto {
  protected PlacesDto result;

  public static SearchGeoPlaceDto from(Places result) {
    return builder()
        .result(PlacesDto.from(result))
        .build();
  }

  @Getter
  @SuperBuilder
  public static class SearchGeoPlaceDebugDto extends SearchGeoPlaceDto {
    private final PlacesDto kakao;
    private final List<PlacesDto> others;

    public static SearchGeoPlaceDebugDto from(Places result, Places kakao, List<Places> others) {
      return builder()
          .result(PlacesDto.from(result))
          .kakao(PlacesDto.fromWithDetail(kakao))
          .others(others.stream().map(PlacesDto::fromWithDetail).collect(toUnmodifiableList()))
          .build();
    }
  }
}
