package de.hartz.software.sodevsalaryguide.application.batch.worker.intake;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// https://dzone.com/articles/batch-processing-with-spring-batch-and-amqp-easier
// https://www.baeldung.com/spring-batch-tasklet-chunk
// https://spring.io/blog/2021/01/27/spring-batch-on-kubernetes-efficient-batch-processing-at-scale
@SpringBootApplication
@EnableDiscoveryClient
public class InputCsvWorkerBatchApplication {

    public static void main(String[] args) {
        val application = SpringApplication.run(InputCsvWorkerBatchApplication.class, args);
        val springExit = SpringApplication.exit(application);
        System.exit(springExit);
    }
}
