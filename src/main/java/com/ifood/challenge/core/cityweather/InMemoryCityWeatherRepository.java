package com.ifood.challenge.core.cityweather;

import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class InMemoryCityWeatherRepository implements ICityWeatherRepository {
  List<CityWeatherDto> cityWeatherDtoList;

  public InMemoryCityWeatherRepository() {
    cityWeatherDtoList = new ArrayList<>();
    cityWeatherDtoList.add(
        CityWeatherDto.builder().setCityName("London").setLatitude(123.00).setLongitude(
            -123.00).setCurrentTemperature(25).build());
    cityWeatherDtoList.add(
        CityWeatherDto.builder().setCityName("SaoPaulo").setLatitude(456.00).setLongitude(
            -456.00).setCurrentTemperature(30).build());
    cityWeatherDtoList.add(
        CityWeatherDto.builder().setCityName("SaoPaulo").setLatitude(456.00).setLongitude(
            -456.00).setCurrentTemperature(30).build());
  }

  @Override
  public Optional<CityWeatherDto> findByCityName(String cityName) {
    return this.cityWeatherDtoList.stream().findFirst().filter(
        c -> c.getCityName().equals(cityName));
  }

  @Override
  public Optional<CityWeatherDto> findByCityCoordinates(Double lat, Double lon) {
    return this.cityWeatherDtoList.stream().findFirst().filter(
        c -> c.getLatitude().equals(lat) && c.getLongitude().equals(lon));
  }
}
