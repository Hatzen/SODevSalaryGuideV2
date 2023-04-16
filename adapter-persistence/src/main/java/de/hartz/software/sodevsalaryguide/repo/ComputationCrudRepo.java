package de.hartz.software.sodevsalaryguide.repo;

import de.hartz.software.sodevsalaryguide.model.ComputationJpa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputationCrudRepo extends CrudRepository<ComputationJpa, Long> {

}
