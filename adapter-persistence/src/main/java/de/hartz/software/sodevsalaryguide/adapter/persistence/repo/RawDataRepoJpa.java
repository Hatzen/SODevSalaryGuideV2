package de.hartz.software.sodevsalaryguide.adapter.persistence.repo;

import de.hartz.software.sodevsalaryguide.adapter.persistence.mapper.RawRowJpaMapper;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataset;
import de.hartz.software.sodevsalaryguide.core.model.raw.RawRow;
import de.hartz.software.sodevsalaryguide.core.port.repo.RawDataRepo;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RawDataRepoJpa implements RawDataRepo {

  @Autowired private RawDataCrudRepo rawDataCrudRepo;

  @Override
  public void insertRawRow(RawRow rawRow) {
    val mappedinstance = RawRowJpaMapper.INSTANCE.rawRowToJpa(rawRow);
    rawDataCrudRepo.save(mappedinstance);
  }

  @Override
  public RawDataset getAll() {
    val iterable = rawDataCrudRepo.findAll();

    val list =
        StreamSupport.stream(iterable.spliterator(), false)
            .map(RawRowJpaMapper.INSTANCE::rawRowJpaToDomain)
            .collect(Collectors.toList());
    return new RawDataset(list);
  }
}
