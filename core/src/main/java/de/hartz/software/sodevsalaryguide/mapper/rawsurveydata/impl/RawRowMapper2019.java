package de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl;

import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.AbstractRawRowMapper;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.model.raw.ColumnList;

import java.util.Optional;

public class RawRowMapper2019 extends AbstractRawRowMapper {
    @Override
    public Integer MAPPER_FOR_YEAR() {
        return 2019;
    }

    public final String getSalaryColumnName() {
        return "ConvertedComp"; // 2019 - 2021
    }

    // TODO: or ignore currency column as salary is already converted..
    public final String getCurrencyColumnName() {
        return "CurrencyDesc";
    }

    public final String getGenderColumnName() {
        return "Gender";
    }

    public final String getYearsOfExperienceColumnName() {
        return "YearsCode";
    }

    public final Optional<String> getAbilitiesColumnName() {
        return Optional.of("LanguageWorkedWith");
    }

    @Override
    public ColumnList getAbilitiesColumnList() {
        return null;
    }

    public final String getDegreeColumnName() {
        return "EdLevel";
    }

    public final String getCompanyColumnName() {
        return "OrgSize";
    }

    public final String getCountryColumnName() {
        return "Country";
    }

}
