package com.ifood.challenge.infra.spotify;

import com.ifood.challenge.infra.secretmanager.SpotifySecretManagerSingleton;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpotifyConfiguration {

  @Value("${spotify.api.key.value}")
  private String currentToken;

  @PostConstruct
  public void init() {
    SpotifySecretManagerSingleton.getInstance().changeToken(currentToken);
  }
}
