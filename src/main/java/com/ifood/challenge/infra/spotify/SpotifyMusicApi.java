package com.ifood.challenge.infra.spotify;

import com.ifood.challenge.shared.EnvProfiles;
import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Profile(EnvProfiles.PROD)
@FeignClient(value = "${spotify.api.url}", fallbackFactory =
    SpotifyProxyFallbackFactory.class)
interface SpotifyMusicApi {

  @GetMapping(path = "/search", params = {"q", "type", "market"})
  SpotifyDto findPlaylistByGenre(@RequestParam("q") String genre,
      @RequestParam("type") String type, @RequestParam("market") String market,
      @RequestHeader Map<String, Object> headerMap);
}
