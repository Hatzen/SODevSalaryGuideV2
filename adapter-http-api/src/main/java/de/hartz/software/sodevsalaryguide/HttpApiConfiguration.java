package de.hartz.software.sodevsalaryguide;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = "de.hartz.software.sodevsalaryguide.endpoints")
@Configuration
public class HttpApiConfiguration {}
