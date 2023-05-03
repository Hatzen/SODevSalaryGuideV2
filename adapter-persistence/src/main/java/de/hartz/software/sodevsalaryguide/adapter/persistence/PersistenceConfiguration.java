package de.hartz.software.sodevsalaryguide.adapter.persistence;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@AutoConfiguration
@EnableAutoConfiguration // for hibernate autoconfiguration needed?
@EnableJpaRepositories(
    basePackages = {"de.hartz.software.sodevsalaryguide.adapter.persistence.repo"}
    // ,entityManagerFactoryRef = "entityManager"  LocalContainerEntityManagerFactoryBean
    // ,entityManagerFactoryRef = "localContainerEntityManagerFactoryBean"
    ,
    entityManagerFactoryRef = "entityManagerFactory")
@EntityScan({"de.hartz.software.sodevsalaryguide.adapter.persistence.model"})
// @PropertySource(value = "classpath:application.properties")
public class PersistenceConfiguration {

  // TODO: Leading to datasource props not found, even in persistence tests, probably we have to
  // pass these params here..
  // https://stackoverflow.com/a/54663039/8524651
  // @ConditionalOnMissingBean
  // @Bean(name = "entityManagerFactory")
  // public LocalSessionFactoryBean sessionFactory() {
  //  LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
  //  return sessionFactory;
  // }

  /*
  // https://stackoverflow.com/a/48417848/8524651
  private static String PROP_DB_DRIVER_CLASS = "spring.datasource.driver-class-name";
  private static String PROP_DB_URL = "spring.datasource.url";
  private static String PROP_DB_USER = "spring.datasource.username";
  private static String PROP_DB_PASS = "spring.datasource.password";

  @Autowired private Environment env;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty(PROP_DB_DRIVER_CLASS));
    dataSource.setUrl(env.getProperty(PROP_DB_URL));
    dataSource.setUsername(env.getProperty(PROP_DB_USER));
    dataSource.setPassword(env.getProperty(PROP_DB_PASS));
    return dataSource;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
    em.setDataSource(dataSource());
    em.setPackagesToScan("de.hartz.software.sodevsalaryguide.adapter.persistence");

    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    em.setJpaVendorAdapter(vendorAdapter);
    // em.setJpaProperties(additionalProperties());

    return em;
  }
     */
}
