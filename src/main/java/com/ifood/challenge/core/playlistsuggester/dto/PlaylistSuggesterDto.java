package com.ifood.challenge.core.playlistsuggester.dto;

/**
 * The class Playlist suggester dto.
 * Created at 14 de jan de 2020 23:41:28
 */
public class PlaylistSuggesterDto {
  /**
   * The City name.
   */
  /* the idea would be create a class for these properties that would be shared by all domains.
   * These classes would be like a ValueObject (validate inputs, etc)
   *  */
  private String cityName;
  /**
   * The City longitude.
   */
  private Double cityLongitude;
  /**
   * The City latitude.
   */
  private Double cityLatitude;

  /**
   * Builder playlist suggester dto creator.
   * Created at 14 de jan de 2020 23:41:28
   *
   * @return the playlist suggester dto creator
   */
  public static PlaylistSuggesterDtoCreator builder() {
    return new PlaylistSuggesterDtoCreator();
  }

  /**
   * Gets city name.
   * Created at 14 de jan de 2020 23:41:28
   *
   * @return the city name
   */
  public String getCityName() {
    return this.cityName;
  }

  /**
   * Gets latitude.
   * Created at 14 de jan de 2020 23:41:28
   *
   * @return the latitude
   */
  public Double getLatitude() {
    return this.cityLatitude;
  }

  /**
   * Gets longitude.
   * Created at 14 de jan de 2020 23:41:28
   *
   * @return the longitude
   */
  public Double getLongitude() {
    return this.cityLongitude;
  }

  /**
   * The class Playlist suggester dto creator.
   * Created at 14 de jan de 2020 23:41:28
   */
  public static class PlaylistSuggesterDtoCreator {
    /**
     * The Playlist suggester dto.
     */
    private PlaylistSuggesterDto playlistSuggesterDto;

    /**
     * Instantiates a new Playlist suggester dto creator.
     * Created on 14 de jan de 2020 23:41:28
     */
    protected PlaylistSuggesterDtoCreator() {
      this.playlistSuggesterDto = new PlaylistSuggesterDto();
    }

    /**
     * Sets city name.
     * Created at 14 de jan de 2020 23:41:28
     *
     * @param cityName the city name
     * @return the city name
     */
    public PlaylistSuggesterDtoCreator setCityName(String cityName) {
      this.playlistSuggesterDto.cityName = cityName;
      return this;
    }

    /**
     * Build playlist suggester dto.
     * Created at 14 de jan de 2020 23:41:28
     *
     * @return the playlist suggester dto
     */
    public PlaylistSuggesterDto build() {
      return this.playlistSuggesterDto;
    }

    /**
     * Sets city coordinates.
     * Created at 14 de jan de 2020 23:41:28
     *
     * @param lat the lat
     * @param lon the lon
     * @return the city coordinates
     */
    public PlaylistSuggesterDtoCreator setCityCoordinates(double lat, double lon) {
      this.playlistSuggesterDto.cityLatitude = lat;
      this.playlistSuggesterDto.cityLongitude = lon;
      return this;
    }
  }
}
