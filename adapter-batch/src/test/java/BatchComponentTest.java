import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// https://stackoverflow.com/a/39215237/8524651
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// https://stackoverflow.com/a/55838334/8524651
@AutoConfigureTestDatabase
@DataJpaTest
@SpringBatchTest

// @AutoConfigureWebClient
// @AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BatchTestConfig.class})
public class BatchComponentTest {

  @Autowired private JobLauncherTestUtils jobLauncherTestUtils;

  @Test
  public void givenChunksJob_whenJobEnds_thenStatusCompleted() throws Exception {

    JobExecution jobExecution = jobLauncherTestUtils.launchJob();

    assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
  }
}
