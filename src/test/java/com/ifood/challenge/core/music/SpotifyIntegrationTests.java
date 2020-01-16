package com.ifood.challenge.core.music;

import com.ifood.challenge.MusicSuggesterApplication;
import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.core.music.exception.MusicPlaylistByGenreNotFound;
import com.ifood.challenge.shared.EnvProfiles;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(EnvProfiles.PROD)
@Profile("!" + EnvProfiles.DEFAULT)
@SpringBootTest(classes = MusicSuggesterApplication.class)
public class SpotifyIntegrationTests {

  @Autowired
  private MusicFacade facade;

  @Test
  public void shouldReturnAnListOfMusicsForRock() throws MusicPlaylistByGenreNotFound {
    List<MusicDto> musicDtoList = facade.findPlaylistByGenre("rock");

    Assert.assertTrue(musicDtoList != null && !musicDtoList.isEmpty());
  }
}
