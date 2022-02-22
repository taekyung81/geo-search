package com.bistros.gs.api;

import com.bistros.gs.api.dto.RankingDto;
import com.bistros.gs.application.ranking.service.GetRankingService;
import static com.bistros.gs.application.ranking.service.GetRankingService.GetRankingRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/rank")
public class RankingController {

  private final GetRankingService rankingUseCase;

  @GetMapping
  public List<RankingDto> query(@RequestParam(defaultValue = "10") @Positive @Max(10) Integer size) {
    var request = new GetRankingRequest(size);

    var response = rankingUseCase.apply(request);

    var result = response.getData().stream()
        .map(RankingDto::from)
        .collect(Collectors.toUnmodifiableList());

    return result;
  }

}
