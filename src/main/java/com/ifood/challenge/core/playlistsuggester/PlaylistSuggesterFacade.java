package com.ifood.challenge.core.playlistsuggester;

import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.core.playlistsuggester.business.WeatherMusicGenreExpression;
import com.ifood.challenge.core.playlistsuggester.business.WeatherMusicGenreRuleEngine;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityCoordinatesNotFound;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityNameNotFound;
import java.util.List;

/**
 * The class Playlist suggester facade.
 * Created at 14 de jan de 2020 22:54:34
 */
public class PlaylistSuggesterFacade {
  /**
   * The City weather proxy.
   */
  private ICityWeatherProxy cityWeatherProxy;

  /**
   * The Music proxy.
   */
  private IMusicProxy musicProxy;

  /**
   * The Weather music genre rule engine.
   */
  private IWeatherMusicGenreRuleEngine weatherMusicGenreRuleEngine;

  /**
   * Instantiates a new Playlist suggester facade.
   *
   * @param cityWeatherProxy the city weather proxy
   * @param musicProxy       the music proxy Created on 14 de jan de 2020 22:54:34
   */
  public PlaylistSuggesterFacade(ICityWeatherProxy cityWeatherProxy,
      IMusicProxy musicProxy) {
    this.cityWeatherProxy = cityWeatherProxy;
    this.musicProxy = musicProxy;
    this.weatherMusicGenreRuleEngine = new WeatherMusicGenreRuleEngine();
  }

  /**
   * Gets playlist weather suggestion by city name.
   * Created at 14 de jan de 2020 22:54:34
   *
   * @param cityName the city name
   * @return the playlist weather suggestion by city name
   * @throws PlaylistSuggestionByCityNameNotFound the playlist suggestion by city name not found
   */
  public List<MusicDto> getPlaylistWeatherSuggestionByCityName(
      String cityName) throws PlaylistSuggestionByCityNameNotFound {
    CityWeatherDto cityWeatherDto = cityWeatherProxy.findByCityName(cityName);

    String musicGenre =
        weatherMusicGenreRuleEngine.process(
            new WeatherMusicGenreExpression(cityWeatherDto.getCurrentTemperature())).getValue();

    List<MusicDto> musicDtoList = musicProxy.findPlaylistByGenre(musicGenre);

    return musicDtoList;
  }

  /**
   * Gets playlist weather suggestion by city coordinates.
   * Created at 14 de jan de 2020 23:41:41
   *
   * @param latitude  the latitude
   * @param longitude the longitude
   * @return the playlist weather suggestion by city coordinates
   * @throws PlaylistSuggestionByCityCoordinatesNotFound the playlist suggestion by city
   *                                                     coordinates not found
   */
  public List<MusicDto> getPlaylistWeatherSuggestionByCityCoordinates(Double latitude,
      Double longitude) throws PlaylistSuggestionByCityCoordinatesNotFound {
    CityWeatherDto cityWeatherDto = cityWeatherProxy.findByCityCoordinates(latitude, longitude);

    String musicGenre =
        weatherMusicGenreRuleEngine.process(
            new WeatherMusicGenreExpression(cityWeatherDto.getCurrentTemperature())).getValue();

    List<MusicDto> musicDtoList = musicProxy.findPlaylistByGenre(musicGenre);

    return musicDtoList;
  }
}
