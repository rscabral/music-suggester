package com.ifood.challenge.core.music;

import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.core.music.exception.MusicPlaylistByGenreNotFound;
import java.util.List;

public class MusicFacade {
  private IMusicRepository repository;

  public MusicFacade(IMusicRepository repository) {
    this.repository = repository;
  }

  public List<MusicDto> findPlaylistByGenre(String genre) throws MusicPlaylistByGenreNotFound {
    return (List<MusicDto>) repository.findByGenre(genre).orElseThrow(
        () -> new MusicPlaylistByGenreNotFound(genre));
  }
}
