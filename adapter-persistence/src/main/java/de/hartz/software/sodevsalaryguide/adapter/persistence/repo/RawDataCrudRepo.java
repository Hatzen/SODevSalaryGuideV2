package de.hartz.software.sodevsalaryguide.adapter.persistence.repo;

import de.hartz.software.sodevsalaryguide.adapter.persistence.model.RawRowJpa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RawDataCrudRepo extends CrudRepository<RawRowJpa, Long> {

  // TODO: Implement useful operations..
  // https://www.baeldung.com/spring-data-jpa-query
  // @Query("SELECT u FROM User u WHERE u.status = 1")
  // abstract Long getInvalidSurveyEntryCount();
}
