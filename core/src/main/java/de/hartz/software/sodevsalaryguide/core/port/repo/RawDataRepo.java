package de.hartz.software.sodevsalaryguide.core.port.repo;

import de.hartz.software.sodevsalaryguide.core.model.raw.RawDataset;import de.hartz.software.sodevsalaryguide.core.model.raw.RawRow;

public interface RawDataRepo {

  public void insertRawRow(RawRow rawRow);
RawDataset getAll();}
