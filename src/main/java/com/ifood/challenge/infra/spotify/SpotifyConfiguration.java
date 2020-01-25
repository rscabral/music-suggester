package com.ifood.challenge.infra.spotify;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Configuration;

@Configuration
class SpotifyConfiguration {

  @Autowired
  private SpotifyAppKeyRefreshService keyRefreshService;

  public CacheManager cacheManager() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    cacheManager.setCaches(Arrays.asList(new CaffeineCache("spotifyToken",
        Caffeine.newBuilder()
                .expireAfterWrite(
                    30,
                    TimeUnit.SECONDS)
                .refreshAfterWrite(
                    2,
                    TimeUnit.MINUTES)
                .build(
                    k -> keyRefreshService
                        .getRefreshToken()))));
    return cacheManager;
  }

  Caffeine<Object, Object> caffeineCacheBuilder() {
    return Caffeine.newBuilder()
                   .initialCapacity(1)
                   .maximumSize(1)
                   .expireAfterWrite(30, TimeUnit.SECONDS)
                   .refreshAfterWrite(2, TimeUnit.MINUTES);
  }
}
