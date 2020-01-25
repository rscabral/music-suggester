package com.ifood.challenge.infra.spotify;

import com.ifood.challenge.shared.EnvProfiles;
import feign.hystrix.FallbackFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(EnvProfiles.PROD)
@Service
class SpotifyProxyFallbackFactory implements FallbackFactory<SpotifyMusicApi> {

  @Override
  public SpotifyMusicApi create(Throwable throwable) {
    return new SpotifyMusicServiceFallback(throwable);
  }
}
