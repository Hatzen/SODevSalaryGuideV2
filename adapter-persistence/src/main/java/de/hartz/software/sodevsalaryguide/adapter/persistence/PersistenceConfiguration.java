package de.hartz.software.sodevsalaryguide.adapter.persistence;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@AutoConfiguration
@EnableAutoConfiguration // for hibernate autoconfiguration needed?
@ComponentScan
@EnableJpaRepositories(
    basePackages = {"de.hartz.software.sodevsalaryguide.adapter.persistence.repo"})
@EntityScan({"de.hartz.software.sodevsalaryguide.adapter.persistence.model"})
public class PersistenceConfiguration {}
