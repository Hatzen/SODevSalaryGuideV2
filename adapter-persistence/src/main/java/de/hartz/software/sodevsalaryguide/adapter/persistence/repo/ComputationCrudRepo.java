package de.hartz.software.sodevsalaryguide.adapter.persistence.repo;

import de.hartz.software.sodevsalaryguide.adapter.persistence.mapper.ComputationJpaMapper;
import de.hartz.software.sodevsalaryguide.adapter.persistence.model.ComputationJpa;
import de.hartz.software.sodevsalaryguide.core.model.Computation;
import de.hartz.software.sodevsalaryguide.core.port.repo.ComputationRepo;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.val;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputationCrudRepo extends CrudRepository<ComputationJpa, Long>, ComputationRepo {

  default Computation save(Computation entity) {
    val computationJpa = ComputationJpaMapper.INSTANCE.mapToJpa(entity);
    val result = this.save(computationJpa);
    return ComputationJpaMapper.INSTANCE.mapToDomain(result);
  }

  default List<Computation> getAll() {
    return StreamSupport.stream(this.findAll().spliterator(), false)
        .map(ComputationJpaMapper.INSTANCE::mapToDomain)
        .collect(Collectors.toList());
  }
}
