package com.ifood.challenge.core.music;

import com.ifood.challenge.core.music.dto.MusicDto;
import java.util.Collection;
import java.util.Optional;

interface IMusicRepository {
  Optional<Collection<MusicDto>> findByGenre(String genre);
}
