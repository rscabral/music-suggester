package com.ifood.challenge.infra.spotify;

import com.ifood.challenge.shared.EnvProfiles;
import feign.hystrix.FallbackFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile(EnvProfiles.PROD)
@Component
class SpotifyAppKeyRefreshProxyFallbackFactory
    implements FallbackFactory<SpotifyAppKeyRefreshProxy> {
  @Override
  public SpotifyAppKeyRefreshProxy create(Throwable throwable) {
    return new SpotifyAppKeyRefreshProxyFallback(throwable);
  }
}
