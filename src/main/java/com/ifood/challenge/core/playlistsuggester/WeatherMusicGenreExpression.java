package com.ifood.challenge.core.playlistsuggester;

import java.io.Serializable;

class WeatherMusicGenreExpression implements Serializable {
  private Integer temperature;

  public WeatherMusicGenreExpression(Integer temperature) {
    this.temperature = temperature;
  }

  public Integer getTemperature() {
    return temperature;
  }
}
