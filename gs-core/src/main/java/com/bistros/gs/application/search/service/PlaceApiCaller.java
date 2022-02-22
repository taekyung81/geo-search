package com.bistros.gs.application.search.service;

import com.bistros.gs.application.search.port.GetPlacePort;
import com.bistros.gs.domain.KakaoResource;
import com.bistros.gs.domain.OtherResource;
import com.bistros.gs.domain.Places;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.of;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PlaceApiCaller {

  private final ExecutorService apiExecutor;
  private final GetPlacePort<KakaoResource> kakaoApiPort;
  private final List<GetPlacePort<OtherResource>> othersApiPorts;

  public List<Places> call(String query, int size) {
    var kakaoFuture = supplyAsync(() -> kakaoApiPort.getPlaces(query, size), apiExecutor);
    var otherFutures = othersApiPorts.stream()
        .map(port -> supplyAsync(() -> port.getPlaces(query, size), apiExecutor));

    var result = concat(of(kakaoFuture), otherFutures)
        .map(CompletableFuture::join)
        .parallel()
        .collect(Collectors.toList());

    return result;
  }
}
