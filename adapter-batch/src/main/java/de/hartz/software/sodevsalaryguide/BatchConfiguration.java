package de.hartz.software.sodevsalaryguide;

import de.hartz.software.sodevsalaryguide.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.model.raw.RawRow;
import de.hartz.software.sodevsalaryguide.services.InputProcessor;
import de.hartz.software.sodevsalaryguide.services.InputReader;
import de.hartz.software.sodevsalaryguide.services.OutputWriter;
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

@Import({RabbitMQConfig.class, PersistenceConfiguration.class})
@ComponentScan
@Configuration
public class BatchConfiguration {

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
