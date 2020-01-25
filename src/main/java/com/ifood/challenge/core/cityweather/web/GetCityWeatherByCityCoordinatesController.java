package com.ifood.challenge.core.cityweather.web;

import com.ifood.challenge.core.cityweather.CityWeatherFacade;
import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.core.cityweather.exception.CityWeatherNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/${resource.city.path}", produces =
    MediaTypes.ALPS_JSON_VALUE)
class GetCityWeatherByCityCoordinatesController {
  @Autowired
  private CityWeatherFacade facade;

  @GetMapping(params = {"lat", "lon"})
  public EntityModel<CityWeatherDto> findByCityCoordinates(@RequestParam Double lat,
      @RequestParam Double lon) throws CityWeatherNotFound {
    CityWeatherDto cityWeatherDto = facade.findByCityCoordinates(lat, lon);
    return cityWeatherDto.toEntityModel();
  }
}
