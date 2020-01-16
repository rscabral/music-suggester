package com.ifood.challenge.core.cityweather;

import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.infra.openweather.OpenWeatherDto;
import java.util.function.Function;

/**
 * The class Open weather city weather mapper.
 * Created at 15 de jan de 2020 21:33:23
 */
public class OpenWeatherCityWeatherMapper {
  /**
   * The Kelvin to integer celsius.
   */
  private static Function<Double, Integer> kelvinToIntegerCelsius = (k) -> (int) (k - 273.15);

  /**
   * Map from origin to internal dto city weather dto.
   * Created at 15 de jan de 2020 21:33:23
   *
   * @param openWeatherDto the open weather dto
   * @return the city weather dto
   */
  public static CityWeatherDto mapFromOriginToInternalDto(OpenWeatherDto openWeatherDto) {
    if (openWeatherDto == null) {
      return null;
    }
    return CityWeatherDto.builder()
                         .setCurrentTemperature(handleCurrentTemp(openWeatherDto))
                         .setCityName(handleCityName(openWeatherDto))
                         .setLongitude(handleLongitude(openWeatherDto))
                         .setLatitude(handleLatitude(openWeatherDto))
                         .build();
  }

  /**
   * Handle longitude double.
   * Created at 15 de jan de 2020 21:33:23
   *
   * @param openWeatherDto the open weather dto
   * @return the double
   */
  private static Double handleLongitude(OpenWeatherDto openWeatherDto) {
    if (openWeatherDto.hasCoordinates()) {
      return openWeatherDto.getCoord().getLon();
    }
    return null;
  }

  /**
   * Handle latitude double.
   * Created at 15 de jan de 2020 21:33:23
   *
   * @param openWeatherDto the open weather dto
   * @return the double
   */
  private static Double handleLatitude(OpenWeatherDto openWeatherDto) {
    if (openWeatherDto.hasCoordinates()) {
      return openWeatherDto.getCoord().getLat();
    }
    return null;
  }

  /**
   * Handle city name string.
   * Created at 15 de jan de 2020 21:33:23
   *
   * @param openWeatherDto the open weather dto
   * @return the string
   */
  private static String handleCityName(OpenWeatherDto openWeatherDto) {
    if (openWeatherDto.hasName()) {
      return openWeatherDto.getName();
    }
    return null;
  }

  /**
   * Handle current temp integer.
   * Created at 15 de jan de 2020 21:33:23
   *
   * @param openWeatherDto the open weather dto
   * @return the integer
   */
  private static Integer handleCurrentTemp(OpenWeatherDto openWeatherDto) {
    if (openWeatherDto.hasMainData() && openWeatherDto.getMain().hasTemp()) {
      Integer currentTempInCelsius = kelvinToIntegerCelsius
          .apply(openWeatherDto.getMain().getTemp());
      return currentTempInCelsius;
    }
    return null;
  }
}
