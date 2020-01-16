package com.ifood.challenge.core.cityweather;

import static org.hamcrest.Matchers.is;


import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.core.cityweather.exception.CityWeatherNotFound;
import com.ifood.challenge.shared.EnvProfiles;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(EnvProfiles.DEFAULT)
public class CityWeatherUnitTest {
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  private CityWeatherFacade facade = new CityWeatherFacadeConfiguration().cityWeatherFacade();
  private CityWeatherDto initialDtoParameters = createCityWeatherDto();

  private CityWeatherDto createCityWeatherDto() {
    return CityWeatherDto.builder().setCityName("London").setLatitude(123.00).setLongitude(
        -123.00).build();
  }

  @Test
  public void shouldGetCityWeatherByCityName() throws CityWeatherNotFound {
    CityWeatherDto responseDto = facade.findByCityName(
        initialDtoParameters.getCityName());
    Assert.assertNotNull(responseDto.getCurrentTemperature());
  }

  @Test
  public void shouldGetCityWeatherByCityCoordinates() throws CityWeatherNotFound {
    CityWeatherDto responseDto =
        facade.findByCityCoordinates(initialDtoParameters.getLatitude(),
            initialDtoParameters.getLongitude());
    Assert.assertNotNull(responseDto.getCurrentTemperature());
  }

  @Test
  public void shouldThrowAnExceptionByNonexistentCityName() throws CityWeatherNotFound {
    thrown.expect(CityWeatherNotFound.class);
    //test message
    thrown.expectMessage(is(CityWeatherNotFound.MESSAGE));

    CityWeatherDto responseDto = facade.findByCityName(
        "NonExistentCity");
  }

  @Test
  public void shouldThrowAnExceptionByNonexistentCityCoordinates() throws CityWeatherNotFound {
    thrown.expect(CityWeatherNotFound.class);
    //test message
    thrown.expectMessage(is("CityWeather Not Found!"));

    CityWeatherDto responseDto = facade.findByCityCoordinates(
        9999.0, -9812.1);
  }


}
