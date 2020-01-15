package com.ifood.challenge.core.playlistsuggester.web;

import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.core.playlistsuggester.PlaylistSuggesterFacade;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityNameNotFound;
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

/**
 * The class Get suggestion by city name controller.
 * Created on 12 de jan de 2020 18:39:13
 */
@RestController
@RequestMapping(path = "/${resource.playlist.suggester.path}", produces =
    MediaTypes.ALPS_JSON_VALUE)
class GetPlaylistWeatherSuggestionByCityNameGatewayController {
  @Autowired
  private PlaylistSuggesterFacade facade;

  /**
   * Execute entity model.
   *
   * @return the entity model Created on 12 de jan de 2020 18:39:13
   */
  @GetMapping(params = {"cityName"})
  CollectionModel<EntityModel<MusicDto>> getPlaylistWeatherSuggestionByCityName(
      @RequestParam("cityName") String cityName) throws PlaylistSuggestionByCityNameNotFound {
    List<MusicDto> musicDtoList = facade.getPlaylistWeatherSuggestionByCityName(cityName);
    List<EntityModel<MusicDto>> entityModels =
        musicDtoList.stream().map(musicDto -> musicDto.toEntityModel()).collect(
            Collectors.toList());
    return new CollectionModel<>(entityModels);
  }
}
