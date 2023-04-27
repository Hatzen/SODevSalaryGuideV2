package de.hartz.software.sodevsalaryguide.application.http.api;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan(basePackages = "de.hartz.software.sodevsalaryguide.application.http.api.endpoints")
@Configuration
public class HttpApiConfiguration {}
