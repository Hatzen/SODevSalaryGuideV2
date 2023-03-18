package de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl;

import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.AbstractRawRowMapper;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.model.raw.ColumnList;

import java.util.Optional;

public class RawRowMapper2018 extends AbstractRawRowMapper {
    @Override
    public Integer MAPPER_FOR_YEAR() {
        return 2018;
    }

    public final String getSalaryColumnName() {
        return "ConvertedSalary";
    }

    public final String getCurrencyColumnName() {
        return "Currency";
    }

    public final String getGenderColumnName() {
        return "Gender";
    }

    public final String getYearsOfExperienceColumnName() {
        return "YearsCoding";
    }

    public final Optional<String> getAbilitiesColumnName() {
        return Optional.of("LanguageWorkedWith");
    }

    @Override
    public ColumnList getAbilitiesColumnList() {
        return null;
    }

    public final String getDegreeColumnName() {
        return "FormalEducation";
    }

    public final String getCompanyColumnName() {
        return "CompanySize";
    }

    public final String getCountryColumnName() {
        return "Country";
    }

}
