package com.bistros.gs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor(staticName = "of")
public class Places {
  List<Place> data;

  public Stream<Place> getStream() {
    return data.stream();
  }

  public static final Places EMPTY = new Places(Collections.emptyList());
}
