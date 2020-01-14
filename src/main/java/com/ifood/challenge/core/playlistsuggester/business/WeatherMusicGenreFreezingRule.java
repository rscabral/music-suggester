package com.ifood.challenge.core.playlistsuggester.business;

import com.ifood.challenge.core.playlistsuggester.IWeatherMusicGenreRule;

public class WeatherMusicGenreFreezingRule implements IWeatherMusicGenreRule {
  private WeatherMusicGenreResult result;

  @Override
  public boolean evaluate(WeatherMusicGenreExpression expression) {
    this.result = new WeatherMusicGenreResult("classical");
    return true;
  }

  @Override
  public WeatherMusicGenreResult getResult() {
    return result;
  }
}
