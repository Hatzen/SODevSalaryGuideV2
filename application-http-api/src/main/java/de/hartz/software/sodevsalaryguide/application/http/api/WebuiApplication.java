package de.hartz.software.sodevsalaryguide.application.http.api;

import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@Import({HttpApiConfiguration.class})
public class WebuiApplication {

    public static void main(String[] args) {
        val config = SpringApplication.run(WebuiApplication.class, args);
    }
}
