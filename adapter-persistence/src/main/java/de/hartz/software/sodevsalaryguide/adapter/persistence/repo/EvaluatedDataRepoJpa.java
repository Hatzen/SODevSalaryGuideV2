package de.hartz.software.sodevsalaryguide.adapter.persistence.repo;

import de.hartz.software.sodevsalaryguide.adapter.persistence.mapper.SurveyEntryJpaMapper;
import de.hartz.software.sodevsalaryguide.adapter.persistence.model.SurveyEntryJpa;
import de.hartz.software.sodevsalaryguide.core.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.core.port.repo.EvaluatedDataReadRepo;
import de.hartz.software.sodevsalaryguide.core.port.repo.EvaluatedDataWriteRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;
import lombok.val;
import org.springframework.stereotype.Repository;

@Repository
public class EvaluatedDataRepoJpa implements EvaluatedDataWriteRepo, EvaluatedDataReadRepo {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<SurveyEntry> getAllSurveyEntries() {
    // https://www.baeldung.com/hibernate-select-all
    val list =
        entityManager
            .createQuery(
                "SELECT a FROM SurveyEntryJpa a LEFT JOIN a.abilities b", SurveyEntryJpa.class)
            .getResultList();
    return list.stream()
        .map(SurveyEntryJpaMapper.INSTANCE::surveyEntryJpaToDomain)
        .collect(Collectors.toList());
  }

  @Override
  public void insertAllSurveyEntries(List<SurveyEntry> entries) {
    entries.stream()
        .map(SurveyEntryJpaMapper.INSTANCE::surveyEntryDomainToJpa)
        .forEach(jpa -> entityManager.merge(jpa));
  }

  public Long countInvalidEntries() {
    return entityManager
        .createQuery("SELECT count(a) FROM SurveyEntryJpa a WHERE a.salary = -1", Long.class)
        .getSingleResult();
  }

  // TODO: Get sql data for returning calculated boxplot data
  // https://mode.com/blog/how-to-make-box-and-whisker-plot-sql/
}
