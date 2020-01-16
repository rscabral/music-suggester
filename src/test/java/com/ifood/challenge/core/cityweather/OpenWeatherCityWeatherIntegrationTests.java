package com.ifood.challenge.core.cityweather;

import com.ifood.challenge.MusicSuggesterApplication;
import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.core.cityweather.exception.CityWeatherNotFound;
import com.ifood.challenge.shared.EnvProfiles;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(EnvProfiles.PROD)
@Profile("!" + EnvProfiles.DEFAULT)
@SpringBootTest(classes = MusicSuggesterApplication.class)
public class OpenWeatherCityWeatherIntegrationTests {

  @Autowired
  private CityWeatherFacade facade;

  @Test
  public void shouldReturnCityCurrentWeather() throws CityWeatherNotFound {
    CityWeatherDto cityWeatherDto = facade.findByCityName("London");

    Assert.assertNotNull(cityWeatherDto.getCurrentTemperature());
  }

  @Test
  public void shouldReturnAnExceptionCityNotFoundEmptyValue() {
    Assertions.assertThrows(CityWeatherNotFound.class, () -> facade.findByCityName(
        "teastasd"));
  }
}
