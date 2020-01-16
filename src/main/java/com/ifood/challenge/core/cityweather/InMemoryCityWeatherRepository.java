package com.ifood.challenge.core.cityweather;

import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.shared.EnvProfiles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(EnvProfiles.DEFAULT)
@Service
class InMemoryCityWeatherRepository implements ICityWeatherRepository {
  List<CityWeatherDto> cityWeatherDtoList;

  public InMemoryCityWeatherRepository() {
    cityWeatherDtoList = new ArrayList<>();
    cityWeatherDtoList.add(
        CityWeatherDto.builder().setCityName("London").setLatitude(123.00).setLongitude(
            -123.00).setCurrentTemperature(25).build());
    cityWeatherDtoList.add(
        CityWeatherDto.builder().setCityName("SaoPaulo").setLatitude(256.00).setLongitude(
            -456.00).setCurrentTemperature(32).build());
    cityWeatherDtoList.add(
        CityWeatherDto.builder().setCityName("Amsterdam").setLatitude(80.00).setLongitude(
            -456.00).setCurrentTemperature(10).build());
  }

  @Override
  public Optional<CityWeatherDto> findByCityName(String cityName) {
    return this.cityWeatherDtoList.stream().filter(
        c -> c.getCityName().equalsIgnoreCase(cityName)).findFirst();
  }

  @Override
  public Optional<CityWeatherDto> findByCityCoordinates(Double lat, Double lon) {
    return this.cityWeatherDtoList.stream().filter(
        c -> c.getLatitude().equals(lat) && c.getLongitude().equals(lon)).findFirst();
  }
}
