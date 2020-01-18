package com.ifood.challenge.core.playlistsuggester;

class WeatherMusicGenreChillyRule implements IWeatherMusicGenreRule {
  private WeatherMusicGenreResult result;

  @Override
  public boolean evaluate(WeatherMusicGenreExpression expression) {
    boolean evalResult = false;
    if (expression.getTemperature() <= 14.0 && expression.getTemperature() >= 10.0) {
      this.result = new WeatherMusicGenreResult("rock");
      evalResult = true;
    }
    return evalResult;
  }

  @Override
  public WeatherMusicGenreResult getResult() {
    return result;
  }
}
