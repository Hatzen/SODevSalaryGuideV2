package de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.impl;

import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.AbstractRawRowMapper;
import de.hartz.software.sodevsalaryguide.mapper.rawsurveydata.model.raw.ColumnList;

import java.util.Optional;

public class RawRowMapper2015 extends AbstractRawRowMapper {
    @Override
    public Integer MAPPER_FOR_YEAR() {
        return 2015;
    }

    public final String getSalaryColumnName() {
        return "Salary";
    }

    public final String getCurrencyColumnName() {
        return AbstractRawRowMapper.COLUMN_DONT_EXIST;
    }

    public final String getGenderColumnName() {
        return "Gender";
    }

    public final String getYearsOfExperienceColumnName() {
        return "Years IT / Programming Experience";
    }

    public final Optional<String> getAbilitiesColumnName() {
        return Optional.of(AbstractRawRowMapper.COLUMN_DONT_EXIST);
    }

    @Override
    public ColumnList getAbilitiesColumnList() {
        return null;
    }

    public final String getDegreeColumnName() {
        return "101";
    }

    public final String getCompanyColumnName() {
        return AbstractRawRowMapper.COLUMN_DONT_EXIST;
    }

    public final String getCountryColumnName() {
        return "0";
    }

}
