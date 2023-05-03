package de.hartz.software.sodevsalaryguide.adapter.persistence;

import de.hartz.software.sodevsalaryguide.adapter.persistence.model.ComputationJpa;
import de.hartz.software.sodevsalaryguide.adapter.persistence.model.RawRowJpa;
import de.hartz.software.sodevsalaryguide.adapter.persistence.repo.ComputationCrudRepo;
import de.hartz.software.sodevsalaryguide.adapter.persistence.repo.RawDataCrudRepo;
import de.hartz.software.sodevsalaryguide.adapter.persistence.repo.RawDataRepoJpa;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceConfiguration.class)
@DataJpaTest
@ActiveProfiles({"persistence-test"})
public class RawDataCrudRepoTest {

  @Autowired private RawDataRepoJpa rawDataRepoJpa;
  @Autowired private ComputationCrudRepo computationCrudRepo;

  @Autowired private RawDataCrudRepo rawDataCrudRepo;

  @Test
  public void crud_writeAndReadData_returnsSameData() {
    ComputationJpa computation =
        ComputationJpa.builder()
            .chunk(1)
            .year(2011)
            .endtime(LocalDateTime.MAX)
            .starttime(LocalDateTime.MIN)
            .build();

    // computation = computationCrudRepo.save(computation);

    RawRowJpa rawRow = RawRowJpa.builder().computation(computation).build();
    rawDataCrudRepo.save(rawRow);

    val rawDataEntry = rawDataCrudRepo.findAll();
    val actualList =
        StreamSupport.stream(rawDataEntry.spliterator(), false).collect(Collectors.toList());

    Assertions.assertThat(actualList).hasSize(1);
    Assertions.assertThat(actualList.get(0).getId()).isEqualTo(1);
    Assertions.assertThat(actualList.get(0).getComputation().getComputationid()).isEqualTo(1);
  }

  @Test
  public void componenttest_writeAndReadData_returnsSameData() {
    // evaluatedDataRepoJpa.insertRawRow(rawRow);
  }
}
