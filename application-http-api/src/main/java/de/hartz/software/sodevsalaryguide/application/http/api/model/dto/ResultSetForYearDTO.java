package de.hartz.software.sodevsalaryguide.application.http.api.model.dto;

import de.hartz.software.sodevsalaryguide.core.model.SurveyEntry;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResultSetForYearDTO {
    List<SurveyEntry> resultSet;
    long overallEntryCount;
    long invalidEntryCount;

    int year;
}
