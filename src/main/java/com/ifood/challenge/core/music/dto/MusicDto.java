package com.ifood.challenge.core.music.dto;

import com.ifood.challenge.shared.dto.DtoObject;
import org.springframework.hateoas.EntityModel;

public class MusicDto implements DtoObject {

  private String name;
  private String performer;
  private String genre;

  private MusicDto() {
  }

  public static MusicDtoCreator builder() {
    return new MusicDtoCreator();
  }

  public String getName() {
    return name;
  }

  public String getPerformer() {
    return performer;
  }

  public String getGenre() {
    return genre;
  }

  @Override
  public EntityModel<MusicDto> toEntityModel() {
    return new EntityModel<>(this);
  }

  public static class MusicDtoCreator {
    private MusicDto musicDto;

    protected MusicDtoCreator() {
      this.musicDto = new MusicDto();
    }

    public MusicDtoCreator setMusicName(String musicName) {
      this.musicDto.name = musicName;
      return this;
    }

    public MusicDtoCreator setPerformer(String performer) {
      this.musicDto.performer = performer;
      return this;
    }

    public MusicDtoCreator setGenre(String genre) {
      this.musicDto.genre = genre;
      return this;
    }

    public MusicDto build() {
      return this.musicDto;
    }
  }
}
