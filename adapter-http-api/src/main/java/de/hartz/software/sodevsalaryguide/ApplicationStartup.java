package de.hartz.software.sodevsalaryguide;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup 
implements ApplicationListener<ApplicationReadyEvent> {

  @Autowired Environment env;
  /**
   * This event is executed as late as conceivably possible to indicate that 
   * the application is ready to service requests.
   */
  @Override
  public void onApplicationEvent(final ApplicationReadyEvent event) {

    System.err.println(System.getProperty("test"));
    System.err.println("htessstt");

    return;
  }
}