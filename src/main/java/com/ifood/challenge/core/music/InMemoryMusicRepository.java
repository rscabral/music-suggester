package com.ifood.challenge.core.music;

import com.ifood.challenge.core.music.dto.MusicDto;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The class In memory music repository.
 * Created at 14 de jan de 2020 03:49:45
 */
class InMemoryMusicRepository implements IMusicRepository {
  /**
   * The Data base by genre.
   */
  private Map<String, List<MusicDto>> dataBaseByGenre;

  /**
   * Instantiates a new In memory music repository.
   * Created on 14 de jan de 2020 03:49:46
   */
  public InMemoryMusicRepository() {
    this.dataBaseByGenre = new HashMap<>();
    List<MusicDto> musicDtoList = new ArrayList<>();
    musicDtoList.add(MusicDto.builder().setMusicName("Cubensis Lenses").setGenre("psychedelic "
        + "rock").setPerformer("Psychedelic Porn Crumpets").build());
    musicDtoList.add(
        MusicDto.builder().setMusicName("Let it Happen").setGenre("psychedelic").setPerformer(
            "Tame Impala").build());
    musicDtoList.add(
        MusicDto.builder().setMusicName("I Appear Missing").setGenre("rock").setPerformer(
            "QotSA").build());
    musicDtoList.add(
        MusicDto.builder().setMusicName("Go With The Flow").setGenre("rock").setPerformer(
            "QotSA").build());

    dataBaseByGenre = musicDtoList.stream().collect(Collectors.groupingBy(MusicDto::getGenre));
  }

  @Override
  public Optional<Collection<MusicDto>> findByGenre(String genre) {
    return Optional.ofNullable(dataBaseByGenre.get(genre));
  }
}
