package de.hartz.software.sodevsalaryguide.application.http.api;

import de.hartz.software.sodevsalaryguide.adapter.persistence.PersistenceConfiguration;
import jakarta.annotation.PostConstruct;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Import({
  // TODO: This autofconfiguration is not considered neither with annotation nor with one of the
  // factoy files.. Or is that added duplicated so leading to no bean for entitymanagerFactory?
  PersistenceConfiguration.class,
  HttpApiConfiguration.class
}) // TODO: do we need it? Probably not..
public class WebuiApplication {

  @Autowired Environment env;

  public static void main(String[] args) {
    val config = SpringApplication.run(WebuiApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void doSomethingAfterStartup() {}

  @PostConstruct
  public void init() {
    // start your monitoring in here
  }
}
