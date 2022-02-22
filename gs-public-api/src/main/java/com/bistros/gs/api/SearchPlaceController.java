package com.bistros.gs.api;

import com.bistros.gs.api.dto.SearchGeoPlaceDto;
import com.bistros.gs.application.search.service.GetPlaceService;
import static com.bistros.gs.application.search.service.GetPlaceService.GetPlaceRequest;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/search")
@Validated
public class SearchPlaceController {

  private final GetPlaceService getPlaceService;

  @GetMapping()
  public SearchGeoPlaceDto get(@RequestParam String query,
                               @Min(1) @Max(5) @RequestParam(defaultValue = "5") int size,
                               @RequestParam(defaultValue = "false") boolean detail) {
    var request = GetPlaceRequest.of(query, size);
    var data = getPlaceService.apply(request);

    if (detail) {
      return SearchGeoPlaceDto.SearchGeoPlaceDebugDto.from(data.getPlaces(), data.getRawKakao(), data.getOthers());
    }
    return SearchGeoPlaceDto.from(data.getPlaces());

  }
}
