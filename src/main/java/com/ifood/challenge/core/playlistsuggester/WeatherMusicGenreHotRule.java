package com.ifood.challenge.core.playlistsuggester;

class WeatherMusicGenreHotRule implements IWeatherMusicGenreRule {
  private WeatherMusicGenreResult result;

  @Override
  public boolean evaluate(WeatherMusicGenreExpression expression) {
    boolean evalResult = false;
    if (expression.getTemperature() > 30.0) {
      this.result = new WeatherMusicGenreResult("party");
      evalResult = true;
    }
    return evalResult;
  }

  @Override
  public WeatherMusicGenreResult getResult() {
    return result;
  }
}
