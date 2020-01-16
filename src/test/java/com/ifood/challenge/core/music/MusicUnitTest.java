package com.ifood.challenge.core.music;

import static org.hamcrest.Matchers.is;


import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.core.music.exception.MusicPlaylistByGenreNotFound;
import com.ifood.challenge.shared.EnvProfiles;
import java.util.List;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(EnvProfiles.DEFAULT)
public class MusicUnitTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private MusicFacade facade = new MusicFacadeConfiguration().musicFacade();
  private MusicDto initialDtoParameters = createMusicDto();

  private MusicDto createMusicDto() {
    return MusicDto.builder().setMusicName("Cubensis Lenses").setPerformer("Psychedelic Porn "
        + "Crumpets").setGenre("psychedelic rock").build();
  }

  @Test
  public void shouldReturnAMusicByGenre() throws MusicPlaylistByGenreNotFound {
    List<MusicDto> musicDtoList = facade.findPlaylistByGenre(initialDtoParameters.getGenre());
    Assert.assertNotNull(musicDtoList);
  }

  @Test
  public void shouldThrowAnExceptionForWrongGenre() throws MusicPlaylistByGenreNotFound {
    String paramByGenre = "unknown";
    thrown.expect(MusicPlaylistByGenreNotFound.class);
    //test message
    thrown.expectMessage(is(MusicPlaylistByGenreNotFound.MESSAGE + paramByGenre));
    List<MusicDto> musicDtoList = facade.findPlaylistByGenre(paramByGenre);
  }
}
