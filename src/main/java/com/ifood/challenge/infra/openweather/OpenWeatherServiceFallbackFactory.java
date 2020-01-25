package com.ifood.challenge.infra.openweather;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
class OpenWeatherServiceFallbackFactory implements FallbackFactory<OpenWeatherWeatherApi> {

  @Override
  public OpenWeatherWeatherApi create(Throwable throwable) {
    return new OpenWeatherWeatherServiceFallback((throwable));
  }
}
