package com.ifood.challenge.core.playlistsuggester.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlaylistSuggestionByCityCoordinatesNotFound extends Exception {
  public static final String MESSAGE = "Playlist suggestion Not Found For City Coordinates: ";

  public PlaylistSuggestionByCityCoordinatesNotFound(Double latitude, Double longitude) {
    super(MESSAGE.concat("Latitude: " + latitude + " - Longitude: " + longitude));
  }
}
