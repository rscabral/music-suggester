package com.ifood.challenge.core.music;

import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.core.music.exception.MusicPlaylistByGenreNotFound;
import java.util.List;

public class MusicFacade {
  private IFindMusicPlaylistByGenreRepository findMusicPlaylistByGenreRepository;

  public MusicFacade(IFindMusicPlaylistByGenreRepository findMusicPlaylistByGenreRepository) {
    this.findMusicPlaylistByGenreRepository = findMusicPlaylistByGenreRepository;
  }

  public List<MusicDto> findPlaylistByGenre(String genre) throws MusicPlaylistByGenreNotFound {
    return (List<MusicDto>) findMusicPlaylistByGenreRepository.findByGenre(genre).orElseThrow(
        () -> new MusicPlaylistByGenreNotFound(genre));
  }
}
