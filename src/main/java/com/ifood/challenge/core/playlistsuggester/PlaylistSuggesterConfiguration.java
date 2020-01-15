package com.ifood.challenge.core.playlistsuggester;

import com.ifood.challenge.shared.EnvProfiles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
class PlaylistSuggesterConfiguration {

  @Profile(EnvProfiles.DEFAULT)
  @Bean
  PlaylistSuggesterFacade playlistSuggesterFacade() {
    return playlistSuggesterFacadeCreator(new InMemoryCityWeatherProxy(), new InMemoryMusicProxy());
  }

  @Profile(EnvProfiles.LIVE)
  @Bean
  PlaylistSuggesterFacade playlistSuggesterFacade(ICityWeatherProxy cityWeatherProxy,
      IMusicProxy musicProxy) {
    return playlistSuggesterFacadeCreator(cityWeatherProxy, musicProxy);
  }


  PlaylistSuggesterFacade playlistSuggesterFacadeCreator(ICityWeatherProxy cityWeatherProxy,
      IMusicProxy musicProxy) {
    return new PlaylistSuggesterFacade(cityWeatherProxy, musicProxy);
  }
}
