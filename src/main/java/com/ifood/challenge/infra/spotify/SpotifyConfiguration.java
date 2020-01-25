package com.ifood.challenge.infra.spotify;

import com.ifood.challenge.infra.secretmanager.SpotifySecretManagerSingleton;


public class SpotifyConfiguration {

  //  @Value("${spotify.api.key.value}")
  private String currentToken;

  // @PostConstruct
  public void init() {
    SpotifySecretManagerSingleton.getInstance().changeToken(currentToken);
  }
}
