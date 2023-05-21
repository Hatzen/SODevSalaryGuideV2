package de.hartz.software.sodevsalaryguide.application.batch.worker.intake;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;
import de.hartz.software.sodevsalaryguide.core.port.repo.EvaluatedDataReadRepo;
import de.hartz.software.sodevsalaryguide.core.port.service.AMQPSendService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@AutoConfigureWebClient // https://stackoverflow.com/a/43131830/8524651
@SpringBootTest
@SpringBatchTest
// @ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BatchTestConfig.class})
@ActiveProfiles({"persistence-test", "batch-test", "amqp-test"})
public class BatchComponentTest {

  @Autowired private AMQPSendService amqpService;
  @Autowired private JobLauncherTestUtils jobLauncherTestUtils;
  @Autowired private EvaluatedDataReadRepo evaluatedDataReadRepo;

  @Test
  public void providingChunksAsync_runningBatch_processesAllData() throws Exception {
    setupDummyMessages();

    JobExecution jobExecution = jobLauncherTestUtils.launchJob();

    assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
    assertEquals(1234, getEntryCount());
  }

  @Test
  public void noAmqpInput_runningBatch_successfullyFinishes() throws Exception {
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();

    assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
    assertEquals(0, getEntryCount());
  }

  private long getEntryCount() {
    return evaluatedDataReadRepo.getAllSurveyEntries().size();
  }

  private void setupDummyMessages() {
    val testName1 = new RawDataSetName("2011-chunk-1", 2011);
    amqpService.queueDatasetName(testName1);

    new Thread(
            () -> {
              try {
                Thread.sleep(5000L);
                val testName2 = new RawDataSetName("2012-chunk-1", 2012);
                amqpService.queueDatasetName(testName2);
                amqpService.setQueueFinished();
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            })
        .start();
  }
}
