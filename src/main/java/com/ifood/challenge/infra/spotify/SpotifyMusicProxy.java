package com.ifood.challenge.infra.spotify;

import com.ifood.challenge.infra.secretmanager.SpotifySecretManagerSingleton;
import com.ifood.challenge.shared.EnvProfiles;
import com.ifood.challenge.shared.ultils.SimpleAsyncExecutionHelper;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

/**
 * The class Spotify music service.
 * Created at 24 de jan de 2020 23:01:04
 */
@Profile(EnvProfiles.PROD)
@Service
public class SpotifyMusicProxy {
  /**
   * The Log.
   */
  private final Logger log = LoggerFactory.getLogger(SpotifyMusicProxy.class);

  /**
   * The Header map.
   */
  private Map<String, Object> headerMap;

  /**
   * The Spotify music proxy.
   */
  @Autowired
  private SpotifyMusicApi spotifyMusicProxy;

  /**
   * Reload.
   * Created at 24 de jan de 2020 23:01:04
   */
  private void reload() {
    headerMap = new HashMap<>();
    headerMap.put("Accept", MediaType.APPLICATION_JSON);
    headerMap.put("Authorization",
        "Bearer " + SpotifySecretManagerSingleton.getInstance().getCurrentToken());
    headerMap.put("Content-Type", MediaType.APPLICATION_JSON);
  }

  /**
   * Find playlist by genre spotify dto.
   * Created at 24 de jan de 2020 23:01:04
   *
   * @param genre the genre
   * @return the spotify dto
   */
  public SpotifyDto findPlaylistByGenre(String genre) {
    reload();
    return SimpleAsyncExecutionHelper.executeAsync(() -> spotifyMusicProxy
        .findPlaylistByGenre(getGenreParam(genre), "track", getMarketParam(),
            this.headerMap), log);
  }

  /**
   * Gets market param.
   * Created at 24 de jan de 2020 23:01:05
   *
   * @return the market param
   */
  private String getMarketParam() {
    return "US";
  }

  /**
   * Gets genre param.
   * Created at 24 de jan de 2020 23:01:05
   *
   * @param genre the genre
   * @return the genre param
   */
  private String getGenreParam(String genre) {
    return "genre%3A" + genre;
  }
}
