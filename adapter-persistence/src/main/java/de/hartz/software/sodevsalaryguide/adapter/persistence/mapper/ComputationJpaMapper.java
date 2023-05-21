package de.hartz.software.sodevsalaryguide.adapter.persistence.mapper;

import de.hartz.software.sodevsalaryguide.adapter.persistence.model.ComputationJpa;
import de.hartz.software.sodevsalaryguide.core.model.Computation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

// https://mapstruct.org/documentation/stable/reference/html/#updating-bean-instances
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ComputationJpaMapper {

  ComputationJpaMapper INSTANCE = Mappers.getMapper(ComputationJpaMapper.class);

  ComputationJpa map(Computation computation);

  Computation map(ComputationJpa computation);
}
