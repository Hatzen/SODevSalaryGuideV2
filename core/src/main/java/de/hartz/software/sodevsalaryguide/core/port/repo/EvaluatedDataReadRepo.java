package de.hartz.software.sodevsalaryguide.core.port.repo;

import de.hartz.software.sodevsalaryguide.core.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.core.model.dto.FilterDto;

import java.util.List;
import java.util.Set;

public interface EvaluatedDataReadRepo {

  List<SurveyEntry> getAllSurveyEntries();

  List<SurveyEntry> getMatchingSurveyEntries(FilterDto filterDto);

  Set<String> getAllAbilities();

  Set<String> getAllEducations();

  Set<String> getAllCountries();
}
