package com.ifood.challenge.infra.openweather;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
class OpenWeatherServiceFallbackFactory implements FallbackFactory<OpenWeatherWeatherProxy> {
  private final Logger log = LoggerFactory
      .getLogger(OpenWeatherServiceFallbackFactory.class);
  private Throwable cause;

  @Override
  public OpenWeatherWeatherProxy create(Throwable throwable) {
    return new OpenWeatherWeatherServiceFallback((throwable));
  }
}
