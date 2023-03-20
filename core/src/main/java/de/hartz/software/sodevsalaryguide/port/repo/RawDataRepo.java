package de.hartz.software.sodevsalaryguide.port.repo;

import de.hartz.software.sodevsalaryguide.model.raw.RawRow;

public interface RawDataRepo {

  public void insertRawRow(RawRow rawRow);
}
