package com.ifood.challenge.infra.openweather;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenWeatherDto {
  @JsonProperty("main")
  private Main main;
  @JsonProperty("name")
  private String name;
  @JsonProperty("coord")
  private Coordinates coordinates;

  public Main getMain() {
    return this.main;
  }

  public void setMain(Main main) {
    this.main = main;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("coord")
  public Coordinates getCoord() {
    return this.coordinates;
  }

  @JsonProperty("coord")
  public void setCoord(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public boolean hasMainData() {
    return this.main != null;
  }

  public boolean hasCoordinates() {
    return this.getCoord() != null && this.getCoord().getLat() != null
        && getCoord().getLon() != null;
  }

  public boolean hasName() {
    return this.name != null;
  }

  public class Main {
    @JsonProperty("temp")
    private Double temp;

    public Double getTemp() {
      return this.temp;
    }

    public void setTemp(Double temp) {
      this.temp = temp;
    }

    public boolean hasTemp() {
      return this.temp != null;
    }
  }

  public class Coordinates {

    @JsonProperty("lon")
    private Double lon;
    @JsonProperty("lat")
    private Double lat;

    public Double getLon() {
      return this.lon;
    }

    public void setLon(Double lon) {
      this.lon = lon;
    }

    public Double getLat() {
      return this.lat;
    }

    public void setLat(Double lat) {
      this.lat = lat;
    }
  }
}
