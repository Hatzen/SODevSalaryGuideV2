package de.hartz.software.sodevsalaryguide.application.http.api.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SurveyEntryDTO {
    private Double salary;
}
