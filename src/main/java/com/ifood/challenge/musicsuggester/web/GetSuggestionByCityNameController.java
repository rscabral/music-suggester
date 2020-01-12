package com.ifood.challenge.musicsuggester.web;

import com.ifood.challenge.musicsuggester.web.dto.MusicSuggestionDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The class Get suggestion by city name controller.
 * Created on 12 de jan de 2020 18:39:13
 */
@RestController
@RequestMapping(path = "/music-weather-suggester", produces = MediaTypes.ALPS_JSON_VALUE)
public class GetSuggestionByCityNameController {

  /**
   * Execute entity model.
   *
   * @return the entity model Created on 12 de jan de 2020 18:39:13
   */
  @GetMapping("/getSuggestionByCityName")
  public EntityModel<MusicSuggestionDto> execute() {
    MusicSuggestionDto musicSuggestionDto = new MusicSuggestionDto();
    musicSuggestionDto.setText("Hello World");
    return new EntityModel<>(musicSuggestionDto);
  }
}
