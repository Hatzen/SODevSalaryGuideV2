package de.hartz.software.sodevsalaryguide.application.batch.worker.intake;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.http.Body;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import de.hartz.software.sodevsalaryguide.application.batch.worker.intake.services.DataRestClient;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataSetName;
import de.hartz.software.sodevsalaryguide.core.port.repo.ComputationRepo;
import de.hartz.software.sodevsalaryguide.core.port.repo.EvaluatedDataReadRepo;
import de.hartz.software.sodevsalaryguide.core.port.repo.RawDataRepo;
import de.hartz.software.sodevsalaryguide.core.port.service.AMQPSendService;
import lombok.val;
import org.junit.jupiter.api.Disabled;
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

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static de.hartz.software.sodevsalaryguide.application.http.config.WiremockRouterServiceImpl.SERVER_PORT;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureWebClient // https://stackoverflow.com/a/43131830/8524651
@SpringBootTest
@SpringBatchTest
@EnableWireMock({
        @ConfigureWireMock(name = "localhost", port = SERVER_PORT)
})
@ContextConfiguration(classes = {BatchTestConfig.class})
@ActiveProfiles({"persistence-test", "batch-test", "amqp-test"})
public class BatchComponentTest {
    private static final String TEST_FILE_1_NAME = "2011-chunk-1";
    private static final String TEST_FILE_2_NAME = "2012-chunk-1";
    @Autowired
    private AMQPSendService amqpService;
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;
    @Autowired
    private RawDataRepo rawDataRepo;
    @Autowired
    private EvaluatedDataReadRepo evaluatedDataReadRepo;
    @Autowired
    private ComputationRepo computationCrudRepo;

    @InjectWireMock("localhost")
    private WireMockServer wiremock;


    @Test
    public void providingOneChunkAsync_runningBatch_processesAllData() throws Exception {
        setupMockAmqpWithOneSet();
        setupMockDataService();

        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        // TODO: RawRow Computation Problem
        //   ALL: org.springframework.dao.InvalidDataAccessApiUsageException: detached entity passed to persist: de.hartz.software.sodevsalaryguide.adapter.persistence.model.ComputationJpa
        //   Merge: https://stackoverflow.com/questions/19074278/not-null-property-references-a-transient-value-transient-instance-must-be-save

        assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
        assertEquals(2814, getRawEntryCount());
        assertEquals(2814, getEntryCount());
        assertEquals(1, getComputations());
    }


    // TODO: Why is redelviered=true it seems acknowleding is not working in mocked environments? That is causing the same message to get queued again leading to infitne calls..
    @Disabled
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
        wiremock.start();
        stubFileRestEndpoints(TEST_FILE_1_NAME);
        stubFileRestEndpoints(TEST_FILE_2_NAME);
    }

    private void stubFileRestEndpoints(String fileName) throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        wiremock.stubFor(
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

    private void setupMockAmqpWithOneSet() {
        // TODO: Maybe it is better to send from same thread?
        val testName1 = new RawDataSetName(TEST_FILE_1_NAME, 2011);
        amqpService.queueDatasetName(testName1);
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
