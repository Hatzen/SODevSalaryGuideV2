package de.hartz.software.sodevsalaryguide.application.batch.worker.intake;

import javax.sql.DataSource;
import org.springframework.batch.core.configuration.BatchConfigurationException;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

// https://stackoverflow.com/a/46964761/8524651
// @Component
public class TablelessBatchConfigurer extends DefaultBatchConfiguration {
  private PlatformTransactionManager transactionManager;
  private JobRepository jobRepository;
  private JobLauncher jobLauncher;
  private JobExplorer jobExplorer;
  private DataSource dataSource;

  @Autowired
  public TablelessBatchConfigurer(DataSource dataSource) {
    this.dataSource = dataSource;
    this.transactionManager = new DataSourceTransactionManager(this.dataSource);

    try {
      /*
      final MapJobRepositoryFactoryBean jobRepositoryFactory =
          new MapJobRepositoryFactoryBean(this.transactionManager);
      jobRepositoryFactory.afterPropertiesSet();
      this.jobRepository = jobRepositoryFactory.getObject();

      final MapJobExplorerFactoryBean jobExplorerFactory =
          new MapJobExplorerFactoryBean(jobRepositoryFactory);
      jobExplorerFactory.afterPropertiesSet();
      this.jobExplorer = jobExplorerFactory.getObject();

      final SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
      simpleJobLauncher.setJobRepository(this.jobRepository);
      simpleJobLauncher.afterPropertiesSet();
      this.jobLauncher = simpleJobLauncher;

         */
    } catch (Exception e) {
      throw new BatchConfigurationException(e);
    }
  }
  // ... override getters
}
