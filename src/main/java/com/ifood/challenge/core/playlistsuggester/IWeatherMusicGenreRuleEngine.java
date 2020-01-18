package com.ifood.challenge.core.playlistsuggester;

import java.util.Queue;
import javax.annotation.PostConstruct;

public interface IWeatherMusicGenreRuleEngine {
  @PostConstruct
  void init();

  Queue<IWeatherMusicGenreRule> getRules();

  WeatherMusicGenreResult process(WeatherMusicGenreExpression expression);
}
