package com.ifood.challenge.infra.secretmanager;

import org.apache.commons.lang.StringUtils;

public class SpotifySecretManagerSingleton {
  private static volatile SpotifySecretManagerSingleton spotifySecretManagerSingleton;
  private static Object aux = new Object();
  private StringBuffer myCurrentToken = new StringBuffer("xxxxx");

  private SpotifySecretManagerSingleton() {
  }

  public static SpotifySecretManagerSingleton getInstance() {
    /* result is to improve the performance of our code
     * In cases where the instance is already initialized (most of the time)
     *, the volatile field is only accessed once
     *(due to “return result;” instead of “return instance;”)*/
    SpotifySecretManagerSingleton result = spotifySecretManagerSingleton;
    if (result == null) {
      synchronized (aux) {
        result = spotifySecretManagerSingleton;
        if (result == null) {
          spotifySecretManagerSingleton = result = new SpotifySecretManagerSingleton();
        }
      }
    }
    return result;
  }

  public void changeToken(String newToken) {
    String oldToken = myCurrentToken.toString();
    if (StringUtils.isNotBlank(oldToken)) {
      myCurrentToken.replace(0, oldToken.length(), newToken);
    } else {
      myCurrentToken.append(newToken);
    }
  }

  public String getCurrentToken() {
    return myCurrentToken.toString();
  }


}
