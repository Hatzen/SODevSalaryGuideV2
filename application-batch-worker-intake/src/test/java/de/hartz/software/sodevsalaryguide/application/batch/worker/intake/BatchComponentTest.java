package de.hartz.software.sodevsalaryguide.application.batch.worker.intake;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.http.Body;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import de.hartz.software.sodevsalaryguide.application.batch.worker.intake.services.DataRestClient;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;
import de.hartz.software.sodevsalaryguide.core.port.repo.ComputationRepo;
import de.hartz.software.sodevsalaryguide.core.port.repo.EvaluatedDataReadRepo;
import de.hartz.software.sodevsalaryguide.core.port.repo.RawDataRepo;
import de.hartz.software.sodevsalaryguide.core.port.service.AMQPSendService;
import java.io.IOException;
import lombok.val;
import org.junit.Rule;
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
  // TODO: dont use rule, but use WireMockTest??
  @Rule public WireMockRule wireMockRule = new WireMockRule(8080);
  @Autowired private AMQPSendService amqpService;
  @Autowired private JobLauncherTestUtils jobLauncherTestUtils;
  @Autowired private RawDataRepo rawDataRepo;
  @Autowired private EvaluatedDataReadRepo evaluatedDataReadRepo;
  @Autowired private ComputationRepo computationCrudRepo;

  @Test
  public void providingChunksAsync_runningBatch_processesAllData() throws Exception {
    setupMockAmqpWithData();
    setupMockDataService();

    JobExecution jobExecution = jobLauncherTestUtils.launchJob();

    assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
    assertEquals(2814, getRawEntryCount());
    assertEquals(2814, getEntryCount());
    assertEquals(2, getComputations());
  }

  @Test
  public void noAmqpInput_runningBatch_successfullyFinishes() throws Exception {
    JobExecution jobExecution = jobLauncherTestUtils.launchJob();

    assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
    assertEquals(0, getEntryCount());
  }

  private void setupMockDataService() throws IOException {
    wireMockRule.start();
    stubFile(TEST_FILE_1_NAME);
    stubFile(TEST_FILE_2_NAME);
  }

  private void stubFile(String fileName) throws IOException {
    ClassLoader classLoader = this.getClass().getClassLoader();
    wireMockRule.stubFor(
        get(DataRestClient.PATH + fileName)
            .willReturn(
                ResponseDefinitionBuilder.responseDefinition()
                    .withResponseBody(
                        new Body(
                            classLoader
                                .getResourceAsStream("csvchunks/" + fileName + ".csv")
                                .readAllBytes()))));
  }

  private long getComputations() {
    return computationCrudRepo.getAll().size();
  }

  private long getEntryCount() {
    return evaluatedDataReadRepo.getAllSurveyEntries().size();
  }

  private long getRawEntryCount() {
    return rawDataRepo.getAll().size();
  }

  private void setupMockAmqpWithData() {
    // TODO: Maybe it is better to send from same thread?
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
