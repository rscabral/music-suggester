package com.ifood.challenge.core.cityweather;

import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import java.util.Optional;

interface IFindCityWeatherByCityNameRepository {
  Optional<CityWeatherDto> findByCityName(String cityName);
}
