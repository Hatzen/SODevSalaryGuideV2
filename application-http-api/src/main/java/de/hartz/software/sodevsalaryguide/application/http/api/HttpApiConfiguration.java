package de.hartz.software.sodevsalaryguide.application.http.api;

import de.hartz.software.sodevsalaryguide.adapter.frontend.FrontendConfig;
import de.hartz.software.sodevsalaryguide.adapter.persistence.PersistenceConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@AutoConfiguration
@EnableAutoConfiguration
// @ImportAutoConfiguration
@EnableWebMvc
// https://stackoverflow.com/questions/54382641/spring-boot-multi-module-with-data-jpa-not-working
// @ComponentScan(basePackages = "de.hartz.software.sodevsalaryguide")
@Configuration
// TODO: why we need this import to make /app/ accessable, autoconfig doesnt wokr??
@Import({FrontendConfig.class, PersistenceConfiguration.class})
public class HttpApiConfiguration {}
