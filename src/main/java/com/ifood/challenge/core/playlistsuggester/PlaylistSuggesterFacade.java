package com.ifood.challenge.core.playlistsuggester;

import com.ifood.challenge.core.cityweather.CityWeatherFacade;
import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.core.cityweather.exception.CityWeatherNotFound;
import com.ifood.challenge.core.music.MusicFacade;
import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.core.music.exception.MusicPlaylistByGenreNotFound;
import com.ifood.challenge.core.playlistsuggester.business.WeatherMusicGenreExpression;
import com.ifood.challenge.core.playlistsuggester.business.WeatherMusicGenreRuleEngine;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityCoordinatesNotFound;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityNameNotFound;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * The class Playlist suggester facade.
 * Created at 14 de jan de 2020 22:54:34
 */
public class PlaylistSuggesterFacade {
  /**
   * The City weather proxy.
   */
  private CityWeatherFacade cityWeatherProxy;

  /**
   * The Music proxy.
   */
  private MusicFacade musicFacade;

  /**
   * The Weather music genre rule engine.
   */
  private IWeatherMusicGenreRuleEngine weatherMusicGenreRuleEngine;

  /**
   * Instantiates a new Playlist suggester facade.
   *
   * @param cityWeatherFacade the city weather proxy
   * @param musicFacade       the music proxy Created on 14 de jan de 2020 22:54:34
   */
  public PlaylistSuggesterFacade(CityWeatherFacade cityWeatherFacade,
      MusicFacade musicFacade) {
    this.cityWeatherProxy = cityWeatherFacade;
    this.musicFacade = musicFacade;
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
    CityWeatherDto cityWeatherDto = null;

    try {
      cityWeatherDto = cityWeatherProxy.findByCityName(cityName);
    } catch (CityWeatherNotFound cityWeatherNotFound) {
      cityWeatherNotFound.printStackTrace();
      throw new PlaylistSuggestionByCityNameNotFound(cityName);
    }

    String musicGenre = weatherMusicGenreRuleEngine.process(
        new WeatherMusicGenreExpression(cityWeatherDto.getCurrentTemperature())).getValue();

    List<MusicDto> musicDtoList = getMusicDtosByGenre(musicGenre);

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
    CityWeatherDto cityWeatherDto = null;

    try {
      cityWeatherDto = cityWeatherProxy.findByCityCoordinates(latitude, longitude);
    } catch (CityWeatherNotFound cityWeatherNotFound) {
      throw new PlaylistSuggestionByCityCoordinatesNotFound(latitude, longitude);
    }

    String musicGenre =
        weatherMusicGenreRuleEngine.process(
            new WeatherMusicGenreExpression(cityWeatherDto.getCurrentTemperature())).getValue();

    List<MusicDto> musicDtoList = getMusicDtosByGenre(musicGenre);

    return musicDtoList;
  }

  @NotNull
  private List<MusicDto> getMusicDtosByGenre(String musicGenre) {
    List<MusicDto> entityModels = null;
    try {
      entityModels = musicFacade.findPlaylistByGenre(musicGenre);
    } catch (MusicPlaylistByGenreNotFound musicPlaylistByGenreNotFound) {
      musicPlaylistByGenreNotFound.printStackTrace();
      return new ArrayList<>();
    }
    return entityModels;
  }
}
