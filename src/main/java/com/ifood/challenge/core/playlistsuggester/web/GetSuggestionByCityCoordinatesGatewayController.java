package com.ifood.challenge.core.playlistsuggester.web;

import java.util.Arrays;
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
@RequestMapping(path = "/${api.use.spotify.path}", produces =
    MediaTypes.ALPS_JSON_VALUE)
class GetSuggestionByCityCoordinatesGatewayController {
  /**
   * Gets suggestion by city coordinates.
   * Created at 12 de jan de 2020 20:38:02
   *
   * @param lat the lat
   * @param lon the lon
   * @return the suggestion by city coordinates
   */
  @GetMapping(params = {"lat", "lon"})
  CollectionModel<EntityModel<String>> getSuggestionByCityCoordinates(
      @RequestParam Long lat,
      @RequestParam Long lon) {
    // suggestionByCityCoordinatesService.execute(lat, lon);
    return new CollectionModel<>(Arrays.asList(
        new EntityModel<>("getSuggestionByCityName from Spotify - lat: " + lat + "; lon: " + lon)));

    // return null;
  }


}
