package com.ifood.challenge.core.cityweather;

import com.ifood.challenge.shared.EnvProfiles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
class CityWeatherFacadeConfiguration {

  @Profile(EnvProfiles.DEFAULT)
  @Bean
  CityWeatherFacade cityWeatherFacade() {
    return cityWeatherFacadeConstructor(new InMemoryCityWeatherRepository());
  }

  @Profile(EnvProfiles.LIVE)
  @Bean
  CityWeatherFacade cityWeatherFacade(ICityWeatherRepository repository) {
    return cityWeatherFacadeConstructor(repository);
  }

  CityWeatherFacade cityWeatherFacadeConstructor(ICityWeatherRepository repository) {
    return new CityWeatherFacade(repository);
  }

}
