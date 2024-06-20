package de.hartz.software.sodevsalaryguide.application.http.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@AllArgsConstructor
@Data
public class FilterValuesDTO {
    Set<String> countries;
    Set<String> educations;
    Set<String> abilities;
}
