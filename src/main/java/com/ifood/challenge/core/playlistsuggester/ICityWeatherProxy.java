package com.ifood.challenge.core.playlistsuggester;

import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityCoordinatesNotFound;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityNameNotFound;

interface ICityWeatherProxy {
  CityWeatherDto findByCityName(String cityName) throws PlaylistSuggestionByCityNameNotFound;

  CityWeatherDto findByCityCoordinates(Double latitude,
      Double longitude) throws PlaylistSuggestionByCityCoordinatesNotFound;
}
