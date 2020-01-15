package com.ifood.challenge.core.playlistsuggester;

import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityCoordinatesNotFound;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityNameNotFound;
import com.ifood.challenge.shared.EnvProfiles;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;

@Profile(EnvProfiles.DEFAULT)
class InMemoryCityWeatherProxy implements ICityWeatherProxy {

  List<CityWeatherDto> cityWeatherDtoList;

  public InMemoryCityWeatherProxy() {
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
  public CityWeatherDto findByCityName(
      String cityName) throws PlaylistSuggestionByCityNameNotFound {
    return this.cityWeatherDtoList.stream().findFirst().filter(
        c -> c.getCityName().equals(cityName)).orElseThrow(
        () -> new PlaylistSuggestionByCityNameNotFound(cityName));
  }

  @Override
  public CityWeatherDto findByCityCoordinates(Double latitude,
      Double longitude) throws PlaylistSuggestionByCityCoordinatesNotFound {
    return this.cityWeatherDtoList.stream().findFirst().filter(
        c -> c.getLatitude().equals(latitude) && c.getLongitude().equals(longitude)).orElseThrow(
        () -> new PlaylistSuggestionByCityCoordinatesNotFound(latitude, longitude));
  }
}
