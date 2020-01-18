package com.ifood.challenge.core.playlistsuggester;

import com.ifood.challenge.shared.ruleengine.Result;

class WeatherMusicGenreResult implements Result<String> {
  private String value;

  public WeatherMusicGenreResult(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
