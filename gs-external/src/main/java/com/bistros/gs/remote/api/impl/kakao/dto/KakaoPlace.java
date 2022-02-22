package com.bistros.gs.remote.api.impl.kakao.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class KakaoPlace {
  String place_name;
  String place_url;
  String address_name;
  String phone;
  String locX;
  String locY;
}
