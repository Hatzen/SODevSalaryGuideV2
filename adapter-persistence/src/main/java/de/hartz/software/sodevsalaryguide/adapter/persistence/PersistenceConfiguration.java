package de.hartz.software.sodevsalaryguide.adapter.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
// @EnableAutoConfiguration // replaced by factory method
@EnableJpaRepositories(
    basePackages = {"de.hartz.software.sodevsalaryguide.adapter.persistence.repo"})
@EntityScan({"de.hartz.software.sodevsalaryguide.adapter.persistence.model"})
// @Import(HibernateJpaAutoConfiguration.class) // TODO: leads to test errors
// Caused by: org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of
// type 'jakarta.persistence.EntityManagerFactory' available

@PropertySource(value = "classpath:application.properties")
public class PersistenceConfiguration {

  // TODO: why is it not obtained directly?
  /*@Bean
  public EntityManagerFactory entityManagerFactory(
      LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
    return localContainerEntityManagerFactoryBean.getObject();
  }*/

  /*
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.yummynoodlebar.persistence.domain");
    factory.setDataSource(dataSource());
    factory.afterPropertiesSet();


    return factory.getObject();
    }
  public EntityManagerFactory entityManagerFactory(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
      return localContainerEntityManagerFactoryBean;
  }
  public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean() {
      return
  }*/

  // https://stackoverflow.com/q/25185023/8524651
  /*
  @Bean
  public EntityManagerFactory entityManagerFactory(
      LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean)
      throws SQLException {


      retrun localContainerEntityManagerFactoryBean.getObject();
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.yummynoodlebar.persistence.domain");
    factory.setDataSource(dataSource());
    factory.afterPropertiesSet();


    return factory.getObject();
  }

    @Bean
    public DataSource dataSource() throws SQLException {

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2).build();
    }

  @Bean
  public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
    return entityManagerFactory.createEntityManager();
  }

  @Bean
  public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
    final JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(emf);
    transactionManager.setDataSource(dataSource());
    transactionManager.setJpaDialect(jpaDialect());
    return transactionManager;
  }

  @Bean
  public PlatformTransactionManager transactionManager() throws SQLException {

    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory());
    return txManager;
  }

  @Bean
  public HibernateExceptionTranslator hibernateExceptionTranslator() {
    return new HibernateExceptionTranslator();
  }
   */
}
