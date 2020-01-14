package com.ifood.challenge.core.playlistsuggester.business;

import com.ifood.challenge.core.playlistsuggester.IWeatherMusicGenreRule;
import com.ifood.challenge.core.playlistsuggester.IWeatherMusicGenreRuleEngine;
import java.util.LinkedList;
import java.util.Queue;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
// try to turn this into interface
public class WeatherMusicGenreRuleEngine implements IWeatherMusicGenreRuleEngine {
  private Queue<IWeatherMusicGenreRule> rules = new LinkedList<>();

  @Override
  @PostConstruct
  public void init() {
    rules.add(new WeatherMusicGenreHotRule());
    rules.add(new WeatherMusicGenreWarmRule());
    rules.add(new WeatherMusicGenreChillyRule());
    rules.add(new WeatherMusicGenreFreezingRule());
  }

  @Override
  public Queue<IWeatherMusicGenreRule> getRules() {
    return rules;
  }

  @Override
  public WeatherMusicGenreResult process(WeatherMusicGenreExpression expression) {
    IWeatherMusicGenreRule rule = getRules()
        .stream()
        .filter(r -> r.evaluate(expression))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Expression does not matches any Rule"));
    return (WeatherMusicGenreResult) rule.getResult();
  }
}
