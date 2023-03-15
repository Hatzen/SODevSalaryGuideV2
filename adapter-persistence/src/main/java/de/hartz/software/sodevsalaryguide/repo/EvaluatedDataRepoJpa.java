package de.hartz.software.sodevsalaryguide.repo;

import de.hartz.software.sodevsalaryguide.mapper.JpaMapper;
import de.hartz.software.sodevsalaryguide.model.SurveyEntry;
import de.hartz.software.sodevsalaryguide.model.SurveyEntryJpa;
import de.hartz.software.sodevsalaryguide.port.EvaluatedDataReadRepo;
import de.hartz.software.sodevsalaryguide.port.EvaluatedDataWriteRepo;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EvaluatedDataRepoJpa implements EvaluatedDataWriteRepo, EvaluatedDataReadRepo {

    @Override
    public List<SurveyEntry> getAllSurveyEntries() {
        val list = List.of(new SurveyEntryJpa());
        return list.stream().map(JpaMapper.INSTANCE::surveyEntryJpaToDomain).collect(Collectors.toList());
    }

    @Override
    public void insertAllSurveyEntries(List<SurveyEntry> entries) {
    }

    // TODO: Get sql data for returning calculated boxplot data https://mode.com/blog/how-to-make-box-and-whisker-plot-sql/
}
