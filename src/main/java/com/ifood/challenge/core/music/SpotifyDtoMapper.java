package com.ifood.challenge.core.music;

import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.infra.spotify.SpotifyDto;
import com.ifood.challenge.infra.spotify.SpotifyDtoItem;
import java.util.LinkedList;
import java.util.List;

/**
 * The class Spotify dto mapper.
 * Created at 15 de jan de 2020 21:34:08
 */
public class SpotifyDtoMapper {

  private SpotifyDtoMapper() {
  }

  /**
   * Map from origin to internal music dto list.
   * Created at 15 de jan de 2020 21:34:08
   *
   * @param spotifyDto the spotify dto
   * @return the list
   */
  public static List<MusicDto> mapFromOriginToInternalMusicDto(SpotifyDto spotifyDto) {

    List<MusicDto> musicDtoList = new LinkedList<>();
    if (spotifyDto == null || !spotifyDto.hasMusics()) {
      return musicDtoList;
    }

    for (SpotifyDtoItem song : spotifyDto.getTracks().getItems()) {
      musicDtoList.add(MusicDto.builder().setMusicName(song.getName()).build());
    }

    return musicDtoList;
  }
}
