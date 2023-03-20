package de.hartz.software.sodevsalaryguide.port.repo;

import de.hartz.software.sodevsalaryguide.model.SurveyEntry;
import java.util.List;

public interface EvaluatedDataReadRepo {

  List<SurveyEntry> getAllSurveyEntries();
}
