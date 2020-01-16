package com.ifood.challenge.core.cityweather;

import com.ifood.challenge.core.cityweather.dto.CityWeatherDto;
import com.ifood.challenge.infra.openweather.OpenWeatherDto;
import com.ifood.challenge.infra.openweather.OpenWeatherWeatherProxy;
import com.ifood.challenge.shared.EnvProfiles;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Function;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("!" + EnvProfiles.DEFAULT)
@Service
class OpenWeatherCityWeatherRepository implements ICityWeatherRepository {
  private final Logger log = LoggerFactory.getLogger(OpenWeatherCityWeatherRepository.class);

  @Value("${open.weather.api.key.value}")
  private String apiKey;

  @Autowired
  private OpenWeatherWeatherProxy openWeatherWeatherProxy;

  private Function<Double, Integer> kelvinToIntegerCelsius = (k) -> (int) (k - 273.15);

  @Override
  public Optional<CityWeatherDto> findByCityName(String cityName) {
    return executeAsync(() -> openWeatherWeatherProxy.findByCityName(cityName, apiKey));
  }

  @Override
  public Optional<CityWeatherDto> findByCityCoordinates(Double lat, Double lon) {
    return executeAsync(() -> openWeatherWeatherProxy.findByCityCoordinates(lat, lon, apiKey));
  }

  @NotNull
  private Optional<CityWeatherDto> executeAsync(Supplier<OpenWeatherDto> executor) {
    try {
      final CompletableFuture<OpenWeatherDto> completableFuture =
          CompletableFuture
              .supplyAsync(executor);
      // Thread free to do other tasks...
      final OpenWeatherDto openWeatherDto = completableFuture.join();
      return Optional
          .ofNullable(OpenWeatherCityWeatherMapper.mapFromOriginToInternalDto(openWeatherDto));
    } catch (CompletionException e) {
      log.error("Error: {}", e.getCause().getMessage());
    } catch (CancellationException e) {
      log.error("Async operation cancelled.");
    }
    return Optional.empty();
  }
}
