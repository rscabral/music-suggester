package com.ifood.challenge.core.playlistsuggester.web;

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
@RequestMapping(path = "/${api.use.spotify.path}", produces = MediaTypes.ALPS_JSON_VALUE)
class GetSuggestionByCityNameGatewayController {

  /**
   * Execute entity model.
   *
   * @return the entity model Created on 12 de jan de 2020 18:39:13
   */
  @GetMapping(params = {"cityName"})
  EntityModel<String> getSuggestionByCityName(
      @RequestParam("cityName") String cityName) {
    String musicSuggestionDto = "getSuggestionByCityName from Spotify - cityName: " + cityName;
    return new EntityModel<>(musicSuggestionDto);
  }
}
