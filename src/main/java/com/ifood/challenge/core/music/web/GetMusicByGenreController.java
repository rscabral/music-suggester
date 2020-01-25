package com.ifood.challenge.core.music.web;

import com.ifood.challenge.core.music.MusicFacade;
import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.core.music.exception.MusicPlaylistByGenreNotFound;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/${resource.music.path}", produces =
    MediaTypes.ALPS_JSON_VALUE)
class GetMusicByGenreController {
  @Autowired
  private MusicFacade facade;

  @GetMapping(params = {"genre"})
  public CollectionModel<EntityModel<MusicDto>> getMusicsByGenre(
      @RequestParam("genre") String genre) throws MusicPlaylistByGenreNotFound {
    List<MusicDto> musicDtoList = facade.findPlaylistByGenre(genre);
    List<EntityModel<MusicDto>> entityModels =
        musicDtoList.stream().map(MusicDto::toEntityModel).collect(
            Collectors.toList());
    return new CollectionModel<>(entityModels);
  }
}
