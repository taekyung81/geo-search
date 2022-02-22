package com.bistros.gs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@ConfigurationPropertiesScan
public class GeoSearchApplication {
  public static void main(String[] args) {
    var apps = SpringApplication.run(GeoSearchApplication.class, args);
  }
}
