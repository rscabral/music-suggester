package com.ifood.challenge.core.music;

import com.ifood.challenge.core.music.dto.MusicDto;
import java.util.Collection;
import java.util.Optional;

interface IFindMusicPlaylistByGenreRepository {
  Optional<Collection<MusicDto>> findByGenre(String genre);
}
