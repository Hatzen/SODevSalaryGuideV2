package de.hartz.software.sodevsalaryguide.adapter.persistence.repo;

import de.hartz.software.sodevsalaryguide.adapter.persistence.mapper.ComputationJpaMapper;
import de.hartz.software.sodevsalaryguide.adapter.persistence.model.ComputationJpa;
import de.hartz.software.sodevsalaryguide.core.model.Computation;
import de.hartz.software.sodevsalaryguide.core.port.repo.ComputationRepo;
import lombok.val;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputationCrudRepo extends CrudRepository<ComputationJpa, Long>, ComputationRepo {

    default Computation save(Computation entity) {
        val computationJpa = ComputationJpaMapper.INSTANCE.map(entity);
        val result = this.save(computationJpa);
        return ComputationJpaMapper.INSTANCE.map(result);
    }
}
