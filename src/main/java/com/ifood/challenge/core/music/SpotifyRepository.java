package com.ifood.challenge.core.music;

import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.infra.spotify.SpotifyDto;
import com.ifood.challenge.infra.spotify.SpotifyMusicProxy;
import com.ifood.challenge.shared.EnvProfiles;
import java.util.Collection;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("!" + EnvProfiles.DEFAULT)
@Component
class SpotifyRepository implements IFindMusicPlaylistByGenreRepository {

  @Autowired
  private SpotifyMusicProxy spotifyMusicProxy;

  @Override
  public Optional<Collection<MusicDto>> findByGenre(String genre) {
    SpotifyDto spotifyDto = spotifyMusicProxy.findPlaylistByGenre(genre);
    return Optional.ofNullable(SpotifyDtoMapper.mapFromOriginToInternalMusicDto(spotifyDto));
  }
}
