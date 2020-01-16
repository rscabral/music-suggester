package com.ifood.challenge.infra.openweather;

import com.ifood.challenge.shared.EnvProfiles;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Profile("!" + EnvProfiles.DEFAULT)
@FeignClient(value = "${open.weather.api.url}", fallbackFactory =
    OpenWeatherServiceFallbackFactory.class)
public interface OpenWeatherWeatherProxy {

  @GetMapping(params = {"lat", "lon", "APPID:${open.weather.api.key.value}"})
  OpenWeatherDto findByCityCoordinates(@RequestParam Double lat,
      @RequestParam Double lon, @RequestParam("APPID") String apiKey);

  @GetMapping(params = {"q", "APPID:${open.weather.api.key.value}"})
  OpenWeatherDto findByCityName(
      @RequestParam("q") String cityName,
      @RequestParam("APPID") String apiKey);


}
