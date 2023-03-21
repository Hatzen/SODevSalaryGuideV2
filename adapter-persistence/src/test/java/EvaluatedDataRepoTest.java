import de.hartz.software.sodevsalaryguide.model.Range;
import de.hartz.software.sodevsalaryguide.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.repo.EvaluatedDataRepoJpa;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceTestConfig.class)
@DataJpaTest
public class EvaluatedDataRepoTest {

  @Autowired private EvaluatedDataRepoJpa evaluatedDataRepoJpa;

  @Test
  public void componenttest_writeAndReadData_returnsSameData() {
    SurveyEntry testEntryMax =
        SurveyEntry.builder()
            .salary(1000.0)
            .age(1)
            .abilities(Set.of("SQL", "JAVA", "FUN"))
            .country("DE")
            .companySize(new Range(100, 1000))
            .build();
    SurveyEntry testEntryMin = SurveyEntry.builder().salary(1000.0).build();

    List<SurveyEntry> testEntries = List.of(testEntryMax, testEntryMin);
    evaluatedDataRepoJpa.insertAllSurveyEntries(testEntries);
    List<SurveyEntry> actualEntries = evaluatedDataRepoJpa.getAllSurveyEntries();

    Assertions.assertEquals(testEntries, actualEntries);
  }
}
