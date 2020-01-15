package com.ifood.challenge.core.playlistsuggester;

import com.ifood.challenge.core.music.dto.MusicDto;
import java.util.List;

interface IMusicProxy {
  List<MusicDto> findPlaylistByGenre(String musicGenre);
}
