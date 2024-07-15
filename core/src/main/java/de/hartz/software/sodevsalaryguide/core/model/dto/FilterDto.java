package de.hartz.software.sodevsalaryguide.core.model.dto;

import de.hartz.software.sodevsalaryguide.core.model.Range;
import de.hartz.software.sodevsalaryguide.core.model.enums.Gender;

import java.util.Map;
import java.util.Set;

public record FilterDto(
        Map<Integer, Boolean> selectedYears,
        Range expirienceInYears,
        Set<Gender> genders,
        Set<String> abilities,
        Range companySize,
        Set<String> countries,
        Set<String> degrees,

        // TODO: Consider?
        Boolean abilitiesFilterActive,
        Boolean companySizeFilterActive,
        Boolean countriesFilterActive,
        Boolean degreeFilterActive,
        Boolean expirienceFilterActive
) {

}