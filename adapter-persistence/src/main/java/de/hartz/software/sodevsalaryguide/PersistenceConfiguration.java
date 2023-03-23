package de.hartz.software.sodevsalaryguide;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration
@EnableJpaAuditing
@Configuration
@ComponentScan
@EnableJpaRepositories(basePackages = {"de.hartz.software.sodevsalaryguide.repo"})
@EntityScan({"de.hartz.software.sodevsalaryguide.model"})
public class PersistenceConfiguration {}
