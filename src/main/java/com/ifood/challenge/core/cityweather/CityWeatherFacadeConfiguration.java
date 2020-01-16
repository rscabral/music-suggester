package com.ifood.challenge.core.cityweather;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CityWeatherFacadeConfiguration {

  CityWeatherFacade cityWeatherFacade() {
    return cityWeatherFacadeConstructor(new InMemoryCityWeatherRepository());
  }

  @Bean
  CityWeatherFacade cityWeatherFacade(ICityWeatherRepository repository) {
    return cityWeatherFacadeConstructor(repository);
  }

  CityWeatherFacade cityWeatherFacadeConstructor(ICityWeatherRepository repository) {
    return new CityWeatherFacade(repository);
  }

  @Bean
  feign.Logger.Level feignLoggerLevel() {
    return Logger.Level.HEADERS;
  }
}
