package de.hartz.software.sodevsalaryguide.application.batch.worker.intake;

import de.hartz.software.sodevsalaryguide.adapater.amqp.RabbitMQConfig;
import de.hartz.software.sodevsalaryguide.adapter.persistence.PersistenceConfiguration;
import de.hartz.software.sodevsalaryguide.application.batch.worker.intake.services.InputProcessor;
import de.hartz.software.sodevsalaryguide.application.batch.worker.intake.services.InputReader;
import de.hartz.software.sodevsalaryguide.application.batch.worker.intake.services.OutputWriter;
import de.hartz.software.sodevsalaryguide.core.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawRow;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

// TODO: looks like autoconfiguration PersistenceConfiguration not loaded..
@Import({RabbitMQConfig.class, PersistenceConfiguration.class})
@ComponentScan
@Configuration
public class BatchConfiguration {
  // TODO: Maybe use extended features:
  // https://docs.spring.io/spring-batch/docs/current/reference/html/job.html#advancedMetaData

  private static final int CHUNK_SIZE = 1000;

  @Autowired private InputReader inputReader;
  @Autowired private InputProcessor inputProcessor;
  @Autowired private OutputWriter outputWriter;

  @Bean
  public Job importUserJob(
      JobRepository jobRepository,
      // JobCompletionNotificationListener listener,
      Step processRawDataStep) {
    return new JobBuilder("importUserJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        // .listener(listener)
        .flow(processRawDataStep)
        .end()
        .build();
  }

  @Bean
  public Step processRawDataStep(
      JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("processRawDataStep", jobRepository)
        .<RawRow, SurveyEntry>chunk(CHUNK_SIZE, transactionManager)
        // TODO
        // .faultTolerant()
        // .allowStartIfComplete()
        // .exceptionHandler()
        .reader(inputReader)
        .processor(inputProcessor)
        .writer(outputWriter)
        .build();
  }
}
