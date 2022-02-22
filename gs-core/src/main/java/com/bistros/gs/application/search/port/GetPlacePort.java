package com.bistros.gs.application.search.port;

import com.bistros.gs.domain.LocalResource;
import com.bistros.gs.domain.Places;

/*
   1. 비지니스로직을 처리하는 gs-core 모듈 (사용 측)
   2. 실제로 구현을 하는 gs-external 모듈 (구현 측) 에 서 사용하는 인터페이스
 */
@FunctionalInterface
public interface GetPlacePort<E extends LocalResource> {

  Places getPlaces(String query, int count);
}
