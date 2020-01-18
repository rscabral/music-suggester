package com.ifood.challenge.core.playlistsuggester;

class WeatherMusicGenreFreezingRule implements IWeatherMusicGenreRule {
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
