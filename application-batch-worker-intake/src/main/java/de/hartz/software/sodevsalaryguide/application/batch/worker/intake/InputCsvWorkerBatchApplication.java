package de.hartz.software.sodevsalaryguide.application.batch.worker.intake;

import jakarta.annotation.PostConstruct;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;

// https://dzone.com/articles/batch-processing-with-spring-batch-and-amqp-easier
// https://www.baeldung.com/spring-batch-tasklet-chunk
// https://spring.io/blog/2021/01/27/spring-batch-on-kubernetes-efficient-batch-processing-at-scale
@SpringBootApplication
// @Import(BatchConfiguration.class)
public class InputCsvWorkerBatchApplication {

  @Autowired Environment env;

  public static void main(String[] args) {
    System.exit(
        SpringApplication.exit(SpringApplication.run(InputCsvWorkerBatchApplication.class, args)));
  }

  @EventListener(ApplicationReadyEvent.class)
  public void doSomethingAfterStartup() {
    val test = env.getProperty("spring.batch.jdbc.initialize-schema");
    System.err.println("dummy" + test);
  }

  @PostConstruct
  public void init() {
    var test = env.getProperty("spring.batch.initialize-schema");
    System.err.println("dummy1" + test);

    test = env.getProperty("spring.liquibase.url");
    System.err.println("dummy2" + test);
    // start your monitoring in here
  }
}
