package de.hartz.software.sodevsalaryguide.application.http.api;

import jakarta.annotation.PostConstruct;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class WebuiApplication {

  @Autowired Environment env;

  public static void main(String[] args) {
    val config = SpringApplication.run(WebuiApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void doSomethingAfterStartup() {
  }

  @PostConstruct
  public void init() {
    // start your monitoring in here
  }
}
