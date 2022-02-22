package com.bistros.gs.application.search.service;

import com.bistros.gs.application.ranking.port.PutQueryCountPort;
import static com.bistros.gs.application.search.service.GetPlaceService.GetPlaceRequest;
import static com.bistros.gs.application.search.service.GetPlaceService.GetPlaceResponse;
import com.bistros.gs.application.shared.RequestUseCase;
import com.bistros.gs.application.shared.ResponseUseCase;
import com.bistros.gs.application.shared.UseCase;
import com.bistros.gs.domain.Places;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetPlaceService implements UseCase<GetPlaceRequest, GetPlaceResponse> {

  private final PutQueryCountPort putQueryCountPort;
  private final PlaceCollector placeCollector;
  private final PlaceApiCaller placeApiCaller;

  @Override
  public GetPlaceResponse apply(GetPlaceRequest request) {

    var query = request.getKeyword();
    var size = request.getSize();

    putQueryCountPort.write(query);

    var streams = placeApiCaller.call(query, size);
    var kakaoResult = streams.get(0);
    var othersResult = streams.stream().skip(1).collect(Collectors.toList());


    var result = placeCollector.apply(kakaoResult, othersResult);
    return GetPlaceResponse.of(result, kakaoResult, othersResult);
  }


  @Getter
  @AllArgsConstructor(staticName = "of")
  public static class GetPlaceRequest implements RequestUseCase {
    private final String keyword;
    private final int size;
  }

  @Getter
  @AllArgsConstructor(staticName = "of")
  public static class GetPlaceResponse implements ResponseUseCase {
    private final Places places;
    private final Places rawKakao;
    private final List<Places> others;
  }
}
