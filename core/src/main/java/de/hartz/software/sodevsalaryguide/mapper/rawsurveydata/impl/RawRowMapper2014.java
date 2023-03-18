package de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl;

import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.AbstractRawRowMapper;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.model.raw.ColumnList;

import java.util.Optional;

public class RawRowMapper2014 extends AbstractRawRowMapper {
    @Override
    public Integer MAPPER_FOR_YEAR() {
        return 2014;
    }

    public final String getSalaryColumnName() {
        return "Including bonus, what is your annual compensation in USD?"; // 2011 - 2014
    }

    public final String getCurrencyColumnName() {
        return AbstractRawRowMapper.COLUMN_DONT_EXIST;
    }

    public final String getGenderColumnName() {
        return "Gender";
    }

    public final String getYearsOfExperienceColumnName() {
        return "How many years of IT/Programming experience do you have?";
    }

    public final Optional<String> getAbilitiesColumnName() {
        return Optional.empty();
    }

    @Override
    public ColumnList getAbilitiesColumnList() {
        return new ColumnList("Which of the following languages or technologies have you used significantly in the past year?",
                43,
                54);
    }

    public final String getDegreeColumnName() {
        return AbstractRawRowMapper.COLUMN_DONT_EXIST;
    }

    public final String getCompanyColumnName() {
        return "How many developers are employed at your company?";
    }

    public final String getCountryColumnName() {
        return "What Country do you live in?";
    }

}
