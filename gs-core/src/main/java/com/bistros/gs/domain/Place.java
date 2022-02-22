package com.bistros.gs.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.With;

import java.util.Objects;

@Getter
@Builder
public class Place {
  @With
  String placeName;
  String phone;
  String address;

  @With
  String origin;
  Integer originOrder;

  @Override
  public String toString() {
    return "PlaceResponses{" +
        "placeName='" + placeName + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Place that = (Place) o;
    return Objects.equals(placeName, that.placeName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(placeName);
  }
}
