package de.hartz.software.sodevsalaryguide;

import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
  // https://stackoverflow.com/a/48264995/8524651
  ServletWebServerFactoryAutoConfiguration.class,
  HttpApiConfiguration.class,
  PersistenceConfiguration.class,
  // de.hartz.software.sodevsalaryguide.PersistenceTestConfig.class
})
public class HttpApiTestConfiguration {}
