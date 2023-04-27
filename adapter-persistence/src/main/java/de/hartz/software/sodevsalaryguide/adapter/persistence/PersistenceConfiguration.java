package de.hartz.software.sodevsalaryguide.adapter.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
@ComponentScan
@EnableJpaRepositories(
    basePackages = {"de.hartz.software.sodevsalaryguide.adapter.persistence.repo"})
@EntityScan({"de.hartz.software.sodevsalaryguide.adapter.persistence.model"})
// @PropertySource(value = "classpath:application.properties")
public class PersistenceConfiguration {

  // https://stackoverflow.com/a/54663039/8524651
  @Bean(name = "entityManagerFactory")
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

    return sessionFactory;
  }
}
