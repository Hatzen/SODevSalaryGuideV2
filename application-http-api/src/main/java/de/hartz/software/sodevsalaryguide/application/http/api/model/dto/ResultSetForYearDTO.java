package de.hartz.software.sodevsalaryguide.application.http.api.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResultSetForYearDTO {
    List<SurveyEntryDTO> resultSet;
    long overallEntryCount;
    long invalidEntryCount;

    int year;
}
