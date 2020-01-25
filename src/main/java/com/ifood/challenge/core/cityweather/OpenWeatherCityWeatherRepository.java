package com.ifood.challenge.core.cityweather;

import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.infra.openweather.OpenWeatherDto;
import com.ifood.challenge.infra.openweather.OpenWeatherProxy;
import com.ifood.challenge.shared.EnvProfiles;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("!" + EnvProfiles.DEFAULT)
@Service
class OpenWeatherCityWeatherRepository implements IFindCityWeatherByCityCoordinatesRepository,
    IFindCityWeatherByCityNameRepository {

  @Value("${open.weather.api.key.value}")
  private String apiKey;

  @Autowired
  private OpenWeatherProxy openWeatherWeatherProxy;

  @Override
  public Optional<CityWeatherDto> findByCityName(String cityName) {
    return createOptional(openWeatherWeatherProxy.findByCityName(cityName));
  }

  @Override
  public Optional<CityWeatherDto> findByCityCoordinates(Double lat, Double lon) {
    return createOptional(openWeatherWeatherProxy.findByCityCoordinates(lat, lon));
  }

  @NotNull
  protected Optional<CityWeatherDto> createOptional(OpenWeatherDto openWeatherDto) {
    return Optional
        .ofNullable(OpenWeatherCityWeatherMapper.mapFromOriginToInternalDto(openWeatherDto));
  }
}
