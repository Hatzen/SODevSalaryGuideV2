package de.hartz.software.sodevsalaryguide.core.port.repo;

import de.hartz.software.sodevsalaryguide.core.model.SurveyEntry;
import java.util.List;

public interface EvaluatedDataWriteRepo {

  void insertAllSurveyEntries(List<SurveyEntry> entries);
}
