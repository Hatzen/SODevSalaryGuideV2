package de.hartz.software.sodevsalaryguide.core.port.repo;

import de.hartz.software.sodevsalaryguide.core.model.Computation;
import java.util.List;

public interface ComputationRepo {
  Computation save(Computation entity);
  List<Computation> getAll();
}
