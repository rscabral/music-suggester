package com.ifood.challenge.core.playlistsuggester;

import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.shared.EnvProfiles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Profile;

@Profile(EnvProfiles.DEFAULT)
class InMemoryMusicProxy implements IMusicProxy {

  private Map<String, List<MusicDto>> dataBaseByGenre;

  public InMemoryMusicProxy() {
    this.dataBaseByGenre = new HashMap<>();
    List<MusicDto> musicDtoList = new ArrayList<>();
    musicDtoList.add(
        MusicDto.builder().setMusicName("I Appear Missing").setGenre("rock").setPerformer(
            "QotSA").build());
    musicDtoList.add(
        MusicDto.builder().setMusicName("Go With The Flow").setGenre("rock").setPerformer(
            "QotSA").build());
    musicDtoList.add(
        MusicDto.builder().setMusicName("Some music of Ariana Grande").setGenre("pop").setPerformer(
            "Ariana Grande").build());
    musicDtoList.add(
        MusicDto.builder().setMusicName("Some music of Ariana Grande").setGenre("pop").setPerformer(
            "Ariana Grande").build());
    musicDtoList.add(
        MusicDto.builder().setMusicName("Daftpunk").setGenre("party").setPerformer(
            "Too Long / Steam Machine").build());
    musicDtoList.add(
        MusicDto.builder().setMusicName("Vladimir Sterzer").setGenre("classical").setPerformer(
            "Another Style").build());

    dataBaseByGenre = musicDtoList.stream().collect(Collectors.groupingBy(MusicDto::getGenre));
  }

  @Override
  public List<MusicDto> findPlaylistByGenre(String musicGenre) {
    return dataBaseByGenre.get(musicGenre);
  }
}
