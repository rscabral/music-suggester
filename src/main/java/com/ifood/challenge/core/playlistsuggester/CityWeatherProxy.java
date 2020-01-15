package com.ifood.challenge.core.playlistsuggester;

import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;

class CityWeatherProxy implements ICityWeatherProxy {
  @Override
  public CityWeatherDto findByCityName(String cityName) {
    return null;
  }

  @Override
  public CityWeatherDto findByCityCoordinates(Double latitude, Double longitude) {
    return null;
  }
}
