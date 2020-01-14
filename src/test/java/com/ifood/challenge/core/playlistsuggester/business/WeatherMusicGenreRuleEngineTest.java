package com.ifood.challenge.core.playlistsuggester.business;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherMusicGenreRuleEngineTest {

  private static WeatherMusicGenreRuleEngine ruleEngine = new WeatherMusicGenreRuleEngine();

  @Test
  public void whenCityTemperatureIsAbove30Celcius() {
    WeatherMusicGenreExpression expression = new WeatherMusicGenreExpression(31L);
    WeatherMusicGenreResult result = ruleEngine.process(expression);

    Assert.assertNotNull(result);
    Assert.assertEquals("party", result.getValue());
  }

  @Test
  public void whenCityTemperatureIsBetween15And30Celcius() {
    WeatherMusicGenreExpression minTemperature = new WeatherMusicGenreExpression(15L);
    WeatherMusicGenreExpression maxTemperature = new WeatherMusicGenreExpression(30L);
    WeatherMusicGenreResult result = ruleEngine.process(minTemperature);

    Assert.assertNotNull(result);
    Assert.assertEquals("pop", result.getValue());

    result = ruleEngine.process(maxTemperature);

    Assert.assertNotNull(result);
    Assert.assertEquals("pop", result.getValue());
  }

  @Test
  public void whenCityTemperatureIsBetween10And14Celcius() {
    WeatherMusicGenreExpression minTemperature = new WeatherMusicGenreExpression(10L);
    WeatherMusicGenreExpression maxTemperature = new WeatherMusicGenreExpression(14L);
    WeatherMusicGenreResult result = ruleEngine.process(minTemperature);

    Assert.assertNotNull(result);
    Assert.assertEquals("rock", result.getValue());

    result = ruleEngine.process(maxTemperature);

    Assert.assertNotNull(result);
    Assert.assertEquals("rock", result.getValue());
  }

  @Test
  public void whenCityTemperatureIsBelow10Celcius() {
    WeatherMusicGenreExpression expression = new WeatherMusicGenreExpression(9L);
    WeatherMusicGenreResult result = ruleEngine.process(expression);

    Assert.assertNotNull(result);
    Assert.assertEquals("classical", result.getValue());
  }
}
