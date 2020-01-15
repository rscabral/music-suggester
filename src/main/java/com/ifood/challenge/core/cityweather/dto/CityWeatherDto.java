package com.ifood.challenge.core.cityweather.dto;

import com.ifood.challenge.shared.dto.DtoObject;
import org.springframework.hateoas.EntityModel;

public class CityWeatherDto implements DtoObject {
  private Integer currentTemperature;
  private Double latitude;
  private Double longitude;
  private String cityName;

  private CityWeatherDto() {
  }

  public static CityWeatherDtoCreator builder() {
    return new CityWeatherDtoCreator();
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public String getCityName() {
    return cityName;
  }

  public Integer getCurrentTemperature() {
    return currentTemperature;
  }

  @Override
  public EntityModel<CityWeatherDto> toEntityModel() {
    return new EntityModel<>(this);
  }

  public static class CityWeatherDtoCreator {
    private CityWeatherDto cityWeatherDto;

    protected CityWeatherDtoCreator() {
      this.cityWeatherDto = new CityWeatherDto();
    }

    public CityWeatherDtoCreator setLatitude(Double latitude) {
      this.cityWeatherDto.latitude = latitude;
      return this;
    }

    public CityWeatherDtoCreator setLongitude(Double longitude) {
      this.cityWeatherDto.longitude = longitude;
      return this;
    }

    public CityWeatherDto build() {
      return this.cityWeatherDto;
    }

    public CityWeatherDtoCreator setCityName(String cityName) {
      this.cityWeatherDto.cityName = cityName;
      return this;
    }

    public CityWeatherDtoCreator setCurrentTemperature(Integer currentTemperature) {
      this.cityWeatherDto.currentTemperature = currentTemperature;
      return this;
    }
  }
}
