import de.hartz.software.sodevsalaryguide.repo.EvaluatedDataRepoJpa;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceTestConfig.class)
@DataJpaTest
public class EvaluatedDataRepoTest {

  @Autowired private EvaluatedDataRepoJpa evaluatedDataRepoJpa;

  @Test
  public void someDataAccessTest() {
    evaluatedDataRepoJpa.insertAllSurveyEntries(List.of());
    evaluatedDataRepoJpa.getAllSurveyEntries();
  }

  @Test
  public void test() {
    fail();
  }
}
