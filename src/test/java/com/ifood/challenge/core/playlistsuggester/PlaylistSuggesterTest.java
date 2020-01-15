package com.ifood.challenge.core.playlistsuggester;

import static org.hamcrest.Matchers.is;


import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.core.playlistsuggester.dto.PlaylistSuggesterDto;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityCoordinatesNotFound;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityNameNotFound;
import com.ifood.challenge.shared.EnvProfiles;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(EnvProfiles.DEFAULT)
public class PlaylistSuggesterTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private PlaylistSuggesterFacade facade =
      new PlaylistSuggesterConfiguration().playlistSuggesterFacade();
  private PlaylistSuggesterDto initialDtoParameters = createPlaylistSuggesterDto();

  private PlaylistSuggesterDto createPlaylistSuggesterDto() {
    return PlaylistSuggesterDto.builder().setCityName("London").setCityCoordinates(123.00,
        -123.00).build();
  }

  @Test
  public void shouldReturnAPlaylistSuggestionsByCityName()
      throws PlaylistSuggestionByCityNameNotFound {
    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityName(initialDtoParameters.getCityName());

    Assert.assertNotNull(playlistSuggesterDtoList);
    Assert.assertTrue(!playlistSuggesterDtoList.isEmpty());
  }

  @Test
  public void shouldReturnAPlaylistSuggestionsByCityCoordinates()
      throws PlaylistSuggestionByCityCoordinatesNotFound {
    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityCoordinates(initialDtoParameters.getLatitude(),
            initialDtoParameters.getLongitude());

    Assert.assertNotNull(playlistSuggesterDtoList);
    Assert.assertTrue(!playlistSuggesterDtoList.isEmpty());
  }

  @Test
  public void shouldThrowAnExceptionPlaylistSuggestionsCityNameNotFoundByUnknownCityName()
      throws PlaylistSuggestionByCityNameNotFound {
    String paramByCityName = "unknown";

    thrown.expect(PlaylistSuggestionByCityNameNotFound.class);
    //test message
    thrown.expectMessage(is(PlaylistSuggestionByCityNameNotFound.MESSAGE + paramByCityName));

    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityName(paramByCityName);
  }

  @Test
  public void shouldThrowAnExceptionPlaylistSuggestionsCityCoordinatesNotFoundByUnknownCityName()
      throws PlaylistSuggestionByCityCoordinatesNotFound {
    Double latitude = 999.00;
    Double longitude = -999.00;

    thrown.expect(PlaylistSuggestionByCityCoordinatesNotFound.class);
    //test message
    thrown.expectMessage(
        is(PlaylistSuggestionByCityCoordinatesNotFound.MESSAGE + "Latitude: " + latitude
            + " - Longitude: " + longitude));

    List<MusicDto> playlistSuggesterDtoList =
        facade.getPlaylistWeatherSuggestionByCityCoordinates(latitude, longitude);
  }
}
