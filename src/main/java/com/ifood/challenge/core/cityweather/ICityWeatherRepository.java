package com.ifood.challenge.core.cityweather;

import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import java.util.Optional;

interface ICityWeatherRepository {
  Optional<CityWeatherDto> findByCityName(String cityName);

  Optional<CityWeatherDto> findByCityCoordinates(Double lat, Double lon);
}
