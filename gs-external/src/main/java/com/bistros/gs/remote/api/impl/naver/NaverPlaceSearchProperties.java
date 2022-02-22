package com.bistros.gs.remote.api.impl.naver;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@ConstructorBinding //boot2.2+
@Validated
@ConfigurationProperties(prefix = "key.naver")
public class NaverPlaceSearchProperties {

  @NotNull
  private final String clientId;
  @NotNull
  private final String clientSecret;

  public NaverPlaceSearchProperties(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }
}
