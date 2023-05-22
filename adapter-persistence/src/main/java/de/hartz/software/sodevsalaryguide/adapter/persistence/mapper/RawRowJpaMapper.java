package de.hartz.software.sodevsalaryguide.adapter.persistence.mapper;

import de.hartz.software.sodevsalaryguide.adapter.persistence.model.RawRowJpa;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawRow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

// https://mapstruct.org/documentation/stable/reference/html/#updating-bean-instances
@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN)
public interface RawRowJpaMapper {

  RawRowJpaMapper INSTANCE = Mappers.getMapper(RawRowJpaMapper.class);

  @Mapping(target = "id", ignore = true)
  RawRowJpa rawRowToJpa(RawRow rawRow);

  RawRow rawRowJpaToDomain(RawRowJpa rawRow);
}
