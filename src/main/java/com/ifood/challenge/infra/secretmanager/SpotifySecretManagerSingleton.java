package com.ifood.challenge.infra.secretmanager;

import org.apache.commons.lang.StringUtils;

/**
 * The class Spotify secret manager singleton.
 * Created at 24 de jan de 2020 23:01:26
 */
public class SpotifySecretManagerSingleton {
  /**
   * The constant spotifySecretManagerSingleton.
   */
  private static volatile SpotifySecretManagerSingleton spotifySecretManagerSingleton;
  /**
   * The constant aux.
   */
  private static Object aux = new Object();
  /**
   * The My current token.
   */
  private StringBuffer myCurrentToken = new StringBuffer("xxxxx");

  /**
   * Instantiates a new Spotify secret manager singleton.
   * Created on 24 de jan de 2020 23:01:26
   */
  private SpotifySecretManagerSingleton() {
  }

  /**
   * Gets instance.
   * Created at 24 de jan de 2020 23:01:27
   *
   * @return the instance
   */
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

  /**
   * Change token.
   * Created at 24 de jan de 2020 23:01:27
   *
   * @param newToken the new token
   */
  public void changeToken(String newToken) {
    String oldToken = myCurrentToken.toString();
    if (StringUtils.isNotBlank(oldToken)) {
      myCurrentToken.replace(0, oldToken.length(), newToken);
    } else {
      myCurrentToken.append(newToken);
    }
  }

  /**
   * Gets current token.
   * Created at 24 de jan de 2020 23:01:27
   *
   * @return the current token
   */
  public String getCurrentToken() {
    return myCurrentToken.toString();
  }


}
