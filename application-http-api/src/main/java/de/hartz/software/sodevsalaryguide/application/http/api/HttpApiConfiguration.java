package de.hartz.software.sodevsalaryguide.application.http.api;

import de.hartz.software.sodevsalaryguide.adapater.amqp.RabbitMQConfig;
import de.hartz.software.sodevsalaryguide.adapter.frontend.FrontendConfig;
import de.hartz.software.sodevsalaryguide.adapter.persistence.PersistenceConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@EnableWebMvc
@Configuration
@ComponentScan
// TODO: Why is the autoconfiguration not detected??
@Import({PersistenceConfiguration.class, FrontendConfig.class, RabbitMQConfig.class})
public class HttpApiConfiguration {}
