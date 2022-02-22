package com.bistros.gs.remote.api;

import com.bistros.gs.application.search.port.GetPlacePort;
import com.bistros.gs.domain.OtherResource;
import com.bistros.gs.domain.Places;
import io.vavr.NotImplementedError;

class GoogleOpenApiAdapter extends AbstractPlaceApiAdapter<OtherResource> implements GetPlacePort<OtherResource> {

  public GoogleOpenApiAdapter(OtherResource type) {
    super(new OtherResource("google"));
  }

  @Override
  public Places getPlaces(String query, int count) {
    throw new NotImplementedError();
  }
}