//package com.bistros.gs.configuration;
//
//import com.fasterxml.jackson.databind.PropertyNamingStrategies;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.context.annotation.Configuration;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.PropertyNamingStrategy;
//import feign.codec.Decoder;
//
//import org.springframework.beans.factory.ObjectFactory;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.cloud.openfeign.support.PageJacksonModule;
//import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
//import org.springframework.cloud.openfeign.support.SortJacksonModule;
//import org.springframework.cloud.openfeign.support.SpringDecoder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//
//
//@Configuration
//public class GeoWebConfiguration {
//  @Bean
//  public Decoder fetaFeignDecoder() {
//    HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(customObjectMapper());
//    ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
//    return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
//  }
//
//  public ObjectMapper customObjectMapper() {
//    ObjectMapper mapper = new ObjectMapper();
//
//    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
//    return mapper;
//  }
//}
