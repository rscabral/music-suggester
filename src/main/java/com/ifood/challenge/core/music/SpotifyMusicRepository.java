package com.ifood.challenge.core.music;

import com.ifood.challenge.core.music.dto.MusicDto;
import com.ifood.challenge.infra.secretmanager.SpotifySecretManagerSingleton;
import com.ifood.challenge.infra.spotify.SpotifyDto;
import com.ifood.challenge.infra.spotify.SpotifyMusicProxy;
import com.ifood.challenge.shared.EnvProfiles;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Profile("!" + EnvProfiles.DEFAULT)
@Component
class SpotifyMusicRepository implements IMusicRepository {

  private final Logger log = LoggerFactory.getLogger(SpotifyMusicRepository.class);

  private Map<String, Object> headerMap;

  @Autowired
  private SpotifyMusicProxy spotifyMusicProxy;

  public void reload() {
    headerMap = new HashMap<>();
    headerMap.put("Accept", MediaType.APPLICATION_JSON);
    headerMap.put("Authorization",
        "Bearer " + SpotifySecretManagerSingleton.getInstance().getCurrentToken());
    headerMap.put("Content-Type", MediaType.APPLICATION_JSON);
  }

  @Override
  public Optional<Collection<MusicDto>> findByGenre(String genre) {
    reload();
    return executeAsync(() -> spotifyMusicProxy
        .findPlaylistByGenre(getGenreParam(genre), "track", getMarketParam(),
            this.headerMap));
  }

  @NotNull
  private Optional<Collection<MusicDto>> executeAsync(Supplier<SpotifyDto> executor) {
    try {
      final CompletableFuture<SpotifyDto> spotifyDtoFuture =
          CompletableFuture.supplyAsync(executor);
      // Thread free to do other tasks...
      final SpotifyDto spotifyDto = spotifyDtoFuture.join();
      return Optional.ofNullable(SpotifyDtoMapper.mapFromOriginToInternalMusicDto(spotifyDto));
    } catch (CompletionException e) {
      log.error("Error: {}", e.getCause().getMessage());
    } catch (CancellationException e) {
      log.error("Async operation cancelled.");
    }

    return Optional.empty();
  }

  private String getMarketParam() {
    return "US";
  }

  private String getGenreParam(String genre) {
    return "genre%3A" + genre;
  }
}
