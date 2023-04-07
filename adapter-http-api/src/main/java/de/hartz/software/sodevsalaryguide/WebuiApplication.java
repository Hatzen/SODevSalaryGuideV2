package de.hartz.software.sodevsalaryguide;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WebuiApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    val config = SpringApplication.run(WebuiApplication.class, args);
  }

  /*
  @Bean
  public ServletWebServerFactory servletWebServerFactory() {
    return new TomcatServletWebServerFactory();
  }
   */
}
