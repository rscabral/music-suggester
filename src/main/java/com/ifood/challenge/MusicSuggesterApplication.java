package com.ifood.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The class Music suggester application.
 * Created at 12 de jan de 2020 19:40:13
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.ifood.challenge.*"})
@EnableFeignClients
@EnableCircuitBreaker
@EnableCaching
@EnableScheduling
public class MusicSuggesterApplication {

  /**
   * The entry point of application.
   * Created at 12 de jan de 2020 19:40:13
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(MusicSuggesterApplication.class, args);
  }

}