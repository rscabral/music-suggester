package com.ifood.challenge.core.cityweather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CityWeatherNotFound extends Exception {
  public static final String MESSAGE = "CityWeather Not Found!";

  public CityWeatherNotFound() {
    super(MESSAGE);
  }
}
