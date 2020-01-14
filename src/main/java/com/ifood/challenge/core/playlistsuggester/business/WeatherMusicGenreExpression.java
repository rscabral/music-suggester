package com.ifood.challenge.core.playlistsuggester.business;

import java.io.Serializable;

public class WeatherMusicGenreExpression implements Serializable {
  private Long temperature;

  public WeatherMusicGenreExpression(Long temperature) {
    this.temperature = temperature;
  }

  public Long getTemperature() {
    return temperature;
  }
}
