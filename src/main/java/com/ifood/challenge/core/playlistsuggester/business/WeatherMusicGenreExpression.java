package com.ifood.challenge.core.playlistsuggester.business;

import java.io.Serializable;

public class WeatherMusicGenreExpression implements Serializable {
  private Integer temperature;

  public WeatherMusicGenreExpression(Integer temperature) {
    this.temperature = temperature;
  }

  public Integer getTemperature() {
    return temperature;
  }
}
