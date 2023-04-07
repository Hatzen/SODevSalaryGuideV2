package de.hartz.software.sodevsalaryguide;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebuiApplication {

  public static void main(String[] args) {
    val config = SpringApplication.run(WebuiApplication.class, args);
  }
}
