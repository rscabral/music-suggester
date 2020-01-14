package com.ifood.challenge.core.music;

import com.ifood.challenge.shared.EnvProfiles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
class MusicFacadeConfiguration {


  @Profile(EnvProfiles.DEFAULT)
  MusicFacade musicFacade() {
    return musicFacade(new InMemoryMusicRepository());
  }

  @Profile(EnvProfiles.LIVE)
  @Bean
  MusicFacade musicFacade(IMusicRepository repository) {
    return new MusicFacade(repository);
  }
}
