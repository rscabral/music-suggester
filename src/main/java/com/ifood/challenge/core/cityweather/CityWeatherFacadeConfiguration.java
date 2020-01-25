package com.ifood.challenge.core.cityweather;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CityWeatherFacadeConfiguration {

  CityWeatherFacade cityWeatherFacade() {
    return cityWeatherFacadeConstructor(new InMemoryFindCityWeatherByCityCoordinatesRepository(),
        new InMemoryFindCityWeatherByCityCoordinatesRepository());
  }

  @Bean
  CityWeatherFacade cityWeatherFacade(
      IFindCityWeatherByCityCoordinatesRepository findCityWeatherByCityCoordinatesRepository,
      IFindCityWeatherByCityNameRepository findCityWeatherByCityNameRepository) {
    return cityWeatherFacadeConstructor(findCityWeatherByCityCoordinatesRepository,
        findCityWeatherByCityNameRepository);
  }

  CityWeatherFacade cityWeatherFacadeConstructor(
      IFindCityWeatherByCityCoordinatesRepository findCityWeatherByCityCoordinatesRepository,
      IFindCityWeatherByCityNameRepository findCityWeatherByCityNameRepository) {
    return new CityWeatherFacade(findCityWeatherByCityCoordinatesRepository,
        findCityWeatherByCityNameRepository);
  }

  @Bean
  feign.Logger.Level feignLoggerLevel() {
    return Logger.Level.HEADERS;
  }
}
