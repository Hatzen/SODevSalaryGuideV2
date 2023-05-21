package de.hartz.software.sodevsalaryguide.core.port.repo;

import de.hartz.software.sodevsalaryguide.core.model.Computation;

public interface ComputationRepo {
  Computation save(Computation entity);
}
