package com.ifood.challenge.infra.spotify;

import com.ifood.challenge.shared.EnvProfiles;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Profile(EnvProfiles.PROD)
@FeignClient(value = "${spotify.authorization.api.token}", fallbackFactory =
    SpotifyAppKeyRefreshProxyFallbackFactory.class)
interface SpotifyAppKeyRefreshProxy {

  @PostMapping(consumes =
      MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  SpotifyRefreshedTokenDto refreshApiToken(MultiValueMap<String, Object> bodyData,
      @RequestHeader(name = "Authorization") Map<String, Object> clientIdAndKey);

}
