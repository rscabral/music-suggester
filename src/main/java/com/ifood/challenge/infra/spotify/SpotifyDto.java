package com.ifood.challenge.infra.spotify;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpotifyDto {

  @JsonProperty("tracks")
  private SpotifyTracks tracks;
  @JsonIgnore
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  @JsonProperty("tracks")
  public SpotifyTracks getTracks() {
    return tracks;
  }

  @JsonProperty("tracks")
  public void setTracks(SpotifyTracks tracks) {
    this.tracks = tracks;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  @JsonAnySetter
  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

  public boolean hasMusics() {
    return this.tracks != null && this.tracks.hasMusics();
  }
}