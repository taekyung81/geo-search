package com.bistros.gs.application.ranking.service;

import com.bistros.gs.application.ranking.port.GetRankingPort;
import static com.bistros.gs.application.ranking.service.GetRankingService.GetRankingRequest;
import static com.bistros.gs.application.ranking.service.GetRankingService.GetRankingResponse;
import com.bistros.gs.application.shared.RequestUseCase;
import com.bistros.gs.application.shared.ResponseUseCase;
import com.bistros.gs.application.shared.UseCase;
import com.bistros.gs.domain.Ranking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GetRankingService implements UseCase<GetRankingRequest, GetRankingResponse> {

  private final GetRankingPort getRankingPort;

  @Override
  public GetRankingResponse apply(GetRankingRequest request) {
    var size = request.getSize();
    var result = getRankingPort.getRank(size);

    return new GetRankingResponse(result);
  }


  @Getter
  @AllArgsConstructor
  public static class GetRankingRequest implements RequestUseCase {
    private final int size;
  }

  @Getter
  @AllArgsConstructor
  public static class GetRankingResponse implements ResponseUseCase {
    private final List<Ranking> data;
  }

}
