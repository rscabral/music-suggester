package com.ifood.challenge.core.music.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MusicPlaylistByGenreNotFound extends Exception {
  public static final String MESSAGE = "Music Not Found For Genre: ";

  public MusicPlaylistByGenreNotFound(String genre) {
    super(MESSAGE.concat(genre));
  }
}
