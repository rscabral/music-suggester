package com.ifood.challenge.infra.spotify;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class SpotifyScheduler {

  @Autowired
  private CacheManager cacheManager;

  @Scheduled(fixedRate = 3000000)
  public void reportCurrentTime() {
    Collection<String> cacheNames = cacheManager.getCacheNames();
    cacheNames.forEach(this::getCacheAndClear);
  }

  private void getCacheAndClear(final String cacheName) {

    final Cache cache = cacheManager.getCache(cacheName);
    if (cache == null) {
      throw new IllegalArgumentException("invalid cache name: " + cacheName);
    }
    cache.clear();
  }
}
