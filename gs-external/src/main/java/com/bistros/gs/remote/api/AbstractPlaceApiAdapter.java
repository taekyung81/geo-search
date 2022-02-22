package com.bistros.gs.remote.api;

import com.bistros.gs.domain.LocalResource;

public abstract class AbstractPlaceApiAdapter<E extends LocalResource> {
  private E type;

  public AbstractPlaceApiAdapter(E type) {
    this.type = type;
  }

  public String getApiId() {
    return type.getId();
  }
}
