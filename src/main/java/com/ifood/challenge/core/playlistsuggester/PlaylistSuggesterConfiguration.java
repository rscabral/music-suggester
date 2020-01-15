package com.ifood.challenge.core.playlistsuggester;

import com.ifood.challenge.shared.EnvProfiles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

class PlaylistSuggesterConfiguration {

  @Profile(EnvProfiles.DEFAULT)
  @Bean
  PlaylistSuggesterFacade playlistSuggesterFacade() {
    return playlistSuggesterFacadeCreator(new InMemoryCityWeatherProxy(), new InMemoryMusicProxy());
  }

  PlaylistSuggesterFacade playlistSuggesterFacadeCreator(ICityWeatherProxy cityWeatherProxy,
      IMusicProxy musicProxy) {
    return new PlaylistSuggesterFacade(cityWeatherProxy, musicProxy);
  }
}
