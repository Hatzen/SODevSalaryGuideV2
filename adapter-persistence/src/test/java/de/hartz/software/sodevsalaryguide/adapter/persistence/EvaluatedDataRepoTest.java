package de.hartz.software.sodevsalaryguide.adapter.persistence;

import de.hartz.software.sodevsalaryguide.adapter.persistence.repo.EvaluatedDataRepoJpa;
import de.hartz.software.sodevsalaryguide.core.model.Range;
import de.hartz.software.sodevsalaryguide.core.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.core.model.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceConfiguration.class)
// @ContextConfiguration(classes = PersistenceTestConfig.class)
// @AutoConfigureTestDatabase
@DataJpaTest
@ActiveProfiles({"persistence-test"})
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
            .gender(Gender.FEMALE)
            .companySize(new Range(100, 1000))
            .build();
    SurveyEntry testEntryMin = SurveyEntry.builder().salary(1000.0).build();

    List<SurveyEntry> testEntries = List.of(testEntryMax, testEntryMin);
    evaluatedDataRepoJpa.insertAllSurveyEntries(testEntries);
    List<SurveyEntry> actualEntries = evaluatedDataRepoJpa.getAllSurveyEntries();

    // ability set may conntain in different order and mapped mapstruct Ranges are filled with null..
    final var expectedMax = testEntryMax.toBuilder()
            .abilities(Set.of("SQL", "JAVA", "FUN"))
            .expirienceInYears(new Range(null, null))
            .build();
    final var expectedMin = testEntryMin.toBuilder()
            .abilities(Set.of())
            .expirienceInYears(new Range(null, null))
            .companySize(new Range(null, null))
            .build();
    List<SurveyEntry> expectedEntries = List.of(expectedMax, expectedMin);

    Assertions.assertEquals(expectedEntries, actualEntries);
  }
}
