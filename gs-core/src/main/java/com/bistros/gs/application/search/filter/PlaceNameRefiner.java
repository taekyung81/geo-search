package com.bistros.gs.application.search.filter;

import com.bistros.gs.domain.Places;

public interface PlaceNameRefiner {
  Places refine(Places places);
}
