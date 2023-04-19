package de.hartz.software.sodevsalaryguide;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@ComponentScan(basePackages = "de.hartz.software.sodevsalaryguide.endpoints")
@Configuration
public class HttpApiConfiguration {

  // https://stackoverflow.com/a/54663039/8524651
  @Bean(name = "entityManagerFactory")
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

    return sessionFactory;
  }
}
