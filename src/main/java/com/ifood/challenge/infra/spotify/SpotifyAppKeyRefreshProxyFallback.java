package com.ifood.challenge.infra.spotify;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;

class SpotifyAppKeyRefreshProxyFallback implements SpotifyAppKeyRefreshProxy {
  private final Logger log = LoggerFactory.getLogger(SpotifyAppKeyRefreshProxyFallback.class);
  private Throwable cause;

  public SpotifyAppKeyRefreshProxyFallback(Throwable throwable) {
    this.cause = throwable;
  }

  @Override
  public SpotifyRefreshedTokenDto refreshApiToken(MultiValueMap<String, Object> bodyData,
      Map<String, Object> clientIdAndKey) {
    if (cause != null) {
      log.error(cause.getMessage());
      // check status code
    }
    return new SpotifyRefreshedTokenDto();
  }
}
