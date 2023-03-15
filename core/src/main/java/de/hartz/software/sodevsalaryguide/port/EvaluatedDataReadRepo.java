package de.hartz.software.sodevsalaryguide.port;

import de.hartz.software.sodevsalaryguide.model.SurveyEntry;

import java.util.List;

public interface EvaluatedDataReadRepo {

    List<SurveyEntry> getAllSurveyEntries();
}
