import static org.junit.jupiter.api.Assertions.assertEquals;

import de.hartz.software.sodevsalaryguide.BatchConfiguration;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBatchTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BatchConfiguration.class)
public class ChunksTest {

  @Autowired private JobLauncherTestUtils jobLauncherTestUtils;

  @Test
  public void givenChunksJob_whenJobEnds_thenStatusCompleted() throws Exception {

    JobExecution jobExecution = jobLauncherTestUtils.launchJob();

    assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
  }
}
