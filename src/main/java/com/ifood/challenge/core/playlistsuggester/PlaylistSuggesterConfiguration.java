package com.ifood.challenge.core.playlistsuggester;

import com.ifood.challenge.core.cityweather.CityWeatherFacade;
import com.ifood.challenge.core.music.MusicFacade;
import feign.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PlaylistSuggesterConfiguration {

  @Bean
  PlaylistSuggesterFacade playlistSuggesterFacade(CityWeatherFacade cityWeatherFacade,
      MusicFacade musicFacade, IWeatherMusicGenreRuleEngine weatherMusicGenreRuleEngine) {
    return playlistSuggesterFacadeCreator(cityWeatherFacade, musicFacade,
        weatherMusicGenreRuleEngine);
  }


  PlaylistSuggesterFacade playlistSuggesterFacadeCreator(CityWeatherFacade cityWeatherFacade,
      MusicFacade musicFacade, IWeatherMusicGenreRuleEngine weatherMusicGenreRuleEngine) {
    return new PlaylistSuggesterFacade(cityWeatherFacade, musicFacade, weatherMusicGenreRuleEngine);
  }

  @Bean
  public Client feignClient() {
    return new Client.Default(null, null);
  }
}
