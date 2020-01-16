package com.ifood.challenge.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
class StartupListener {
  private static final Logger log = LoggerFactory.getLogger(StartupListener.class);
  @Autowired
  private Environment environment;

  @EventListener
  public void onApplicationEvent(ContextRefreshedEvent event) {
    logCurrentProfile();
  }

  private void logCurrentProfile() {
    for (String profileName : environment.getActiveProfiles()) {
      log.info("###### Currently active profile: {} ", profileName);
    }
  }
}
