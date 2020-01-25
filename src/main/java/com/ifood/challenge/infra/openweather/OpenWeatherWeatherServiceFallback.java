package com.ifood.challenge.infra.openweather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class OpenWeatherWeatherServiceFallback implements OpenWeatherWeatherApi {
  private final Logger log = LoggerFactory
      .getLogger(OpenWeatherWeatherServiceFallback.class);
  private Throwable cause;

  public OpenWeatherWeatherServiceFallback(Throwable cause) {
    this.cause = cause;
  }

  @Override
  public OpenWeatherDto findByCityName(String cityName, String apiKey) {
    if (cause != null) {
      log.error(cause.getMessage());
    }
    return null;
  }

  @Override
  public OpenWeatherDto findByCityCoordinates(Double lat, Double lon, String apiKey) {
    if (cause != null) {
      log.error(cause.getMessage());
    }
    return null;
  }
}
