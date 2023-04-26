package de.hartz.software.sodevsalaryguide.application.http.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = "de.hartz.software.sodevsalaryguide.application.http.api.endpoints")
@Configuration
public class HttpApiConfiguration {

  @Autowired Environment env;

  @Bean
  Object getTestClass() {
    System.err.println(env.getProperty("test"));
    System.err.println(env.getProperty("test"));
    System.err.println(env.getProperty("test"));
    System.err.println(env.getProperty("test"));
    System.err.println(env.getProperty("test"));
    System.err.println(env.getProperty("test"));
    System.err.println(env.getProperty("test"));
    System.err.println(env.getProperty("test"));
    System.err.println(env.getProperty("test"));
    System.err.println(env.getProperty("test"));

    return null;
  }

  // https://stackoverflow.com/a/54663039/8524651
  @Bean(name = "entityManagerFactory")
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

    return sessionFactory;
  }
}
