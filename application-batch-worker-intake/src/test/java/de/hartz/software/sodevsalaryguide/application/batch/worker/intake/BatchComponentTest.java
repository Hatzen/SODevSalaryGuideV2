package de.hartz.software.sodevsalaryguide.application.batch.worker.intake;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.http.Body;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import de.hartz.software.sodevsalaryguide.application.batch.worker.intake.services.DataRestClient;
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
@WireMockTest
@ContextConfiguration(classes = {BatchTestConfig.class})
@ActiveProfiles({"persistence-test", "batch-test", "amqp-test"})
public class BatchComponentTest {

  private static final String TEST_FILE_1_NAME = "2011-chunk-1";
  private static final String TEST_FILE_2_NAME = "2012-chunk-1";
  @Autowired private AMQPSendService amqpService;
  @Autowired private JobLauncherTestUtils jobLauncherTestUtils;
  @Autowired private EvaluatedDataReadRepo evaluatedDataReadRepo;

  @Test
  public void providingChunksAsync_runningBatch_processesAllData() throws Exception {
    setupDummyMessages();
    setupDummyService();

    JobExecution jobExecution = jobLauncherTestUtils.launchJob();

    assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
    assertEquals(1234, getEntryCount());
  }

  private void setupDummyService() {
    ClassLoader classLoader = this.getClass().getClassLoader();
    stubFor(
        get(DataRestClient.HOST + DataRestClient.PATH + "/" + TEST_FILE_1_NAME)
            .willReturn(
                ResponseDefinitionBuilder.responseDefinition()
                    // .withBodyFile("")
                    .withResponseBody(
                        new Body(
                            classLoader
                                .getResource("csvchunks/" + TEST_FILE_1_NAME + ".csv")
                                .getFile()
                                .getBytes()))));
    stubFor(
        get(DataRestClient.HOST + DataRestClient.PATH + "/" + TEST_FILE_2_NAME)
            .willReturn(
                ResponseDefinitionBuilder.responseDefinition()
                    // .withBodyFile("")
                    .withResponseBody(
                        new Body(
                            classLoader
                                .getResource("csvchunks/" + TEST_FILE_2_NAME + ".csv")
                                .getFile()
                                .getBytes()))));
    /*
    // TODO: upgrade to wiremock 3?
    // https://wiremock.org/3.x/docs/junit-jupiter/
    stubFor(
        get(DataRestClient.HOST + DataRestClient.PATH + "/" + TEST_FILE_1_NAME)
            .willReturn(
                ResponseDefinitionBuilder.responseDefinition()
                    // .withBodyFile("")
                    .withResponseBody(
                        new Body(
                            classLoader
                                .getResource("csvchunks/" + TEST_FILE_1_NAME + ".csv")
                                .getFile()
                                .getBytes()))));
    stubFor(
        get(DataRestClient.HOST + DataRestClient.PATH + "/" + TEST_FILE_2_NAME)
            .willReturn(
                ResponseDefinitionBuilder.responseDefinition()
                    // .withBodyFile("")
                    .withResponseBody(
                        new Body(
                            classLoader
                                .getResource("csvchunks/" + TEST_FILE_2_NAME + ".csv")
                                .getFile()
                                .getBytes()))));

     */
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
    val testName1 = new RawDataSetName(TEST_FILE_1_NAME, 2011);
    amqpService.queueDatasetName(testName1);

    new Thread(
            () -> {
              try {
                Thread.sleep(5000L);
                val testName2 = new RawDataSetName(TEST_FILE_2_NAME, 2012);
                amqpService.queueDatasetName(testName2);
                amqpService.setQueueFinished();
              } catch (InterruptedException e) {
                throw new RuntimeException(e);
              }
            })
        .start();
  }
}
