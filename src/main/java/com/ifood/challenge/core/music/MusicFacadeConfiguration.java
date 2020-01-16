package com.ifood.challenge.core.music;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class MusicFacadeConfiguration {


  MusicFacade musicFacade() {
    return musicFacadeCreator(new InMemoryMusicRepository());
  }

  @Bean
  MusicFacade musicFacade(IMusicRepository repository) {
    return musicFacadeCreator(repository);
  }

  MusicFacade musicFacadeCreator(IMusicRepository repository) {
    return new MusicFacade(repository);
  }
}
