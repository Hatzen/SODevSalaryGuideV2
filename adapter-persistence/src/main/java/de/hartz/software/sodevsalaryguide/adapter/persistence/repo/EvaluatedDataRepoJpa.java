package de.hartz.software.sodevsalaryguide.adapter.persistence.repo;

import de.hartz.software.sodevsalaryguide.adapter.persistence.mapper.SurveyEntryJpaMapper;
import de.hartz.software.sodevsalaryguide.adapter.persistence.model.SurveyEntryJpa;
import de.hartz.software.sodevsalaryguide.core.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.core.model.dto.FilterDto;
import de.hartz.software.sodevsalaryguide.core.port.repo.EvaluatedDataReadRepo;
import de.hartz.software.sodevsalaryguide.core.port.repo.EvaluatedDataWriteRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
  public List<SurveyEntry> getMatchingSurveyEntries(FilterDto filterDto) {
    // https://www.baeldung.com/hibernate-criteria-queries
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<SurveyEntryJpa> cr = builder.createQuery(SurveyEntryJpa.class);
    Root<SurveyEntryJpa> root = cr.from(SurveyEntryJpa.class);

    ArrayList<Predicate> predicates = new ArrayList<>();

    if (filterDto.companySize() != null) {
      predicates.add(builder.ge(root.get("companySizeMin"), filterDto.companySize().min()));
      predicates.add(builder.le(root.get("companySizeMax"), filterDto.companySize().max()));
    }

    if (filterDto.expirienceInYears() != null) {
      predicates.add(builder.ge(root.get("expirienceInYearsMin"), filterDto.expirienceInYears().min()));
      predicates.add(builder.le(root.get("expirienceInYearsMax"), filterDto.expirienceInYears().max()));
    }

    if (filterDto.abilities() != null && !filterDto.abilities().isEmpty()) {
      predicates.add(root.join("abilities").get("ability").in(filterDto.abilities().toArray()));
    }

    if (filterDto.countries() != null && !filterDto.countries().isEmpty()) {
      predicates.add(root.get("country").in(filterDto.countries().toArray()));
    }

    if (filterDto.degrees() != null && !filterDto.degrees().isEmpty()) {
      predicates.add(root.get("highestDegree").in(filterDto.degrees().toArray()));
    }

    if (filterDto.genders() != null && !filterDto.genders().isEmpty()) {
      predicates.add(root.get("gender").in(filterDto.genders().stream().map(Enum::name).toArray()));
    }

    if (filterDto.selectedYears() != null && !filterDto.selectedYears().isEmpty()) {
      predicates.add(root.get("yearOfSurvey").in(filterDto.selectedYears().toArray()));
    }

    cr = cr.select(root).where(predicates.toArray(Predicate[]::new));

    val list =
            entityManager.createQuery(cr)
                    .getResultList();
    return list.stream()
            .map(SurveyEntryJpaMapper.INSTANCE::surveyEntryJpaToDomain)
            .collect(Collectors.toList());
  }

  @Override
  public Set<String> getAllAbilities() {
    // https://www.baeldung.com/hibernate-select-all
    val list =
            entityManager
                    .createQuery(
                            "SELECT DISTINCT a.ability FROM AbilityJpa a", String.class)
                    .getResultList();
    return new HashSet<>(list);
  }

  @Override
  public Set<String> getAllEducations() {
    // https://www.baeldung.com/hibernate-select-all
    val list =
            entityManager
                    .createQuery(
                            "SELECT DISTINCT a.highestDegree FROM SurveyEntryJpa a", String.class)
                    .getResultList();
    return new HashSet<>(list);
  }

  @Override
  public Set<String> getAllCountries() {
    // https://www.baeldung.com/hibernate-select-all
    val list =
            entityManager
                    .createQuery(
                            "SELECT DISTINCT a.country FROM SurveyEntryJpa a", String.class)
                    .getResultList();
    return new HashSet<>(list);
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

  // Wont be done, we simply filter all entries..
  // TODO: Get sql data for returning calculated boxplot data
  // https://mode.com/blog/how-to-make-box-and-whisker-plot-sql/
}
