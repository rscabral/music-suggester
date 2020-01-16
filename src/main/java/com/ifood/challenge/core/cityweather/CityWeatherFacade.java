package com.ifood.challenge.core.cityweather;

import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.core.cityweather.exception.CityWeatherNotFound;

public class CityWeatherFacade {

  private ICityWeatherRepository repository;

  public CityWeatherFacade(ICityWeatherRepository repository) {
    this.repository = repository;
  }

  public CityWeatherDto findByCityName(String cityName) throws CityWeatherNotFound {
    return repository.findByCityName(cityName).orElseThrow(CityWeatherNotFound::new);
  }

  public CityWeatherDto findByCityCoordinates(Double latitude,
      Double longitude) throws CityWeatherNotFound {
    return repository.findByCityCoordinates(latitude, longitude).orElseThrow(
        CityWeatherNotFound::new);
  }

}
