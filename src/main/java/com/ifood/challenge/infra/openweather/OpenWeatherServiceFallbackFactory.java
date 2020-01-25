package com.ifood.challenge.infra.openweather;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class OpenWeatherServiceFallbackFactory implements FallbackFactory<OpenWeatherWeatherApi> {
  private final Logger log = LoggerFactory
      .getLogger(OpenWeatherServiceFallbackFactory.class);
  private Throwable cause;

  @Override
  public OpenWeatherWeatherApi create(Throwable throwable) {
    return new OpenWeatherWeatherServiceFallback((throwable));
  }
}
