package de.hartz.software.sodevsalaryguide.repo;

import de.hartz.software.sodevsalaryguide.mapper.RawRowJpaMapper;
import de.hartz.software.sodevsalaryguide.model.raw.RawRow;
import de.hartz.software.sodevsalaryguide.port.repo.RawDataRepo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RawDataRepoJpa implements RawDataRepo {

  @Autowired private RawDataCrudRepo crudRepo;

  @Override
  public void insertRawRow(RawRow rawRow) {
    val mappedinstance = RawRowJpaMapper.INSTANCE.surveyEntryDomainToJpa(rawRow);
    crudRepo.save(mappedinstance);
  }
}
