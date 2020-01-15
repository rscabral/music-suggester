package com.ifood.challenge.core.playlistsuggester.web;

import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.core.playlistsuggester.PlaylistSuggesterFacade;
import com.ifood.challenge.core.playlistsuggester.exception.PlaylistSuggestionByCityCoordinatesNotFound;
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
 * The class Get suggestion by city coordinates controller.
 * Created at 12 de jan de 2020 20:38:02
 */
@RestController
// remove this spotify
@RequestMapping(path = "/${resource.playlist.suggester.path}", produces =
    MediaTypes.ALPS_JSON_VALUE)
class GetPlaylistWeatherSuggestionByCityCoordinatesGatewayController {
  @Autowired
  private PlaylistSuggesterFacade facade;

  /**
   * Gets suggestion by city coordinates.
   * Created at 12 de jan de 2020 20:38:02
   *
   * @param lat the lat
   * @param lon the lon
   * @return the suggestion by city coordinates
   */
  @GetMapping(params = {"lat", "lon"})
  CollectionModel<EntityModel<MusicDto>> getPlaylistWeatherSuggestionByCityCoordinates(
      @RequestParam Double lat,
      @RequestParam Double lon) throws PlaylistSuggestionByCityCoordinatesNotFound {
    List<MusicDto> musicDtoList =
        facade.getPlaylistWeatherSuggestionByCityCoordinates(lat, lon);
    List<EntityModel<MusicDto>> entityModels =
        musicDtoList.stream().map(musicDto -> musicDto.toEntityModel()).collect(
            Collectors.toList());
    return new CollectionModel<>(entityModels);
  }


}
