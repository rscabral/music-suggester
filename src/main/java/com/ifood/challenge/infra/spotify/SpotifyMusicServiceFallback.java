package com.ifood.challenge.infra.spotify;

import com.ifood.challenge.shared.EnvProfiles;
import feign.FeignException;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(EnvProfiles.PROD)
@Service
class SpotifyMusicServiceFallback implements SpotifyMusicApi {
  private final Logger log = LoggerFactory.getLogger(SpotifyMusicServiceFallback.class);
  private Throwable cause;

  public SpotifyMusicServiceFallback(Throwable cause) {
    this.cause = cause;
  }

  public SpotifyMusicServiceFallback() {
  }


  @Override
  public SpotifyDto findPlaylistByGenre(String genre, String type, String market,
      Map<String, Object> headerMap) {
    if (cause != null) {
      if (cause instanceof FeignException) {
        FeignException exception = (FeignException) cause;
        if (exception.status() == 401) {
          // with cache maybe this is not necessary anymore
        }
        /* check for status code 429 (rate-limit exceeds) (wait for X seconds - Retry-After)
         *  but probably I'll return a cash*/
        /* check for status code is 404 (and any other status?), brings cache */
        // And I'll use same behavior of rule engine here.. Or could be a decorator.. (with the
        // correspondent service implementation passed as param
        // so we will checking the rules
      }
      log.error(cause.getMessage());
    }
    return new SpotifyDto();
  }
}

