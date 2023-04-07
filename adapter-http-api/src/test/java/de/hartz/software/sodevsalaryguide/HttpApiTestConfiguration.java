package de.hartz.software.sodevsalaryguide;

import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
  // https://stackoverflow.com/a/48264995/8524651
  ServletWebServerFactoryAutoConfiguration.class,
  HibernateJpaAutoConfiguration.class,
  HttpApiConfiguration.class,
  PersistenceConfiguration.class
})
public class HttpApiTestConfiguration {}
