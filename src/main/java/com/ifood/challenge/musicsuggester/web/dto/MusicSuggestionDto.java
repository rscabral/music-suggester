package com.ifood.challenge.musicsuggester.web.dto;

import org.springframework.hateoas.RepresentationModel;

/**
 * The class Music suggestion dto.
 * Created at 12 de jan de 2020 18:45:19
 */
public class MusicSuggestionDto extends RepresentationModel<MusicSuggestionDto> {
  /**
   * The Text.
   */
  private String text;

  /**
   * Gets text.
   * Created at 12 de jan de 2020 18:45:19
   *
   * @return the text
   */
  public String getText() {
    return text;
  }

  /**
   * Sets text.
   * Created at 12 de jan de 2020 18:45:19
   *
   * @param text the text
   */
  public void setText(String text) {
    this.text = text;
  }
}
