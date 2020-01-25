package com.ifood.challenge.infra.spotify;

import com.ifood.challenge.shared.EnvProfiles;
import com.ifood.challenge.shared.ultils.SimpleAsyncExecutionHelper;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Profile(EnvProfiles.PROD)
@Service
@CacheConfig(cacheNames = {"spotifyToken"})
class SpotifyAppKeyRefreshService {
  private final Logger log = LoggerFactory.getLogger(SpotifyAppKeyRefreshService.class);

  @Value("${spotify.api.client.id.key.base64}")
  private String clientIdAndClientKeyBase45Key;

  @Autowired
  private SpotifyAppKeyRefreshProxy spotifyAppKeyRefreshProxy;

  private Map<String, Object> headerMap;
  private MultiValueMap<String, Object> responseBodyMap;

  @PostConstruct
  public void init() {
    headerMap = new HashMap<>();
    headerMap.put("Authorization", "Basic " + clientIdAndClientKeyBase45Key);
    responseBodyMap = new LinkedMultiValueMap<>();
    responseBodyMap.add("grant_type", "client_credentials");
  }

  @Cacheable("spotifyToken")
  public SpotifyRefreshedTokenDto getRefreshToken() {
    SpotifyRefreshedTokenDto refreshedTokenDto =
        SimpleAsyncExecutionHelper
            .executeAsync(() -> spotifyAppKeyRefreshProxy.refreshApiToken(responseBodyMap,
                headerMap), log);

    if (refreshedTokenDto != null && refreshedTokenDto.hasToken()) {
      log.info("Spotify Refreshed Token: {}", refreshedTokenDto.getAccessToken());
      return refreshedTokenDto;
    } else {
      log.error("Refreshing Token has failed.");
    }
    return null;
  }

}
