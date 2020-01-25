package com.ifood.challenge.infra.openweather;

import com.ifood.challenge.shared.ultils.SimpleAsyncExecutionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherProxy {
  private final Logger log = LoggerFactory.getLogger(OpenWeatherProxy.class);

  @Value("${open.weather.api.key.value}")
  private String apiKey;

  @Autowired
  private OpenWeatherWeatherApi openWeatherWeatherApi;

  public OpenWeatherDto findByCityName(String cityName) {
    return SimpleAsyncExecutionHelper
        .executeAsync(() -> openWeatherWeatherApi.findByCityName(cityName, apiKey), log);
  }

  public OpenWeatherDto findByCityCoordinates(Double lat, Double lon) {
    return SimpleAsyncExecutionHelper
        .executeAsync(() -> openWeatherWeatherApi.findByCityCoordinates(lat, lon, apiKey), log);
  }
}
