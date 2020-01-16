package com.ifood.challenge.infra.spotify;

import com.ifood.challenge.infra.secretmanager.SpotifySecretManagerSingleton;
import com.ifood.challenge.shared.EnvProfiles;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Supplier;
import javax.annotation.PostConstruct;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Profile(EnvProfiles.PROD)
@Service
class SpotifyAppKeyRefreshService {
  private final Logger log = LoggerFactory.getLogger(SpotifyAppKeyRefreshService.class);

  @Value("${spotify.api.key.value}")
  private String currentToken;

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
    // headerMap.put("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);

    responseBodyMap = new LinkedMultiValueMap<>();
    responseBodyMap.add("grant_type", "client_credentials");
  }

  private void setCurrentToken(String currentToken) {
    this.currentToken = currentToken;
  }

  public void refreshToken() {
    SpotifyRefreshedTokenDto refreshedTokenDto =
        executeAsync(() -> spotifyAppKeyRefreshProxy.refreshApiToken(responseBodyMap, headerMap));

    if (refreshedTokenDto != null && refreshedTokenDto.hasToken()) {
      // The idea would be store this key in a Secrets Manager, but here I'll do the basic
      // update token
      SpotifySecretManagerSingleton.getInstance().changeToken(refreshedTokenDto.getAccessToken());
      setCurrentToken(refreshedTokenDto.getAccessToken());
      log.info("Spotify Refreshed Token: {}", refreshedTokenDto.getAccessToken());
    } else {
      log.error("Refreshing Token has failed. Current token: {}",
          SpotifySecretManagerSingleton.getInstance().getCurrentToken());
    }
  }

  @NotNull
  private SpotifyRefreshedTokenDto executeAsync(Supplier<SpotifyRefreshedTokenDto> executor) {
    try {
      final CompletableFuture<SpotifyRefreshedTokenDto> spotifyDtoFuture =
          CompletableFuture.supplyAsync(executor);
      // Thread free to do other tasks...
      final SpotifyRefreshedTokenDto spotifyDto = spotifyDtoFuture.join();
      return spotifyDto;
    } catch (CompletionException e) {
      log.error("Error: {}", e.getCause().getMessage());
    } catch (CancellationException e) {
      log.error("Async operation cancelled.");
    }

    return null;
  }

}
