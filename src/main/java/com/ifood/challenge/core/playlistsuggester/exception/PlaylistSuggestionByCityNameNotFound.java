package com.ifood.challenge.core.playlistsuggester.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlaylistSuggestionByCityNameNotFound extends Exception {
  public static final String MESSAGE = "Playlist suggestion Not Found For City Name: ";

  public PlaylistSuggestionByCityNameNotFound(String cityName) {
    super(MESSAGE.concat(cityName));
  }
}
