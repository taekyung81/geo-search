package com.bistros.gs.application.search.service;

import com.bistros.gs.application.search.filter.PlaceNameRefiner;
import com.bistros.gs.domain.Places;
import static java.util.List.of;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toUnmodifiableList;
import static java.util.stream.Stream.concat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
class PlaceCollector {

  private final PlaceNameRefiner nameDefiner;

  public Places apply(Places kakao, List<Places> naver) {
    var kakaos = nameDefiner.refine(kakao);
    var others = naver.stream()
        .map(nameDefiner::refine).collect(toUnmodifiableList());
    return combine(kakaos, others);
  }

  /**
   *
   *
   * @return 교집합과, 차집합을 다 더해서 2차원의 장소 리스트(Places)로 반환한다
   */
  protected Places combine(Places kakao, List<Places> others) {
    var intersect = intersect(kakao, others);

    var kakaoOnlyData = complement(kakao, intersect);
    var othersData = others.stream().map(dataSet -> complement(dataSet, intersect));

    var r = concat(of(intersect, kakaoOnlyData).stream(), othersData)
        .flatMap(Places::getStream)
        .collect(toUnmodifiableList());
    return Places.of(r);
  }

  /**
   * @return 각 API 응답에서 교집합을 제거한 결과
   */
  protected Places complement(Places main, Places intersect) {
    var items = main.getStream()
        .filter(not(intersect.getData()::contains))
        .collect(toUnmodifiableList());
    return Places.of(items);
  }

  /**
   * @return 카카오를 포함한 모든 API 응답에서 포함하는 장소 정보를 리턴한다 (교집합)
   * 이 응답값은 최종 결과값에 최우선으로 포함된다
   */
  protected Places intersect(Places kakao, List<Places> places) {
    var items = places.stream()
        .map(Places::getData)
        .reduce(kakao.getData(), (l, r) -> l.stream().filter(r::contains)
            .map(place -> place.withOrigin("intersect"))
            .collect(toUnmodifiableList()));
    return Places.of(items);
  }

}
