package com.ifood.challenge.core.cityweather;

import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.core.cityweather.exception.CityWeatherNotFound;

/**
 * The class City weather facade.
 * Created at 24 de jan de 2020 22:58:59
 */
public class CityWeatherFacade {

  /**
   * The Find city weather by city coordinates repository.
   */
  private IFindCityWeatherByCityCoordinatesRepository findCityWeatherByCityCoordinatesRepository;

  /**
   * The Find city weather by city name repository.
   */
  private IFindCityWeatherByCityNameRepository findCityWeatherByCityNameRepository;

  /**
   * Instantiates a new City weather facade.
   *
   * @param findCityWeatherByCityCoordinatesRepository the find city weather by city coordinates
   *                                                   repository
   * @param findCityWeatherByCityNameRepository        the find city weather by city name repository
   *                                                   Created on 24 de jan de 2020 22:58:59
   */
  public CityWeatherFacade(
      IFindCityWeatherByCityCoordinatesRepository findCityWeatherByCityCoordinatesRepository,
      IFindCityWeatherByCityNameRepository findCityWeatherByCityNameRepository) {
    this.findCityWeatherByCityCoordinatesRepository = findCityWeatherByCityCoordinatesRepository;
    this.findCityWeatherByCityNameRepository = findCityWeatherByCityNameRepository;
  }

  /**
   * Find by city name city weather dto.
   * Created at 24 de jan de 2020 22:58:59
   *
   * @param cityName the city name
   * @return the city weather dto
   * @throws CityWeatherNotFound the city weather not found
   */
  public CityWeatherDto findByCityName(String cityName) throws CityWeatherNotFound {
    return findCityWeatherByCityNameRepository.findByCityName(cityName)
                                              .orElseThrow(CityWeatherNotFound::new);
  }

  /**
   * Find by city coordinates city weather dto.
   * Created at 24 de jan de 2020 22:58:59
   *
   * @param latitude  the latitude
   * @param longitude the longitude
   * @return the city weather dto
   * @throws CityWeatherNotFound the city weather not found
   */
  public CityWeatherDto findByCityCoordinates(Double latitude,
      Double longitude) throws CityWeatherNotFound {
    return findCityWeatherByCityCoordinatesRepository.findByCityCoordinates(latitude, longitude)
                                                     .orElseThrow(
                                                         CityWeatherNotFound::new);
  }

}
