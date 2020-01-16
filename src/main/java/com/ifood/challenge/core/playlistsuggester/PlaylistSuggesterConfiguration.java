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
      MusicFacade musicFacade) {
    return playlistSuggesterFacadeCreator(cityWeatherFacade, musicFacade);
  }


  PlaylistSuggesterFacade playlistSuggesterFacadeCreator(CityWeatherFacade cityWeatherFacade,
      MusicFacade musicFacade) {
    return new PlaylistSuggesterFacade(cityWeatherFacade, musicFacade);
  }

  @Bean
  public Client feignClient() {
    return new Client.Default(null, null);
  }
}
