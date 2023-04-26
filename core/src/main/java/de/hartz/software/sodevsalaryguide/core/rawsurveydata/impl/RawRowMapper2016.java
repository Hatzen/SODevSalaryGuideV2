package de.hartz.software.sodevsalaryguide.core.rawsurveydata.impl;

import de.hartz.software.sodevsalaryguide.core.rawsurveydata.AbstractRawRowMapper;
import de.hartz.software.sodevsalaryguide.core.rawsurveydata.model.raw.ColumnList;
import java.util.Optional;

public class RawRowMapper2016 extends AbstractRawRowMapper {
  @Override
  public Integer MAPPER_FOR_YEAR() {
    return 2016;
  }

  public final String getSalaryColumnName() {
    return "salary_range";
  }

  public final String getCurrencyColumnName() {
    return AbstractRawRowMapper.COLUMN_DONT_EXIST;
  }

  public final String getGenderColumnName() {
    return "Gender";
  }

  public final String getYearsOfExperienceColumnName() {
    return "experience_range"; // or use midpoint
  }

  public final Optional<String> getAbilitiesColumnName() {
    return Optional.of("tech_do");
  }

  @Override
  public ColumnList getAbilitiesColumnList() {
    return null;
  }

  public final String getDegreeColumnName() {
    return "education";
  }

  public final String getCompanyColumnName() {
    return "company_size_range";
  }

  public final String getCountryColumnName() {
    return "country";
  }
}
