package com.ifood.challenge.core.playlistsuggester;

import static org.hamcrest.Matchers.is;


import com.ifood.challenge.core.cityweather.CityWeatherFacade;
import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.core.cityweather.exception.CityWeatherNotFound;
import com.ifood.challenge.core.music.MusicFacade;
import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.core.music.exception.MusicPlaylistByGenreNotFound;
import com.ifood.challenge.core.playlistsuggester.dto.PlaylistSuggesterDto;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityCoordinatesNotFound;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityNameNotFound;
import com.ifood.challenge.shared.EnvProfiles;
import java.util.ArrayList;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(EnvProfiles.DEFAULT)
public class PlaylistSuggesterUnitTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private CityWeatherFacade cityWeatherFacade = Mockito.mock(CityWeatherFacade.class);
  private MusicFacade musicFacade = Mockito.mock(MusicFacade.class);
  private PlaylistSuggesterFacade facade =
      new PlaylistSuggesterConfiguration().playlistSuggesterFacade(cityWeatherFacade, musicFacade
          , new WeatherMusicGenreRuleEngine());
  private PlaylistSuggesterDto initialDtoParameters = createPlaylistSuggesterDto();

  private PlaylistSuggesterDto createPlaylistSuggesterDto() {
    return PlaylistSuggesterDto.builder().setCityName("London").setCityCoordinates(123.00,
        -123.00).build();
  }

  @Test
  public void shouldReturnAPlaylistSuggestionsByCityNameWithPartyMusicsWhenTempGreaterThan30()
      throws PlaylistSuggestionByCityNameNotFound, CityWeatherNotFound,
      MusicPlaylistByGenreNotFound {

    Mockito.when(cityWeatherFacade
        .findByCityName(initialDtoParameters.getCityName()))
           .thenReturn(CityWeatherDto.builder().setCurrentTemperature(31).build());

    Mockito.when(musicFacade
        .findPlaylistByGenre("party"))
           .thenReturn(new ArrayList<>());

    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityName(initialDtoParameters.getCityName());

    Mockito.verify(musicFacade, Mockito.atLeastOnce()).findPlaylistByGenre("party");
  }

  @Test
  public void shouldReturnAPlaylistSuggestionsByCityNameWithPopMusicsWhenTempBetween15And30()
      throws PlaylistSuggestionByCityNameNotFound, CityWeatherNotFound,
      MusicPlaylistByGenreNotFound {

    Mockito.when(cityWeatherFacade
        .findByCityName(initialDtoParameters.getCityName()))
           .thenReturn(CityWeatherDto.builder().setCurrentTemperature(15).build());

    Mockito.when(musicFacade
        .findPlaylistByGenre("pop"))
           .thenReturn(new ArrayList<>());

    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityName(initialDtoParameters.getCityName());

    Mockito.when(cityWeatherFacade
        .findByCityName(initialDtoParameters.getCityName()))
           .thenReturn(CityWeatherDto.builder().setCurrentTemperature(30).build());

    playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityName(initialDtoParameters.getCityName());

    Mockito.verify(musicFacade, Mockito.atLeast(2)).findPlaylistByGenre("pop");
  }

  @Test
  public void shouldReturnAPlaylistSuggestionsByCityNameWithRockMusicsWhenTempBetween10And14()
      throws PlaylistSuggestionByCityNameNotFound, CityWeatherNotFound,
      MusicPlaylistByGenreNotFound {

    Mockito.when(cityWeatherFacade
        .findByCityName(initialDtoParameters.getCityName()))
           .thenReturn(CityWeatherDto.builder().setCurrentTemperature(10).build());

    Mockito.when(musicFacade
        .findPlaylistByGenre("rock"))
           .thenReturn(new ArrayList<>());

    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityName(initialDtoParameters.getCityName());

    Mockito.when(cityWeatherFacade
        .findByCityName(initialDtoParameters.getCityName()))
           .thenReturn(CityWeatherDto.builder().setCurrentTemperature(14).build());

    playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityName(initialDtoParameters.getCityName());

    Mockito.verify(musicFacade, Mockito.atLeast(2)).findPlaylistByGenre("rock");
  }

  @Test
  public void shouldReturnAPlaylistSuggestionsByCityNameWithRockMusicsWhenTempBelow10()
      throws PlaylistSuggestionByCityNameNotFound, CityWeatherNotFound,
      MusicPlaylistByGenreNotFound {

    Mockito.when(cityWeatherFacade
        .findByCityName(initialDtoParameters.getCityName()))
           .thenReturn(CityWeatherDto.builder().setCurrentTemperature(9).build());

    Mockito.when(musicFacade
        .findPlaylistByGenre("classical"))
           .thenReturn(new ArrayList<>());

    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityName(initialDtoParameters.getCityName());

    Mockito.verify(musicFacade, Mockito.atLeastOnce()).findPlaylistByGenre("classical");
  }

  @Test
  public void shouldReturnAPlaylistSuggestionsByCityCoordinatesWithPartyMusicsWhenTempGreaterThan30()
      throws PlaylistSuggestionByCityNameNotFound, CityWeatherNotFound,
      MusicPlaylistByGenreNotFound, PlaylistSuggestionByCityCoordinatesNotFound {

    Mockito.when(cityWeatherFacade
        .findByCityCoordinates(initialDtoParameters.getLatitude(),
            initialDtoParameters.getLongitude()))
           .thenReturn(CityWeatherDto.builder().setCurrentTemperature(31).build());

    Mockito.when(musicFacade
        .findPlaylistByGenre("party"))
           .thenReturn(new ArrayList<>());

    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityCoordinates(initialDtoParameters.getLatitude(),
            initialDtoParameters.getLongitude());

    Mockito.verify(musicFacade, Mockito.atLeastOnce()).findPlaylistByGenre("party");
  }

  @Test
  public void shouldReturnAPlaylistSuggestionsByCityCoordinatesWithPopMusicsWhenTempBetween15And30()
      throws PlaylistSuggestionByCityNameNotFound, CityWeatherNotFound,
      MusicPlaylistByGenreNotFound, PlaylistSuggestionByCityCoordinatesNotFound {

    Mockito.when(cityWeatherFacade
        .findByCityCoordinates(initialDtoParameters.getLatitude(),
            initialDtoParameters.getLongitude()))
           .thenReturn(CityWeatherDto.builder().setCurrentTemperature(15).build());

    Mockito.when(musicFacade
        .findPlaylistByGenre("pop"))
           .thenReturn(new ArrayList<>());

    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityCoordinates(initialDtoParameters.getLatitude(),
            initialDtoParameters.getLongitude());

    Mockito.when(cityWeatherFacade
        .findByCityCoordinates(initialDtoParameters.getLatitude(),
            initialDtoParameters.getLongitude()))
           .thenReturn(CityWeatherDto.builder().setCurrentTemperature(30).build());

    playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityCoordinates(initialDtoParameters.getLatitude(),
            initialDtoParameters.getLongitude());

    Mockito.verify(musicFacade, Mockito.atLeast(2)).findPlaylistByGenre("pop");
  }

  @Test
  public void shouldReturnAPlaylistSuggestionsByCityCoordinatesWithRockMusicsWhenTempBetween10And14()
      throws PlaylistSuggestionByCityNameNotFound, CityWeatherNotFound,
      MusicPlaylistByGenreNotFound, PlaylistSuggestionByCityCoordinatesNotFound {

    Mockito.when(cityWeatherFacade
        .findByCityCoordinates(initialDtoParameters.getLatitude(),
            initialDtoParameters.getLongitude()))
           .thenReturn(CityWeatherDto.builder().setCurrentTemperature(10).build());

    Mockito.when(musicFacade
        .findPlaylistByGenre("rock"))
           .thenReturn(new ArrayList<>());

    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityCoordinates(initialDtoParameters.getLatitude(),
            initialDtoParameters.getLongitude());

    Mockito.when(cityWeatherFacade
        .findByCityName(initialDtoParameters.getCityName()))
           .thenReturn(CityWeatherDto.builder().setCurrentTemperature(14).build());

    playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityCoordinates(initialDtoParameters.getLatitude(),
            initialDtoParameters.getLongitude());

    Mockito.verify(musicFacade, Mockito.atLeast(2)).findPlaylistByGenre("rock");
  }

  @Test
  public void shouldReturnAPlaylistSuggestionsByCityCoordinatesWithRockMusicsWhenTempBelow10()
      throws PlaylistSuggestionByCityNameNotFound, CityWeatherNotFound,
      MusicPlaylistByGenreNotFound, PlaylistSuggestionByCityCoordinatesNotFound {

    Mockito.when(cityWeatherFacade
        .findByCityCoordinates(initialDtoParameters.getLatitude(),
            initialDtoParameters.getLongitude()))
           .thenReturn(CityWeatherDto.builder().setCurrentTemperature(9).build());

    Mockito.when(musicFacade
        .findPlaylistByGenre("classical"))
           .thenReturn(new ArrayList<>());

    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityCoordinates(initialDtoParameters.getLatitude(),
            initialDtoParameters.getLongitude());

    Mockito.verify(musicFacade, Mockito.atLeastOnce()).findPlaylistByGenre("classical");
  }

  @Test
  public void shouldThrowAnExceptionPlaylistSuggestionsCityNameNotFoundByUnknownCityName()
      throws PlaylistSuggestionByCityNameNotFound, CityWeatherNotFound {
    String paramByCityName = "unknown";
    Mockito.when(cityWeatherFacade.findByCityName(paramByCityName))
           .thenThrow(CityWeatherNotFound.class);
    thrown.expect(PlaylistSuggestionByCityNameNotFound.class);
    //test message
    thrown.expectMessage(is(PlaylistSuggestionByCityNameNotFound.MESSAGE + paramByCityName));

    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityName(paramByCityName);
  }

  @Test
  public void shouldThrowAnExceptionPlaylistSuggestionsCityCoordinatesNotFoundByUnknownCityName()
      throws PlaylistSuggestionByCityCoordinatesNotFound, CityWeatherNotFound {
    Double latitude = 999.00;
    Double longitude = -999.00;
    Mockito.when(cityWeatherFacade.findByCityCoordinates(latitude, longitude))
           .thenThrow(CityWeatherNotFound.class);
    thrown.expect(PlaylistSuggestionByCityCoordinatesNotFound.class);
    //test message
    thrown.expectMessage(
        is(PlaylistSuggestionByCityCoordinatesNotFound.MESSAGE + "Latitude: " + latitude
            + " - Longitude: " + longitude));

    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityCoordinates(latitude, longitude);
  }
}