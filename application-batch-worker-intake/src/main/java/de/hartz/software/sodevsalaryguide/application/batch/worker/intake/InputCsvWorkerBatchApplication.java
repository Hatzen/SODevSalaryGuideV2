package de.hartz.software.sodevsalaryguide.application.batch.worker.intake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

// https://dzone.com/articles/batch-processing-with-spring-batch-and-amqp-easier
// https://www.baeldung.com/spring-batch-tasklet-chunk
// https://spring.io/blog/2021/01/27/spring-batch-on-kubernetes-efficient-batch-processing-at-scale
@SpringBootApplication
@Import(BatchConfiguration.class)
public class InputCsvWorkerBatchApplication {

  public static void main(String[] args) {
    System.exit(
        SpringApplication.exit(SpringApplication.run(InputCsvWorkerBatchApplication.class, args)));
  }
}
